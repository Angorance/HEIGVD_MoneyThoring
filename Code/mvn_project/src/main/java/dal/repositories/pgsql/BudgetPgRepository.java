package dal.repositories.pgsql;

import dal.dalexception.DALException;
import dal.entities.pgsql.BudgetPgEntity;
import dal.ientites.IDALBudgetEntity;
import dal.irepositories.IBudgetRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class BudgetPgRepository implements IBudgetRepository {
    private Session session;
    private Transaction transaction;

    public BudgetPgRepository(Session session, Transaction transaction) {
        this.session = session;
        this.transaction = transaction;
    }


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
    
    @Override
    public List<IDALBudgetEntity> getBudgetsByClient(int id) throws DALException {
        List<IDALBudgetEntity> budgetEntities = null;
        try {
            budgetEntities = session.createQuery("from BudgetPgEntity where clientId = :clientid").setParameter("clientid",id).list();
        } catch (Exception e) {
            throw new DALException(e);
        }
        return budgetEntities;
    }

    @Override
    public void update(IDALBudgetEntity Budget) throws DALException {
        BudgetPgEntity BudgetPg = null;
        if (Budget.getClass() == BudgetPgEntity.class)
            BudgetPg = (BudgetPgEntity) Budget;
        else
            throw new DALException();

        try {


            session.update(BudgetPg);


        } catch (Exception e) {
            throw new DALException(e);
        }
    }

    @Override
    public void addBudget(IDALBudgetEntity Budget) throws DALException {
        BudgetPgEntity newBudget = null;
        if (newBudget.getClass() == BudgetPgEntity.class)
            newBudget = (BudgetPgEntity) newBudget;
        else
            throw new DALException();

        try {
            session.save(newBudget);
        } catch (Exception e) {
            throw new DALException(e);
        }
    }

    @Override
    public void delete(int id) throws DALException {
        IDALBudgetEntity Budget = null;
        try {
            Budget = (BudgetPgEntity) session.createCriteria(BudgetPgEntity.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();

            session.delete(Budget);
        } catch (Exception e) {
            throw new DALException(e);
        }
    }
}
