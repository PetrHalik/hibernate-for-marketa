package de.irs.fopengine.web;

import de.irs.fopengine.web.HibernateUtil;
import de.irs.fopengine.web.model.User;
import org.hibernate.Session;

public class HibernateXmlMain {
    public static void main(String[] args) {
        User emp = new User();
        emp.setUsername("Pankaj1");
        emp.setPassword("123");


        //Get Session
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        //start transaction
        session.beginTransaction();
        //Save the Model object
        session.save(emp);
        //Commit transaction
        session.getTransaction().commit();
        System.out.println("Employee ID="+emp.getId());

        //terminate session factory, otherwise program won't end
        HibernateUtil.getSessionFactory().close();
    }

}
