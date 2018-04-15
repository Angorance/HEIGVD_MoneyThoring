package dal.irepositories;

import dal.dalexception.DALException;
import dal.ientites.IDALBankaccountEntity;

import java.util.Collection;
import java.util.List;

public interface IBankaccountRepository {

    public IDALBankaccountEntity getBankaccount(int id) throws DALException;

    public List<IDALBankaccountEntity> getBankaccounts() throws DALException;

    public List<IDALBankaccountEntity> getBankaccounts(int page, String sort);

    public void update(IDALBankaccountEntity bankaccount) throws DALException;

    public void addBankaccount(IDALBankaccountEntity bankaccount) throws DALException;

    public void delete(int id) throws DALException;

}
