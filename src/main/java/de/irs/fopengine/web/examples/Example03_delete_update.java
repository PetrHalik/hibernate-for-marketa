package de.irs.fopengine.web.examples;


import de.irs.fopengine.web.HibernateUtil;
import de.irs.fopengine.web.model.Font;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class Example03_delete_update {

    public static void main(String[] args) {
        ExampleUtils.createData();
        //Hibernate Query Language (HQL)
        List<Font> fonts = getAllFonts_hql();
        System.out.println(String.format("Fonts: %d", fonts.size()));
        System.out.println("deleting " + fonts.get(0).getId());
        deleteFont(fonts.get(0).getId());
        fonts = getAllFonts_hql();
        System.out.println(String.format("Fonts after delete: %d", fonts.size()));

        String originName = fonts.get(1).getFontName();
        fonts.get(1).setFontName("name changed");
        updateFont(fonts.get(1));

        Font font = getFontById_hql(fonts.get(1).getId());
        System.out.println(String.format("Origin name: %s   Changed name: %s", originName, font.getFontName()));
        System.out.println(String.format("Fonts after update: %d", getAllFonts_hql().size()));

        Font fontNew = new Font("new Font");
     //   fontNew.setProjectId(2L);
        updateFont(fontNew);
        System.out.println(String.format("Fonts after save: %d", getAllFonts_hql().size()));
        HibernateUtil.getSessionJavaConfigFactory().close();
    }

    private static void updateFont(Font font) {
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(font);
        session.getTransaction().commit();
        session.close();
    }

    private static void deleteFont(long id) {
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();
        Font font = getFontById_hql(id);
        session.beginTransaction();
        session.delete(font);
        session.getTransaction().commit();
        session.close();
    }


    /** helper methods */

    private static Font getFontById_hql(long id) {
        String hql = "FROM Font F WHERE F.id = " + id;
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();
        Font result = (Font) session.createQuery(hql).uniqueResult();
        session.close();
        return result;
    }

    private static List<Font> getAllFonts_hql() {
        String hql = "FROM Font";
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();
        List<Font> result = session.createQuery(hql).list();
        session.close();
        return result;
    }
}
