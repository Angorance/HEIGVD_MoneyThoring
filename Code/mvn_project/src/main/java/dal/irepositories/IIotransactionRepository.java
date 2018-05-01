package dal.irepositories;

import dal.dalexception.DALException;
import dal.ientites.IDALIotransactionEntity;

import java.util.List;

public interface IIotransactionRepository {
    public IDALIotransactionEntity getIotransaction(int id) throws DALException;

    public List<IDALIotransactionEntity> getIotransactions() throws DALException;

    public void update(IDALIotransactionEntity otransaction) throws DALException;

    public void addIotransaction(IDALIotransactionEntity otransaction) throws DALException;

    public void delete(int id) throws DALException;
}
