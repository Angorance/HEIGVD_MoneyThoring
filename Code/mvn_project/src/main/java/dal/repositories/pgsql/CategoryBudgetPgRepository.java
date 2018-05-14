package dal.repositories.pgsql;

import dal.dalexception.DALException;
import dal.entities.pgsql.CategoriesbudgetPgEntity;
import dal.ientites.IDALCategoriesbudgetEntity;
import dal.irepositories.ICategoriesBudgetRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * ClientPgRepository give the access methodes for handle the client into
 * postgres persistence
 *
 * @author Guillaume Zaretti
 * @version 1.2
 */
public class CategoryBudgetPgRepository implements ICategoriesBudgetRepository {
	
	private Session session;
	private Transaction transaction;
	
	/**
	 * {@inheritDoc}
	 */
	public CategoryBudgetPgRepository(Session session,
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
			categoriesbudgetEntity = (CategoriesbudgetPgEntity) session
					.createCriteria(CategoriesbudgetPgEntity.class)
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
					.createQuery("from CategoriesbudgetPgEntity ").list();
			
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
		
		CategoriesbudgetPgEntity categoriesbudgetPgEntity = null;
		if (categoriesBudget.getClass() == CategoriesbudgetPgEntity.class) {
			categoriesbudgetPgEntity
					= (CategoriesbudgetPgEntity) categoriesBudget;
		} else {
			throw new DALException();
		}
		
		try {
			session.update(categoriesbudgetPgEntity);
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
		
		CategoriesbudgetPgEntity newCategoriesbudget = null;
		if (categoriesBudget.getClass() == CategoriesbudgetPgEntity.class) {
			newCategoriesbudget = (CategoriesbudgetPgEntity) categoriesBudget;
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
			categoriesbudget = (CategoriesbudgetPgEntity) session
					.createCriteria(CategoriesbudgetPgEntity.class)
					.add(Restrictions.and(Restrictions.eq("category_id",
							categoriesBudget.getCategoryId()), Restrictions
							.eq("budget_id", categoriesBudget.getBudgetId())))
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
					.createCriteria(CategoriesbudgetPgEntity.class)
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
					"from CategoriesbudgetPgEntity where budgetId = :budgetid")
					.setParameter("budgetid", budget_id).list();
		} catch (Exception e) {
			throw new DALException(e);
		}
		return categroriesBudgets;
	}
}
