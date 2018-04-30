package dal.orm;

import dal.dalexception.DALException;
import dal.irepositories.*;
import dal.repositories.derby.*;
import dal.repositories.pgsql.CategoryPgRepository;
import dal.repositories.pgsql.ClientPgRepository;
import dal.repositories.pgsql.DebtPgRepository;
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
    private ICategoryRepository categoryRepository;
    private IBudgetRepository budgetRepository;
    private IDebtRepository debtRepository;

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

    @Override
    public ICategoryRepository getCategoryRepository() {
        categoryRepository =  new CategoryDeRepository(session, transaction);
        return categoryRepository;
    }

    /**
     * Construct an single instance of budgetrepostiory and return it
     *
     * @return an instance of IBudgetRepository
     */
    @Override
    public IBudgetRepository getBudgetRepository() {
        budgetRepository = new BudgetDeRepository(session, transaction);
        return budgetRepository;
    }

    /**
     * Construct an single instance of dbbtRepository and return it
     *
     * @return an instance of IDebtRepository
     */
    @Override
    public IDebtRepository getDebtRepository() {
        debtRepository = new DebtDeRepository(session, transaction);
        return debtRepository;
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
