package dal.repositories.derby;

import dal.dalexception.DALException;
import dal.entities.derby.BankaccountDeEntity;
import dal.ientites.IDALBankaccountEntity;
import dal.irepositories.IBankaccountRepository;
import dal.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class BankaccountDeRepository implements IBankaccountRepository {

    private Session session;
    private Transaction transaction;

    public  BankaccountDeRepository(Session session, Transaction transaction){
        this.session = session;
        this.transaction = transaction;
    }



    @Override
    public IDALBankaccountEntity getBankaccount(int id) throws DALException {
        BankaccountDeEntity BankAccount = null;

        try {
            BankAccount = (BankaccountDeEntity) session.createCriteria(BankaccountDeEntity.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();
        } catch (Exception e) {
            throw new DALException(e);
        }

        return BankAccount;
    }

    @Override
    public List<IDALBankaccountEntity> getBankaccounts() throws DALException {
        List<IDALBankaccountEntity> BankAccounts = null;
        try {
            BankAccounts = session.createQuery("from BankaccountDeEntity").list();


        } catch (Exception e) {
            throw new DALException(e);
        }
        return BankAccounts;
    }

    @Override
    public List<IDALBankaccountEntity> getBankAccoutsByClient(int id) throws DALException {
        List<IDALBankaccountEntity> bankaccountEntities = null;
        try {
            bankaccountEntities = session.createQuery("from BankaccountDeEntity where clientId = :clientid").setParameter("clientid",id).list();
        } catch (Exception e) {
            throw new DALException(e);
        }
        return bankaccountEntities;
    }

    @Override
    public void update(IDALBankaccountEntity bankaccount) throws DALException {

        BankaccountDeEntity BankAccountPg = null;
        if (bankaccount.getClass() == BankaccountDeEntity.class)
            BankAccountPg = (BankaccountDeEntity) bankaccount;
        else
            throw new DALException();

        try {


            session.update(BankAccountPg);


        } catch (Exception e) {
            throw new DALException(e);
        }

    }

    @Override
    public void addBankaccount(IDALBankaccountEntity bankaccount) throws DALException {
        BankaccountDeEntity newBankAccount = null;
        if (bankaccount.getClass() == BankaccountDeEntity.class)
            newBankAccount = (BankaccountDeEntity) bankaccount;
        else
            throw new DALException();

        try {
            session.save(newBankAccount);
        } catch (Exception e) {
            throw new DALException(e);
        }

    }

    @Override
    public void delete(int id) throws DALException {
        IDALBankaccountEntity BankAccount = null;
        try {
            BankAccount = (BankaccountDeEntity) session.createCriteria(BankaccountDeEntity.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();

            session.delete(BankAccount);
        } catch (Exception e) {
            throw new DALException(e);
        }

    }
}
