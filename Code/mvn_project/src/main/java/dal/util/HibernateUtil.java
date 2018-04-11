package dal.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory pgSessionFactory = buildPGSessionFactory();

    private static SessionFactory buildPGSessionFactory() {
        try {
            return new Configuration().configure("hibernate.pgsql.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getPGSessionFactory() {
        return pgSessionFactory;
    }
}
