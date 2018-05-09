package dal.irepositories;

import dal.dalexception.DALException;
import dal.ientites.IDALBankaccountEntity;
import dal.ientites.IDALCategoryEntity;

import java.util.List;

/**
 * ICategoryRepository give the access methodes for handle the category into persistence
 */
public interface ICategoryRepository {

    /**
     * Retreave a budget by id
     *
     * @param id of the category
     * @return IDALCategoryEntity the categroy
     * @throws DALException
     */
    public IDALCategoryEntity getCategory(int id) throws DALException;

    /**
     * Retreave all categories
     *
     * @return List<IDALCategoryEntity> the list of categories
     * @throws DALException
     */
    public List<IDALCategoryEntity> getCategories() throws DALException;

    /**
     * Retreave all categories by c lient id
     *
     * @param id of the category
     * @return List<IDALCategoryEntity> the list of categories by client id
     * @throws DALException
     */
    public List<IDALCategoryEntity> getCategoriesByClientId(int id) throws DALException;

    /**
     * Update category
     *
     * @param category the category would'you update
     * @throws DALException
     */
    public void update(IDALCategoryEntity category) throws DALException;

    /**
     * Add addCategory
     *
     * @param category the category would'you add
     * @throws DALException
     */
    public Integer addCategory(IDALCategoryEntity category) throws DALException;

    /**
     * Delete addCategory
     *
     * @param id the category id would'you delete
     * @throws DALException
     */
    public void delete(int id) throws DALException;
}
