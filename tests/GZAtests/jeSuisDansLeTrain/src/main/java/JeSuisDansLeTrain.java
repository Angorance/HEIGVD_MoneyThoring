import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Persistence;
import java.awt.peer.SystemTrayPeer;

public class JeSuisDansLeTrain {

    public static void main(String[] args){
        //Persistence.createEntityManagerFactory("persistense.xml");
        SessionFactory sessionFactory = (SessionFactory) new Configuration().configure();
        System.out.println("kljsdlgjkf");

    }
}
