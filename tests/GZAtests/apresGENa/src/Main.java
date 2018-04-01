import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello World!");
        Persistence.createEntityManagerFactory("myPersistenceUnit");
    }
}
