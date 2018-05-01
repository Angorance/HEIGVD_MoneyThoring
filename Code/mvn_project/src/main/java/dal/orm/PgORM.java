package dal.orm;

import dal.dalexception.DALException;
import dal.irepositories.*;
import dal.repositories.derby.DebtDeRepository;
import dal.repositories.derby.RecurrenceDeRepository;
import dal.repositories.pgsql.*;
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
    private IBudgetRepository budgetRepository;
    private IDebtRepository debtRepository;
    private IIotransactionRepository iotransactionRepository;
    private IRecurrenceRepository recurrenceRepository;

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
        categoryRepository = new CategoryPgRepository(session, transaction);
        return categoryRepository;
    }

    /**
     * Construct an single instance of budgetrepostiory and return it
     *
     * @return an instance of IBudgetRepository
     */
    @Override
    public IBudgetRepository getBudgetRepository() {
        budgetRepository = new BudgetPgRepository(session, transaction);
        return budgetRepository;

    }

    /**
     * Construct an single instance of dbbtRepository and return it
     *
     * @return an instance of IDebtRepository
     */
    @Override
    public IDebtRepository getDebtRepository() {
        debtRepository = new DebtPgRepository(session, transaction);
        return debtRepository;

    }

    /**
     * Construct an single instance of recurrenceRepository and return it
     *
     * @return an instance of IRecurrenceRepository
     */
    @Override
    public IRecurrenceRepository getRecurrenceRepository() {
        recurrenceRepository = new RecurrencePgRepository(session, transaction);
        return recurrenceRepository;
    }

    /**
     * begin a transaction shared bitween all IIotransactionRepository
     *
     * @throws DALException
     */
    @Override
    public IIotransactionRepository getIotransactionRepository() {
        iotransactionRepository = new IotransactionPgRepository(session, transaction);
        return iotransactionRepository;
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
