package de.irs.fopengine.web.examples;

import de.irs.fopengine.web.HibernateUtil;
import de.irs.fopengine.web.model.Font;
import de.irs.fopengine.web.model.Project;
import de.irs.fopengine.web.model.User;
import org.hibernate.Session;

public class ExampleUtils {

    public static void createData() {
        saveUser();
        saveProject();
    }

    public static void saveUser() {
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();
        User user1 = new User();
        user1.setUsername("user1");
        user1.setPassword("1");
        User user2 = new User();
        user2.setUsername("user2");
        user2.setPassword("2");

        session.beginTransaction();
        session.save(user1);
        session.save(user2);
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
        Project project2 = new Project();
        project2.setName("project2");
        project2.setDescription("desc2");
        project2.setGitUrl("url2");

        session.beginTransaction();
        //Save the Model object
        session.save(project);
        session.save(project2);

        Font font1 = new Font("font1");
        font1.setProject(project);
        session.save(font1);
        Font font2 = new Font("font2");
        font2.setProject(project);
        session.save(font2);
        Font font3 = new Font("font3");
        font3.setProject(project2);
        session.save(font3);
        Font font4 = new Font("font4");
        font4.setProject(project2);
        session.save(font4);
        //Commit transaction
        session.getTransaction().commit();
        session.close();
    }
}
