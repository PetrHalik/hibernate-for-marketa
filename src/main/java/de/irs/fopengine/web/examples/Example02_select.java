package de.irs.fopengine.web.examples;


import de.irs.fopengine.web.HibernateUtil;
import de.irs.fopengine.web.model.Font;
import de.irs.fopengine.web.model.Project;
import de.irs.fopengine.web.model.User;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class Example02_select {

    public static void main(String[] args) {
        ExampleUtils.createData();
        //Hibernate Query Language (HQL)
        List<Font> fonts = getAllFonts_hql();
        System.out.println("font count: " + fonts.size());
        Font font = getFontById_hql(fonts.get(1).getId());
        System.out.println(String.format("parameter id=%d  result id = %d", fonts.get(1).getId(), font.getId()));

        //Native query
        fonts = getAllFonts_native();
        System.out.println("font count: " + fonts.size());
        font = getFontById_native(fonts.get(1).getId());
        System.out.println(String.format("parameter id=%d  result id = %d", fonts.get(1).getId(), font.getId()));


        //Criteria
        fonts = getAllFonts_criteria();
        System.out.println("font count: " + fonts.size());
        font = getFontById_criteria(fonts.get(1).getId());
        System.out.println(String.format("parameter id=%d  result id = %d", fonts.get(1).getId(), font.getId()));
        

        HibernateUtil.getSessionJavaConfigFactory().close();
    }

    private static Font getFontById_criteria(Long id) {
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();
        Criteria cr = session.createCriteria(Font.class);
        cr.add(Restrictions.eq("id", id));
        Font font = (Font) cr.list().get(0);
        session.close();
        // result must be checked
        return font;
    }

    private static List<Font> getAllFonts_criteria() {
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();
        Criteria cr = session.createCriteria(Font.class);
        List<Font> result = cr.list();
        session.close();
        return result;
    }

    private static Font getFontById_native(Long id) {
        String sql = "SELECT * FROM font WHERE id = :id";
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Font.class);
        query.setParameter("id", id);
        Font font =  (Font) query.list().get(0);
        session.close();
        // result must be checked
       return font;
    }

    private static List<Font> getAllFonts_native() {
        String sql = "SELECT * FROM font";
        Session session = HibernateUtil.getSessionJavaConfigFactory().openSession();
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Font.class);
        List results = query.list();
        session.close();
        return results;
    }
    
    

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
