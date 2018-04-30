package dal.repositories.derby;


import dal.dalexception.DALException;
import dal.entities.derby.CategoryDeEntity;
import dal.ientites.IDALCategoryEntity;
import dal.irepositories.ICategoryRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class CategoryDeRepository implements ICategoryRepository {

    private Session session;
    private Transaction transaction;

    public CategoryDeRepository(Session session, Transaction transaction){
        this.session = session;
        this.transaction = transaction;
    }

    public IDALCategoryEntity getCategory(int id) throws DALException {
        CategoryDeEntity Category = null;

        try {
            Category = (CategoryDeEntity) session.createCriteria(CategoryDeEntity.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();
        } catch (Exception e) {
            throw new DALException(e);
        }

        return Category;
    }


    public void addCategory(IDALCategoryEntity Category) throws DALException {

        CategoryDeEntity newCategory = null;
        if (Category.getClass() == CategoryDeEntity.class)
            newCategory = (CategoryDeEntity) Category;
        else
            throw new DALException();

        try {
            session.save(newCategory);
        } catch (Exception e) {
            throw new DALException(e);
        }
    }

    public List<IDALCategoryEntity> getCategories() throws DALException{
        List<IDALCategoryEntity> Categorys = null;
        try {
            Categorys = session.createQuery("from CategoryDeEntity").list();


        } catch (Exception e) {
            throw new DALException(e);
        }
        return Categorys;
    }


    public void update(IDALCategoryEntity Category) throws DALException{

        CategoryDeEntity CategoryPg = null;
        if (Category.getClass() == CategoryDeEntity.class)
            CategoryPg = (CategoryDeEntity) Category;
        else
            throw new DALException();

        try {


            session.update(CategoryPg);


        } catch (Exception e) {
            throw new DALException(e);
        }
    }

    public void delete(int id) throws DALException {
        IDALCategoryEntity Category = null;
        try {
            Category = (CategoryDeEntity) session.createCriteria(CategoryDeEntity.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();

            session.delete(Category);
        } catch (Exception e) {
            throw new DALException(e);
        }

    }
}
