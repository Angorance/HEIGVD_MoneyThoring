import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {

        /*System.out.println("Hello World!");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernateSqLite.cfg.xml");

        entities.UserEntity user = new entities.UserEntity("tott","toto","gaugo",1, "weruoqgh", "slafj");
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
        if (em != null) {
            em.close();
        }
    }
*/

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
