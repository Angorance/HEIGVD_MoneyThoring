import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello World!");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");

    }
}
