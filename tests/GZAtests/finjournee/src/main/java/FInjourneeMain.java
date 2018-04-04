import javax.persistence.Persistence;

public class FInjourneeMain {
    public static void main(String[] arg){
        Persistence.createEntityManagerFactory("persistenceUnit");

    }
}
