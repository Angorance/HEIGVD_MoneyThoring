package dal.orm;

import dal.dalexception.DALException;
import dal.irepositories.IBankaccountRepository;
import dal.irepositories.ICategoryRepository;
import dal.irepositories.IClientRepository;
import dal.repositories.pgsql.BankaccountPgRepository;
import dal.repositories.pgsql.CategoryPgRepository;
import dal.repositories.pgsql.ClientPgRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Assert;

public class PgORM implements IORM {

    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    private IClientRepository clientRepository;
    private IBankaccountRepository bankaccountRepository;
    private ICategoryRepository categoryRepository;

    @Override
    public IClientRepository getClientRepository() {
        //if (clientRepository == null) {
            clientRepository = new ClientPgRepository(session, transaction);
        // }
        return clientRepository;
    }


    @Override
    public IBankaccountRepository getBankaccountRepository() {
        //if (bankaccountRepository == null) {
            bankaccountRepository = new BankaccountPgRepository(session, transaction);
       // }
        return bankaccountRepository;
    }

    /**
     * Construct an single instance of categoryrepostiory and return it
     *
     * @return an instance of ICategoryRepository
     */
    @Override
    public ICategoryRepository getCategoryRepository() {
        return new CategoryPgRepository(session, transaction);
    }

    private void openSession() throws DALException {
        try {
            sessionFactory = new Configuration().configure("hibernate.pgsql.cfg.xml").buildSessionFactory();
        } catch (Exception e) {
            throw new DALException(e);
        }
        session = sessionFactory.openSession();

    }

    private void sessionClose() throws DALException {
        try {
            session.close();
            sessionFactory.close();
        } catch (HibernateException e) {
            throw new DALException(e);
        }
    }

    @Override
    public void beginTransaction() throws DALException {
        try {
            openSession();
            transaction = session.beginTransaction();
            System.out.println("Session du begin transaction " + session);

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
            sessionClose();
        } catch (Exception e) {
            throw new DALException(e);
        }

    }
}
