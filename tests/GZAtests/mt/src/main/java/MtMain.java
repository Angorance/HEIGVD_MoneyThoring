import javax.persistence.Persistence;

public class MtMain {

    public static void main(String[] arg){
        System.out.println("hello");
        Persistence.createEntityManagerFactory("persistenceInPGSql");

    }
}
