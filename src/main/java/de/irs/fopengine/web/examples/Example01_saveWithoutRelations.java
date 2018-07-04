package de.irs.fopengine.web.examples;


import de.irs.fopengine.web.HibernateUtil;
import de.irs.fopengine.web.model.Font;
import de.irs.fopengine.web.model.Project;
import de.irs.fopengine.web.model.User;
import org.hibernate.Session;

public class Example01_saveWithoutRelations {

    public static void main(String[] args) {
        saveUser();
        saveProject();
        HibernateUtil.getSessionJavaConfigFactory().close();
    }

    public static void saveUser() {
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();
        User user1 = new User();
        user1.setUsername("user1");
        user1.setPassword("1");

        session.beginTransaction();
        session.save(user1);

        //Commit transaction
        session.getTransaction().commit();
        session.close();
    }

    public static void saveProject() {
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();
        Project project = new Project();
        project.setName("project1");
        project.setDescription("desc1");
        project.setGitUrl("url1");


        session.beginTransaction();
        //Save the Model object
        session.save(project);


        Font font1 = new Font("font1");
        font1.setProjectId(project.getId());
        session.save(font1);
        Font font2 = new Font("font2");
        font2.setProjectId(project.getId());
        session.save(font2);
        //Commit transaction
        session.getTransaction().commit();
        session.close();
    }
}
