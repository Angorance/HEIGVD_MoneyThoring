package dal.repositories.derby;

import dal.dalexception.DALException;
import dal.entities.derby.CategoriesbudgetDeEntity;
import dal.ientites.IDALCategoriesbudgetEntity;
import dal.irepositories.ICategoriesBudgetRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * ClientDeRepository give the access methodes for handle the client into
 * postgres persistence
 *
 * @author Guillaume Zaretti
 * @version 1.2
 */
public class CategoryBudgetDeRepository implements ICategoriesBudgetRepository {

    private Session session;
    private Transaction transaction;

    /**
     * {@inheritDoc}
     */
    public CategoryBudgetDeRepository(Session session,
                                      Transaction transaction) {

        this.session = session;
        this.transaction = transaction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IDALCategoriesbudgetEntity getCategoriesBudget(int budget_id,
                                                          int category_id) throws DALException {
    
        IDALCategoriesbudgetEntity categoriesbudgetEntity = null;
    
        try {
            categoriesbudgetEntity = (CategoriesbudgetDeEntity) session
                    .createCriteria(CategoriesbudgetDeEntity.class)
                    .add(Restrictions
                            .and(Restrictions.eq("category_id", category_id),
                                    Restrictions.eq("budget_id", budget_id)))
                    .uniqueResult();
        } catch (Exception e) {
            throw new DALException(e);
        }
    
        return categoriesbudgetEntity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IDALCategoriesbudgetEntity> getCategoriesBudgets()
            throws DALException {
    
        List<IDALCategoriesbudgetEntity> categoriesbudgetEntities = null;
        try {
            categoriesbudgetEntities = session
                    .createQuery("from CategoriesbudgetDeEntity ").list();
        
        } catch (Exception e) {
            throw new DALException(e);
        }
        return categoriesbudgetEntities;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(IDALCategoriesbudgetEntity categoriesBudget)
            throws DALException {
    
        CategoriesbudgetDeEntity categoriesbudgetDeEntity = null;
        if (categoriesBudget.getClass() == CategoriesbudgetDeEntity.class) {
            categoriesbudgetDeEntity
                    = (CategoriesbudgetDeEntity) categoriesBudget;
        } else {
            throw new DALException();
        }
    
        try {
            session.update(categoriesbudgetDeEntity);
        } catch (Exception e) {
            throw new DALException(e);
        } categoriesbudgetDeEntity = null;
        if (categoriesBudget.getClass() == CategoriesbudgetDeEntity.class) {
            categoriesbudgetDeEntity
                    = (CategoriesbudgetDeEntity) categoriesBudget;
        } else {
            throw new DALException();
        }
    
        try {
            session.update(categoriesbudgetDeEntity);
        } catch (Exception e) {
            throw new DALException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCategoriesBudget(IDALCategoriesbudgetEntity categoriesBudget)
            throws DALException {
    
        CategoriesbudgetDeEntity newCategoriesbudget = null;
        if (categoriesBudget.getClass() == CategoriesbudgetDeEntity.class) {
            newCategoriesbudget = (CategoriesbudgetDeEntity) categoriesBudget;
        } else {
            throw new DALException();
        }
    
        try {
            session.save(newCategoriesbudget);
        } catch (Exception e) {
            throw new DALException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(IDALCategoriesbudgetEntity categoriesBudget)
            throws DALException {
    
        IDALCategoriesbudgetEntity categoriesbudget = null;
        try {
            categoriesbudget = (CategoriesbudgetDeEntity) session
                    .createCriteria(CategoriesbudgetDeEntity.class)
                    .add(Restrictions.and(Restrictions.eq("categoryId",
                            categoriesBudget.getCategoryId()), Restrictions
                            .eq("budgetId", categoriesBudget.getBudgetId())))
                    .uniqueResult();
        
            session.delete(categoriesbudget);
        } catch (Exception e) {
            throw new DALException(e);
        }
    }

    @Override
    public void delete(int budget_id) throws DALException {
    
        List<IDALCategoriesbudgetEntity> categoriesbudget = null;
        try {
        
            categoriesbudget = session
                    .createCriteria(CategoriesbudgetDeEntity.class)
                    .add(Restrictions.eq("budgetId", budget_id)).list();
        
            for (IDALCategoriesbudgetEntity cat : categoriesbudget) {
                session.delete(cat);
            }
        
        } catch (Exception e) {
            throw new DALException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IDALCategoriesbudgetEntity> getCategoriesBudgetByBudget(
            int budget_id) throws DALException {
    
        List<IDALCategoriesbudgetEntity> categroriesBudgets = null;
        try {
            categroriesBudgets = session.createQuery(
                    "from CategoriesbudgetDeEntity where budgetId = :budgetid")
                    .setParameter("budgetid", budget_id).list();
        } catch (Exception e) {
            throw new DALException(e);
        }
        return categroriesBudgets;
    }
    
    @Override
    public List<IDALCategoriesbudgetEntity> getCategoriesBudgetByCategory(
            int category_id) throws DALException {
    
        List<IDALCategoriesbudgetEntity> categroriesBudgets = null;
        try {
            categroriesBudgets = session.createQuery(
                    "from CategoriesbudgetDeEntity where categoryId = :categoryid")
                    .setParameter("categoryid", category_id).list();
        } catch (Exception e) {
            throw new DALException(e);
        }
        return categroriesBudgets;
    }
}
