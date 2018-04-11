package mtWithJpa.pgsql.testofjpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class testOfJpa {
    public static void jeTesteLannotationJPA(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory( "pgsqlPersistenceUnit" );
    }
}
