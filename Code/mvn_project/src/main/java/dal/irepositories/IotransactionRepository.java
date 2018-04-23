package dal.irepositories;

import dal.dalexception.DALException;
import dal.ientites.IDALotransactionEntity;

import java.util.List;

public interface IotransactionRepository {
    public IDALotransactionEntity getotransaction(int id) throws DALException;

    public List<IDALotransactionEntity> getotransactions() throws DALException;

    public List<IDALotransactionEntity> getotransactions(int page, String sort);

    public void update(IDALotransactionEntity otransaction) throws DALException;

    public void addotransaction(IDALotransactionEntity otransaction) throws DALException;

    public void delete(int id) throws DALException;
}
