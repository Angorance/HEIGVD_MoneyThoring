import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        SessionFactory sessionFactory = new Configuration()
                .configure() Configure le mapping ORM en analysant
        le contenu de hibernate.cfg.xml
                .buildSessionFactory();
        Session session = sessionFactory.openSession(); Ouverture connexion JDBC
                [.. Travail dans la session ..]
        session.close(); Fermeture connexion JDBC
        sessionFactory.close();

    }
}
