package dal.orm;

import dal.dalexception.DALException;
import dal.irepositories.IBankaccountRepository;
import dal.irepositories.IClientRepository;
import dal.repositories.pgsql.BankaccountPgRepository;
import dal.repositories.pgsql.ClientPgRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class PgORM implements IORM {

    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    private IClientRepository clientRepository;
    private IBankaccountRepository bankaccountRepository;

    @Override
    public IClientRepository getClientRepository() {
        if (clientRepository == null) {
            clientRepository = new ClientPgRepository(session, transaction);
        }
        return clientRepository;
    }

    @Override
    public IBankaccountRepository getBankaccountRepository() {
        if (bankaccountRepository == null) {
            bankaccountRepository = new BankaccountPgRepository(session, transaction);
        }
        return bankaccountRepository;
    }

    private void openSession() throws DALException {
        if(sessionFactory == null) {
            try {
                sessionFactory = new Configuration().configure("hibernate.pgsql.cfg.xml").buildSessionFactory();
            } catch (Throwable ex) {
                throw new DALException(ex);
            }
        }
        try{
            session = sessionFactory.openSession();
        }catch (Exception e){
            throw new DALException(e);
        }

    }

    @Override
    public void sessionClose() throws DALException {
        try {
            //session.flush();
            session.close();
        } catch (HibernateException e) {
           throw new DALException(e);
        }
    }

    @Override
    public void beginTransaction() throws DALException {
        try {
            openSession();
            transaction = session.beginTransaction();
        }catch (Exception e){
            throw new DALException(e);
        }
    }

    @Override
    public void rollback() throws DALException {
        try {
            transaction.rollback();
        }catch (Exception e){
            throw new DALException(e);
        }
    }

    @Override
    public void commit() throws DALException {
        try {
            transaction.commit();
        } catch (Exception e) {
            throw new DALException(e);
        }
    }
}
