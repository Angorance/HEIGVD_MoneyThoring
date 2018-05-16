package dal.repositories.pgsql;

import dal.dalexception.DALException;
import dal.entities.pgsql.ClientPgEntity;
import dal.entities.pgsql.SharedbudgetPgEntity;
import dal.ientites.IDALClientEntity;
import dal.ientites.IDALSharedbudgetEntity;
import dal.irepositories.IClientRepository;
import dal.irepositories.ISharedBudgetRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * ClientPgRepository give the access methodes for handle the client into postgres persistence
 */
public class SharedBudgetPgRepository implements ISharedBudgetRepository {
    private Session session;
    private Transaction transaction;

    /**
     * Constructor of ClientPgRepository
     * @param session current session used
     * @param transaction current transaction used into the same session
     */
    public SharedBudgetPgRepository(Session session, Transaction transaction) {
        this.session = session;
        this.transaction = transaction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IDALSharedbudgetEntity getSharedbudget(int client_id, int budget_id) throws DALException {
        IDALSharedbudgetEntity sharedbudgetEntity = null;

        try {
            sharedbudgetEntity = (SharedbudgetPgEntity) session.createCriteria(SharedbudgetPgEntity.class)
                    .add(Restrictions.and(Restrictions.eq("client_id", client_id),
                            Restrictions.eq("budget_id",budget_id)))
                    .uniqueResult();
        } catch (Exception e) {
            throw new DALException(e);
        }

        return sharedbudgetEntity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IDALSharedbudgetEntity> getSharedbudgets() throws DALException {
        List<IDALSharedbudgetEntity> budgets = null;
        try {
            budgets = session.createQuery("from SharedbudgetPgEntity").list();


        } catch (Exception e) {
            throw new DALException(e);
        }
        return budgets;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(IDALSharedbudgetEntity sharedBudget) throws DALException {

        SharedbudgetPgEntity sharedBudgetPg = null;
        if (sharedBudget.getClass() == SharedbudgetPgEntity.class)
            sharedBudgetPg = (SharedbudgetPgEntity) sharedBudget;
        else
            throw new DALException();

        try {


            session.update(sharedBudgetPg);


        } catch (Exception e) {
            throw new DALException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addSharedbudget(IDALSharedbudgetEntity sharedBudget) throws DALException {
        SharedbudgetPgEntity newClient = null;
        if (sharedBudget.getClass() == SharedbudgetPgEntity.class)
            newClient = (SharedbudgetPgEntity) sharedBudget;
        else
            throw new DALException();

        try {
            session.save(newClient);
        } catch (Exception e) {
            throw new DALException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(IDALSharedbudgetEntity sharedBudget) throws DALException {
        IDALSharedbudgetEntity budget = null;
        try {
            budget = (SharedbudgetPgEntity) session.createCriteria(SharedbudgetPgEntity.class)
                    .add(Restrictions.and(Restrictions.eq("client_id", sharedBudget.getClientId()),
                            Restrictions.eq("budget_id",sharedBudget.getBudgetId())))
                    .uniqueResult();

            session.delete(budget);
        } catch (Exception e) {
            throw new DALException(e);
        }
    }
    
    @Override
    public void delete(int budget_id) throws DALException {
        
        List<IDALSharedbudgetEntity> clientsbudget = null;
        
        try {
    
            clientsbudget = session.createCriteria(SharedbudgetPgEntity.class)
                    .add(Restrictions.eq("budgetId", budget_id))
                    .list();
        
            for(IDALSharedbudgetEntity sb : clientsbudget) {
                session.delete(sb);
            }
        
        } catch (Exception e) {
            throw new DALException(e);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<IDALSharedbudgetEntity> getSharedbudgetByClient(int client_id) throws DALException {
        List<IDALSharedbudgetEntity> budgetsharedEntities = null;
        try {
            budgetsharedEntities = session.createQuery("from SharedbudgetPgEntity where clientId = :clientid").setParameter("clientid",client_id).list();
        } catch (Exception e) {
            throw new DALException(e);
        }
        return budgetsharedEntities;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IDALSharedbudgetEntity> getSharedbudgetByBudget(int budget_id) throws DALException {
        List<IDALSharedbudgetEntity> budgetsharedEntities = null;
        try {
            budgetsharedEntities = session.createQuery("from BudgetPgEntity where budgetId = :budgetid").setParameter("budgetid",budget_id).list();
        } catch (Exception e) {
            throw new DALException(e);
        }
        return budgetsharedEntities;
    }



}
