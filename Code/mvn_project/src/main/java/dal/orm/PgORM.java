package dal.orm;

import dal.irepositories.IClientRepository;
import dal.repositories.pgsql.ClientPgRepository;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PgORM implements IORM {


   // private static final SessionFactory pgSessionFactory = buildPGSessionFactory();

    private IClientRepository clientRepository;

    @Override
    public IClientRepository getClientRepository() {
        if(clientRepository == null)
        {
            clientRepository = new ClientPgRepository();
        }
        return clientRepository;
    }


    /*public static SessionFactory buildPGSessionFactory() {
        try {
            return new Configuration().configure("hibernate.pgsql.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }*/

    /*public static SessionFactory getPGSessionFactory() {
        return pgSessionFactory;
    }*/
}
