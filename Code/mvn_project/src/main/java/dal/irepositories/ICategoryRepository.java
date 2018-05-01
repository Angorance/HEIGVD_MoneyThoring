package dal.irepositories;

import dal.dalexception.DALException;
import dal.ientites.IDALBankaccountEntity;
import dal.ientites.IDALCategoryEntity;

import java.util.List;

public interface ICategoryRepository {
    public IDALCategoryEntity getCategory(int id) throws DALException;

    public List<IDALCategoryEntity> getCategories() throws DALException;

    public List<IDALCategoryEntity> getCategoriesByClientId(int id) throws DALException;

    public void update(IDALCategoryEntity Category) throws DALException;

    public void addCategory(IDALCategoryEntity Category) throws DALException;

    public void delete(int id) throws DALException;
}
