package dal.repositories.derby;


import dal.dalexception.DALException;
import dal.entities.derby.CategoryDeEntity;
import dal.ientites.IDALCategoryEntity;
import dal.irepositories.ICategoryRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import javax.management.Query;
import java.util.List;

/**
 * CategoryDeRepository give the access methodes for handle the categories into derby persistence
 */
public class CategoryDeRepository implements ICategoryRepository {

    private Session session;
    private Transaction transaction;

    /**
     * Constructor of CategoryDeRepository
     * @param session current session used
     * @param transaction current transaction used into the same session
     */
    public CategoryDeRepository(Session session, Transaction transaction){
        this.session = session;
        this.transaction = transaction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IDALCategoryEntity getCategory(int id) throws DALException {
        CategoryDeEntity category = null;

        try {
            category = (CategoryDeEntity) session.createCriteria(CategoryDeEntity.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();
        } catch (Exception e) {
            throw new DALException(e);
        }

        return category;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer addCategory(IDALCategoryEntity category) throws DALException {

        CategoryDeEntity newCategory = null;
        if (category.getClass() == CategoryDeEntity.class)
            newCategory = (CategoryDeEntity) category;
        else
            throw new DALException();

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
    public List<IDALCategoryEntity> getCategories() throws DALException{
        List<IDALCategoryEntity> categorys = null;
        try {
            categorys = session.createQuery("from CategoryDeEntity").list();


        } catch (Exception e) {
            throw new DALException(e);
        }
        return categorys;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IDALCategoryEntity> getCategoriesByClientId(int id) throws DALException {
        List<IDALCategoryEntity> categorys = null;
        try {
            categorys = session.createQuery("from CategoryDeEntity where clientId = :clientid").setParameter("clientid",id).list();



        } catch (Exception e) {
            throw new DALException(e);
        }
        return categorys;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(IDALCategoryEntity category) throws DALException{

        CategoryDeEntity categoryPg = null;
        if (category.getClass() == CategoryDeEntity.class)
            categoryPg = (CategoryDeEntity) category;
        else
            throw new DALException();

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
    public void delete(int id) throws DALException {
        IDALCategoryEntity category = null;
        try {
            category = (CategoryDeEntity) session.createCriteria(CategoryDeEntity.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();

            session.delete(category);
        } catch (Exception e) {
            throw new DALException(e);
        }

    }
}
