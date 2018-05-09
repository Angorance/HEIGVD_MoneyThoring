package dal.irepositories;

import dal.dalexception.DALException;
import dal.ientites.IDALIotransactionEntity;

import java.util.List;

/**
 * IIotransactionRepository give the access methodes for handle the iotransaction into persistence
 */
public interface IIotransactionRepository {
    /**
     * Retreave a iotransaction by id
     *
     * @param id of the iotransaction
     * @return IDALIotransactionEntity the iotransaction
     * @throws DALException
     */
    public IDALIotransactionEntity getIotransaction(int id) throws DALException;

    /**
     * Retreave all iotransactions
     *
     * @return List<IDALIotransactionEntity> the list of iotransaction
     * @throws DALException
     */
    public List<IDALIotransactionEntity> getIotransactions() throws DALException;

    /**
     * Update otransaction
     *
     * @param otransaction the debt would'you update
     * @throws DALException
     */
    public void update(IDALIotransactionEntity otransaction) throws DALException;

    /**
     * add otransaction
     *
     * @param otransaction the otransaction would'you add
     * @throws DALException
     */
    public Integer addIotransaction(IDALIotransactionEntity otransaction) throws DALException;

    /**
     * delete otransaction by id
     *
     * @param id the otransaction would'you delete
     * @throws DALException
     */
    public void delete(int id) throws DALException;

    /**
     * Retreave all iotransactions by bankaccount id
     *
     * @return List<IDALIotransactionEntity> the list of iotransaction by bankaccout
     * @throws DALException
     */
    public List<IDALIotransactionEntity> getIotransactionsByBankaccount(int bankaccountId) throws DALException;
}
