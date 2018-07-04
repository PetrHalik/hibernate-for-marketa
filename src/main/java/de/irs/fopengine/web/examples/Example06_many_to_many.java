package de.irs.fopengine.web.examples;


import de.irs.fopengine.web.HibernateUtil;
import de.irs.fopengine.web.model.Font;
import de.irs.fopengine.web.model.Project;
import de.irs.fopengine.web.model.Triplet;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class Example06_many_to_many {

    public static void main(String[] args) {
        Font font = saveProjectWithFontsAndTriplets();

//        Font addedFont = readFontWithTriplets(font.getId());
//        System.out.println("added project after read: " + addedFont);

        HibernateUtil.getSessionJavaConfigFactory().close();
    }

    private static Font saveProjectWithFontsAndTriplets() {
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();
        Font font1 = new Font("font1");
        Font font2 = new Font("font2");
        Triplet triplet1= new Triplet("Triplet1");
        Triplet triplet2= new Triplet("Triplet2");
        Triplet triplet3= new Triplet("Triplet3");
        Triplet triplet4= new Triplet("Triplet4");

        font1.getTriplets().add(triplet1);
        font1.getTriplets().add(triplet2);
        font1.getTriplets().add(triplet3);
        font2.getTriplets().add(triplet1);
        font2.getTriplets().add(triplet4);

        session.beginTransaction();
        session.save(font1);
        session.save(font2);
        session.save(triplet1);
        session.save(triplet2);
        session.save(triplet3);
        session.save(triplet4);
        session.getTransaction().commit();

        session.close();
        return font1;
    }

    private static Font readFontWithTriplets(Long id) {
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();
        Criteria cr = session.createCriteria(Font.class);
        cr.add(Restrictions.eq("id", id));
        Font font = (Font) cr.list().get(0);
        session.close();
        // result must be checked
        return font;
    }


}
