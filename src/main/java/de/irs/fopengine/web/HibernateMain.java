package de.irs.fopengine.web;

import de.irs.fopengine.web.model.Font;
import de.irs.fopengine.web.model.Project;
import de.irs.fopengine.web.model.User;

import org.hibernate.Session;

import java.util.List;

public class HibernateMain {

    public static void main(String[] args) {
        //Get Session
        Session session = HibernateUtil.getSessionJavaConfigFactory().getCurrentSession();
        //saveUser(session);
        //saveProject(session);
        saveFont();
        List<User> fonts = getAllFonts();

        //terminate session factory, otherwise program won't end
        HibernateUtil.getSessionJavaConfigFactory().close();
    }


    private static List<User> getAllFonts() {
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();
        List<User> result = session.createQuery("from Font").list();
        System.out.println("font count: " + result.size());
        session.close();
        return result;
    }

    public static void saveUser(Session session) {
        User user = new User();
        user.setUsername("Pankaj");
        user.setPassword("123");
        session.beginTransaction();
        session.save(user);
        //Commit transaction
        session.getTransaction().commit();
    }

    public static void saveProject(Session session) {
        Project project = new Project();
        project.setName("project1");
        project.setDescription("desc1");
        project.setGitUrl("url1");
        session.beginTransaction();
        //Save the Model object
        session.save(project);
        //Commit transaction
        session.getTransaction().commit();
    }

    public static void saveFont() {
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();
        Font font = new Font();
        font.setFontName("FontName1");
        font.setProjectId(1L);
        session.beginTransaction();
        //Save the Model object
        session.save(font);
        //Commit transaction
        session.getTransaction().commit();
        session.close();
    }
}
