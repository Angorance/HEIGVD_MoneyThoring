import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        SessionFactory sessionFactory = new Configuration().configure("hibernateSessionFactory.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();

        session.close();
        sessionFactory.close();
    }
}
