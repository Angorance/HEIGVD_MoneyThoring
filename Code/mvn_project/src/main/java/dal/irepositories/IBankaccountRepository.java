package dal.irepositories;

import dal.dalexception.DALException;
import dal.ientites.IDALBankaccountEntity;

import java.util.Collection;
import java.util.List;

public interface IBankaccountRepository {

    /**
     *
     * @param id
     * @return
     * @throws DALException
     */
    public IDALBankaccountEntity getBankaccount(int id) throws DALException;

    public List<IDALBankaccountEntity> getBankaccounts() throws DALException;

    public void update(IDALBankaccountEntity bankaccount) throws DALException;

    public void addBankaccount(IDALBankaccountEntity bankaccount) throws DALException;

    public void delete(int id) throws DALException;

}
