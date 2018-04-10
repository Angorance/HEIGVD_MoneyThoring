import mt.DerbyWithHibernate;
import mt.PostgresEithHibernate;
import mt.repositories.postgresql.ClientRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] arg) throws Exception {
        DerbyWithHibernate.displayClientMail();
        //PostgresEithHibernate.displayClientMail();
       // ClientRepository.exempleUtilisation();
       // mt.repositories.derby.ClientRepository.exempleUtilisation();

    }
}