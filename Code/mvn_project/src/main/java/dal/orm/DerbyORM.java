package dal.orm;

import dal.dalexception.DALException;
import dal.irepositories.IBankaccountRepository;
import dal.irepositories.IClientRepository;
import dal.repositories.derby.BankaccountDeRepository;
import dal.repositories.derby.ClientDeRepository;
import dal.repositories.pgsql.ClientPgRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class DerbyORM implements IORM {
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    private IClientRepository clientRepository;
    private IBankaccountRepository bankaccountRepository;

    @Override
    public IClientRepository getClientRepository() {
        //if (clientRepository == null) {
        clientRepository = new ClientDeRepository(session, transaction);
        //}
        return clientRepository;
    }

    @Override
    public IBankaccountRepository getBankaccountRepository() {
        // if(bankaccountRepository == null){
        bankaccountRepository = new BankaccountDeRepository(session, transaction);
        // }
        return bankaccountRepository;
    }


    private void openSession() throws DALException {
        //if(sessionFactory == null) {
        try {
            sessionFactory = new Configuration().configure("hibernate.derby.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            throw new DALException(ex);
        }
        //}
        try {
            session = sessionFactory.openSession();
        } catch (Exception e) {
            throw new DALException(e);
        }

    }

    private void sessionClose() throws DALException {
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
        } catch (Exception e) {
            throw new DALException(e);
        }
    }

    @Override
    public void rollback() throws DALException {
        try {
            transaction.rollback();
        } catch (Exception e) {
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
