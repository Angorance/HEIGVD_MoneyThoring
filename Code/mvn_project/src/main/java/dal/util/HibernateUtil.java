package dal.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static  SessionFactory pgSessionFactory = null;
    private static  SessionFactory derbySessionFactory = null;

    private static SessionFactory buildPGSessionFactory() {
        try {
            return new Configuration().configure("hibernate.pgsql.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static SessionFactory buildDeSessionFactory() {
        try {
            return new Configuration().configure("hibernate.derby.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getPGSessionFactory() {
        if(pgSessionFactory == null){
            pgSessionFactory = buildPGSessionFactory();
        }
        return pgSessionFactory;
    }

    public static SessionFactory getDeSessionFactory() {
        if(derbySessionFactory == null){
            derbySessionFactory = buildDeSessionFactory();
        }
        return derbySessionFactory;
    }
}
