package dal.repositories.pgsql;

import dal.dalexception.DALException;
import dal.entities.pgsql.CategoryPgEntity;
import dal.entities.pgsql.CategoryPgEntity;
import dal.ientites.IDALCategoryEntity;
import dal.ientites.IDALCategoryEntity;
import dal.irepositories.ICategoryRepository;
import dal.irepositories.ICategoryRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class CategoryPgRepository implements ICategoryRepository {
    private Session session;
    private Transaction transaction;
    public CategoryPgRepository(Session session, Transaction transaction) {
        this.session = session;
        this.transaction = transaction;
    }


    @Override
    public IDALCategoryEntity getCategory(int id) throws DALException {
        CategoryPgEntity Category = null;

        try {
            Category = (CategoryPgEntity) session.createCriteria(CategoryPgEntity.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();
        } catch (Exception e) {
            throw new DALException(e);
        }

        return Category;
    }

    @Override
    public List<IDALCategoryEntity> getCategories() throws DALException {

        List<IDALCategoryEntity> Categorys = null;
        try {
            Categorys = session.createQuery("from CategoryPgEntity").list();


        } catch (Exception e) {
            throw new DALException(e);
        }
        return Categorys;
    }

    @Override
    public List<IDALCategoryEntity> getCategoriesByClientId(int id) throws DALException {
        List<IDALCategoryEntity> Categorys = null;
        try {
            Categorys = session.createQuery("from CategoryPgEntity where clientId = :clientid").setParameter("clientid",id).list();



        } catch (Exception e) {
            throw new DALException(e);
        }
        return Categorys;
    }


    @Override
    public void update(IDALCategoryEntity Category) throws DALException {
        CategoryPgEntity CategoryPg = null;
        if (Category.getClass() == CategoryPgEntity.class)
            CategoryPg = (CategoryPgEntity) Category;
        else
            throw new DALException();

        try {


            session.update(CategoryPg);


        } catch (Exception e) {
            throw new DALException(e);
        }
    }

    @Override
    public void addCategory(IDALCategoryEntity Category) throws DALException {
        CategoryPgEntity newCategory = null;
        if (newCategory.getClass() == CategoryPgEntity.class)
            newCategory = (CategoryPgEntity) newCategory;
        else
            throw new DALException();

        try {
            session.save(newCategory);
        } catch (Exception e) {
            throw new DALException(e);
        }
    }

    @Override
    public void delete(int id) throws DALException {
        IDALCategoryEntity Category = null;
        try {
            Category = (CategoryPgEntity) session.createCriteria(CategoryPgEntity.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();

            session.delete(Category);
        } catch (Exception e) {
            throw new DALException(e);
        }
    }
}
