package de.irs.fopengine.web.examples;


import de.irs.fopengine.web.HibernateUtil;
import de.irs.fopengine.web.model.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class Example04_one_to_one {

    public static void main(String[] args) {
        saveMaven();
        System.out.println(String.format("Maven count: %d", getAllMavens().size()));

        Project project = saveProjectWithMaven();
        System.out.println("project after save: " + project);

        Project addedProject = readProjectWithMaven(project.getId());
        System.out.println("added project after read: " + addedProject);

        project = saveProjectWithInfo();
        addedProject = readProjectWithMaven(project.getId());
        System.out.println("added project with info: " + addedProject);
        HibernateUtil.getSessionJavaConfigFactory().close();
    }

    private static Project saveProjectWithInfo() {
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();

        Info info1 = new Info("info1");
        Info info2 = new Info("info2");
        Project project = new Project("projectInfo", "urlInfo");
        project.setInfo(info2);
        info1.setProject(project);

        session.beginTransaction();
        session.save(info1);

        System.out.println(String.format("Info1 %s", info1));

        session.save(info2);
        session.save(project);
        session.getTransaction().commit();
        session.close();
        return project;
    }

    private static Project readProjectWithMaven(Long id) {
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();
        Criteria cr = session.createCriteria(Project.class);
        cr.add(Restrictions.eq("id", id));
        Project project = (Project) cr.list().get(0);
        session.close();
        // result must be checked
        return project;
    }

    private static Project saveProjectWithMaven() {
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();
        Maven maven = new Maven("group2", "artifact2", "version2");
        Project project = new Project("projectMaven", "urlMaven");
        project.setMaven(maven);
        session.beginTransaction();
        session.save(project);
        session.getTransaction().commit();
        session.close();
        return project;
    }

    public static void saveMaven() {
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();
        Maven maven = new Maven("group1", "artifact1", "version1");
        session.beginTransaction();
        session.save(maven);
        //Commit transaction
        session.getTransaction().commit();
        session.close();
    }

    private static List<Maven> getAllMavens() {
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();
        Criteria cr = session.createCriteria(Maven.class);
        List<Maven> result = cr.list();
        session.close();
        return result;
    }
}
