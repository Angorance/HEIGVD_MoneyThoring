package dal.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * HibernateUtil classe.
 * this classe build the session factory from a good configuration file
 *
 * @author Guillaume Zaretti
 * @version 1.2
 */
public class HibernateUtil {
	
	private static SessionFactory pgSessionFactory = null;
	private static SessionFactory derbySessionFactory = null;

	/**
	 * build a new pg session factory
	 * @return sessionFactory
	 */
	private static SessionFactory buildPGSessionFactory() {
		
		try {
			return new Configuration().configure("hibernate.pgsql.cfg.xml")
					.buildSessionFactory();
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	/**
	 * build a new derbyy session factory
	 * @return sessionFactory
	 */
	private static SessionFactory buildDeSessionFactory() {
		
		try {
			return new Configuration().configure("hibernate.derby.cfg.xml")
					.buildSessionFactory();
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	/**
	 * get a singelton postgres SessionFactory
	 * @return SessionFactory
	 */
	public static SessionFactory getPGSessionFactory() {
		
		if (pgSessionFactory == null) {
			pgSessionFactory = buildPGSessionFactory();
		}
		return pgSessionFactory;
	}

	/**
	 * get a singelton derby SessionFactory
	 * @return SessionFactory
	 */
	public static SessionFactory getDeSessionFactory() {
		
		if (derbySessionFactory == null) {
			derbySessionFactory = buildDeSessionFactory();
		}
		return derbySessionFactory;
	}
}
