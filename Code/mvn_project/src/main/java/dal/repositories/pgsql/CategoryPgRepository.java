package dal.repositories.pgsql;

import dal.dalexception.DALException;
import dal.entities.pgsql.CategoryPgEntity;
import dal.ientites.IDALCategoryEntity;
import dal.irepositories.ICategoryRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * CategoryPgRepository give the access methodes for handle the category into
 * postgres persistence
 *
 * @author Guillaume Zaretti
 * @version 1.2
 */
public class CategoryPgRepository implements ICategoryRepository {
	
	private Session session;
	private Transaction transaction;
	
	
	/**
	 * Constructor of CategoryPgRepository
	 *
	 * @param session current session used
	 * @param transaction current transaction used into the same session
	 */
	public CategoryPgRepository(Session session, Transaction transaction) {
		
		this.session = session;
		this.transaction = transaction;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IDALCategoryEntity getCategory(int id) throws DALException {
		
		CategoryPgEntity category = null;
		
		try {
			category = (CategoryPgEntity) session
					.createCriteria(CategoryPgEntity.class)
					.add(Restrictions.eq("id", id)).uniqueResult();
		} catch (Exception e) {
			throw new DALException(e);
		}
		
		return category;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<IDALCategoryEntity> getCategories() throws DALException {
		
		List<IDALCategoryEntity> categorys = null;
		try {
			categorys = session.createQuery("from CategoryPgEntity").list();
			
		} catch (Exception e) {
			throw new DALException(e);
		}
		return categorys;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<IDALCategoryEntity> getCategoriesByClientId(int id)
			throws DALException {
		
		List<IDALCategoryEntity> categorys = null;
		try {
			categorys = session.createQuery(
					"from CategoryPgEntity where clientId = :clientid")
					.setParameter("clientid", id).list();
			
		} catch (Exception e) {
			throw new DALException(e);
		}
		return categorys;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(IDALCategoryEntity Category) throws DALException {
		
		CategoryPgEntity categoryPg = null;
		if (Category.getClass() == CategoryPgEntity.class) {
			categoryPg = (CategoryPgEntity) Category;
		} else {
			throw new DALException();
		}
		
		try {
			
			
			session.update(categoryPg);
			
		} catch (Exception e) {
			throw new DALException(e);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer addCategory(IDALCategoryEntity Category)
			throws DALException {
		
		CategoryPgEntity newCategory = null;
		
		if (Category.getClass() == CategoryPgEntity.class) {
			newCategory = (CategoryPgEntity) Category;
		} else {
			throw new DALException();
		}
		
		try {
			return (Integer) session.save(newCategory);
		} catch (Exception e) {
			throw new DALException(e);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(int id) throws DALException {
		
		IDALCategoryEntity category = null;
		try {
			category = (CategoryPgEntity) session
					.createCriteria(CategoryPgEntity.class)
					.add(Restrictions.eq("id", id)).uniqueResult();
			
			session.delete(category);
		} catch (Exception e) {
			throw new DALException(e);
		}
	}
}
