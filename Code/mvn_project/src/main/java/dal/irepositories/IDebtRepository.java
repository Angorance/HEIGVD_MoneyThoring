package dal.irepositories;

import dal.dalexception.DALException;
import dal.ientites.IDALDebtEntity;

import java.util.List;

/**
 * IDebtRepository give the access methodes for handle the debt into persistence
 */
public interface IDebtRepository {

    /**
     * Retreave a dbt by dbt id
     *
     * @param id of the dbt
     * @return IDALDebtEntity the debt
     * @throws DALException
     */
    public IDALDebtEntity getDebt(int id) throws DALException;

    /**
     * Retreave all debts
     *
     * @return List<IDALDebtEntity> the list of debts
     * @throws DALException
     */
    public List<IDALDebtEntity> getDebts() throws DALException;

    /**
     * Update debt
     *
     * @param debt the debt would'you update
     * @throws DALException
     */
    public void update(IDALDebtEntity debt) throws DALException;

    /**
     * Add add debt
     *
     * @param debt the debt would'you add
     * @throws DALException
     */
    public void addDebt(IDALDebtEntity debt) throws DALException;

    /**
     * delete debt by id
     * @param id of the debt
     * @throws DALException
     */
    public void delete(int id) throws DALException;
}
