package de.irs.fopengine.web;

import de.irs.fopengine.web.model.User;
import org.hibernate.Session;

public class HibernateMain {
    public static void main(String[] args) {


        //Get Session
        Session session = HibernateUtil.getSessionJavaConfigFactory().getCurrentSession();


        saveUser(session);
        //terminate session factory, otherwise program won't end
        HibernateUtil.getSessionJavaConfigFactory().close();
    }

    public static void saveUser(Session session) {
        User user = new User();
        user.setUsername("Pankaj");
        user.setPassword("123");
        session.beginTransaction();
        //Save the Model object
        session.save(user);
        //Commit transaction
        session.getTransaction().commit();
    }

}
