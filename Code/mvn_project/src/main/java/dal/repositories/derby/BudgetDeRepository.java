package dal.repositories.derby;


import dal.dalexception.DALException;
import dal.entities.derby.BudgetDeEntity;
import dal.ientites.IDALBudgetEntity;
import dal.irepositories.IBudgetRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class BudgetDeRepository implements IBudgetRepository {

    private Session session;
    private Transaction transaction;

    public BudgetDeRepository(Session session, Transaction transaction) {
        this.session = session;
        this.transaction = transaction;
    }

    public IDALBudgetEntity getBudget(int id) throws DALException {
        BudgetDeEntity Budget = null;

        try {
            Budget = (BudgetDeEntity) session.createCriteria(BudgetDeEntity.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();
        } catch (Exception e) {
            throw new DALException(e);
        }

        return Budget;
    }


    public void addBudget(IDALBudgetEntity Budget) throws DALException {

        BudgetDeEntity newBudget = null;
        if (Budget.getClass() == BudgetDeEntity.class)
            newBudget = (BudgetDeEntity) Budget;
        else
            throw new DALException();

        try {
            session.save(newBudget);
        } catch (Exception e) {
            throw new DALException(e);
        }
    }

    public List<IDALBudgetEntity> getBudgets() throws DALException {
        List<IDALBudgetEntity> Budgets = null;
        try {
            Budgets = session.createQuery("from BudgetDeEntity").list();


        } catch (Exception e) {
            throw new DALException(e);
        }
        return Budgets;
    }


    public void update(IDALBudgetEntity Budget) throws DALException {

        BudgetDeEntity BudgetPg = null;
        if (Budget.getClass() == BudgetDeEntity.class)
            BudgetPg = (BudgetDeEntity) Budget;
        else
            throw new DALException();

        try {


            session.update(BudgetPg);


        } catch (Exception e) {
            throw new DALException(e);
        }
    }

    public void delete(int id) throws DALException {
        IDALBudgetEntity Budget = null;
        try {
            Budget = (BudgetDeEntity) session.createCriteria(BudgetDeEntity.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();

            session.delete(Budget);
        } catch (Exception e) {
            throw new DALException(e);
        }

    }
}
