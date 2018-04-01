package PGSQLiteIntel.entities;

import PGSQLiteIntel.entities.SQLite.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PGSQLiteIntelMain {

    public static void main(String [ ] args)
    {
        UserEntity user = new UserEntity("tott","toto","gaugo",false, "weruoqgh"
        , "slafj");

        System.out.print("Hello");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("META-INF/hibernatePersistanceSQLite.cfg.xml");

        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception ex) {
           /* if (findCustomer(customer.getKdcustomer()) != null) {
                throw new PreexistingEntityException("Customer " + customer + " already exists.", ex);
            }*/
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }


    }
}
