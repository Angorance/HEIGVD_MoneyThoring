package dal.repositories.derby;


import dal.dalexception.DALException;
import dal.entities.derby.BudgetDeEntity;
import dal.ientites.IDALBudgetEntity;
import dal.irepositories.IBudgetRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * BudgetDeRepository give the access methodes for handle the budget into derby persistence
 */
public class BudgetDeRepository implements IBudgetRepository {

    private Session session;
    private Transaction transaction;

    /**
     * Constructor of BudgetDeRepository
     * @param session current session used
     * @param transaction current transaction used into the same session
     */
    public BudgetDeRepository(Session session, Transaction transaction) {
        this.session = session;
        this.transaction = transaction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IDALBudgetEntity getBudget(int id) throws DALException {
        BudgetDeEntity budget = null;

        try {
            budget = (BudgetDeEntity) session.createCriteria(BudgetDeEntity.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();
        } catch (Exception e) {
            throw new DALException(e);
        }

        return budget;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Integer addBudget(IDALBudgetEntity budget) throws DALException {

        BudgetDeEntity newBudget = (BudgetDeEntity) budget;
        if (budget.getClass() == BudgetDeEntity.class)
            newBudget = (BudgetDeEntity) budget;
        else
            throw new DALException();

        try {
            return (Integer) session.save(newBudget);
        } catch (Exception e) {
            throw new DALException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IDALBudgetEntity> getBudgets() throws DALException {
        List<IDALBudgetEntity> budgets = null;
        try {
            budgets = session.createQuery("from BudgetDeEntity").list();


        } catch (Exception e) {
            throw new DALException(e);
        }
        return budgets;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IDALBudgetEntity> getBudgetsByClient(int id) throws DALException {
    
        List<IDALBudgetEntity> budgetEntities = null;
        try {
            budgetEntities = session.createQuery("from BudgetDeEntity where clientId = :clientid").setParameter("clientid",id).list();
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

        BudgetDeEntity budgetPg = null;
        if (budget.getClass() == BudgetDeEntity.class)
            budgetPg = (BudgetDeEntity) budget;
        else
            throw new DALException();

        try {


            session.update(budgetPg);


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
            budget = (BudgetDeEntity) session.createCriteria(BudgetDeEntity.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();

            session.delete(budget);
        } catch (Exception e) {
            throw new DALException(e);
        }

    }
}
