package dal.irepositories;

import dal.dalexception.DALException;
import dal.ientites.IDALCategoryEntity;

import java.util.List;

public interface ICategoryRepository {
    public IDALCategoryEntity getCategory(int id) throws DALException;

    public List<IDALCategoryEntity> getCategorys() throws DALException;

    public List<IDALCategoryEntity> getCategorys(int page, String sort);

    public void update(IDALCategoryEntity Category) throws DALException;

    public void addCategory(IDALCategoryEntity Category) throws DALException;

    public void delete(int id) throws DALException;
}
