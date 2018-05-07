package dal.repositories.pgsql;

import dal.dalexception.DALException;
import dal.entities.pgsql.BudgetPgEntity;
import dal.ientites.IDALBudgetEntity;
import dal.irepositories.IBudgetRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * BudgetDeRepository give the access methodes for handle the budget into postgres persistence
 */
public class BudgetPgRepository implements IBudgetRepository {
    private Session session;
    private Transaction transaction;

    public BudgetPgRepository(Session session, Transaction transaction) {
        this.session = session;
        this.transaction = transaction;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public IDALBudgetEntity getBudget(int id) throws DALException {
        BudgetPgEntity Budget = null;

        try {
            Budget = (BudgetPgEntity) session.createCriteria(BudgetPgEntity.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();
        } catch (Exception e) {
            throw new DALException(e);
        }

        return Budget;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<IDALBudgetEntity> getBudgets() throws DALException {

        List<IDALBudgetEntity> Budgets = null;
        try {
            Budgets = session.createQuery("from BudgetPgEntity").list();


        } catch (Exception e) {
            throw new DALException(e);
        }
        return Budgets;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IDALBudgetEntity> getBudgetsByClient(int id) throws DALException {
        List<IDALBudgetEntity> budgetEntities = null;
        try {
            budgetEntities = session.createQuery("from BudgetPgEntity where clientId = :clientid").setParameter("clientid", id).list();
        } catch (Exception e) {
            throw new DALException(e);
        }
        return budgetEntities;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(IDALBudgetEntity budget) throws DALException {
        BudgetPgEntity BudgetPg = null;
        if (budget.getClass() == BudgetPgEntity.class)
            BudgetPg = (BudgetPgEntity) budget;
        else
            throw new DALException();

        try {


            session.update(BudgetPg);


        } catch (Exception e) {
            throw new DALException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addBudget(IDALBudgetEntity budget) throws DALException {
        BudgetPgEntity newBudget = null;
        if (newBudget.getClass() == BudgetPgEntity.class)
            newBudget = (BudgetPgEntity) budget;
        else
            throw new DALException();

        try {
            session.save(newBudget);
        } catch (Exception e) {
            throw new DALException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(int id) throws DALException {
        IDALBudgetEntity budget = null;
        try {
            budget = (BudgetPgEntity) session.createCriteria(BudgetPgEntity.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();

            session.delete(budget);
        } catch (Exception e) {
            throw new DALException(e);
        }
    }
}
