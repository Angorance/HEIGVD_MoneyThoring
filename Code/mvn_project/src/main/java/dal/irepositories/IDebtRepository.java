package dal.irepositories;

import dal.dalexception.DALException;
import dal.ientites.IDALDebtEntity;

import java.util.List;

public interface IDebtRepository {
    public IDALDebtEntity getDebt(int id) throws DALException;

    public List<IDALDebtEntity> getDebts() throws DALException;

    public List<IDALDebtEntity> getDebts(int page, String sort);

    public void update(IDALDebtEntity Debt) throws DALException;

    public void addDebt(IDALDebtEntity Debt) throws DALException;

    public void delete(int id) throws DALException;
}
