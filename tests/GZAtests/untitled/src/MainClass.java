
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MainClass {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");

    }
}
