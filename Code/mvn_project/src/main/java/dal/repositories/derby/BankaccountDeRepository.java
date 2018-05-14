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

/**
 * BankaccountDeRepository give the access methodes for handle the bank account
 * into derby persistence
 */
public class BankaccountDeRepository implements IBankaccountRepository {
	
	private Session session;
	private Transaction transaction;
	
	/**
	 * Constructor of BankaccountDeRepository
	 *
	 * @param session current session used
	 * @param transaction current transaction used into the same session
	 */
	public BankaccountDeRepository(Session session, Transaction transaction) {
		
		this.session = session;
		this.transaction = transaction;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IDALBankaccountEntity getBankaccount(int id) throws DALException {
		
		BankaccountDeEntity bankAccount = null;
		
		try {
			bankAccount = (BankaccountDeEntity) session
					.createCriteria(BankaccountDeEntity.class)
					.add(Restrictions.eq("id", id)).uniqueResult();
		} catch (Exception e) {
			throw new DALException(e);
		}
		
		return bankAccount;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<IDALBankaccountEntity> getBankaccounts() throws DALException {
		
		List<IDALBankaccountEntity> bankAccounts = null;
		try {
			bankAccounts = session.createQuery("from BankaccountDeEntity")
					.list();
			
		} catch (Exception e) {
			throw new DALException(e);
		}
		return bankAccounts;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<IDALBankaccountEntity> getBankAccoutsByClient(int id)
			throws DALException {
		
		List<IDALBankaccountEntity> bankaccountEntities = null;
		try {
			bankaccountEntities = session.createQuery(
					"from BankaccountDeEntity where clientId = :clientid")
					.setParameter("clientid", id).list();
		} catch (Exception e) {
			throw new DALException(e);
		}
		return bankaccountEntities;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(IDALBankaccountEntity bankaccount) throws DALException {
		
		BankaccountDeEntity bankAccountPg = null;
		if (bankaccount.getClass() == BankaccountDeEntity.class) {
			bankAccountPg = (BankaccountDeEntity) bankaccount;
		} else {
			throw new DALException();
		}
		
		try {
			
			
			session.update(bankAccountPg);
			
		} catch (Exception e) {
			throw new DALException(e);
		}
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer addBankaccount(IDALBankaccountEntity bankaccount)
			throws DALException {
		
		BankaccountDeEntity newBankAccount = null;
		
		if (bankaccount.getClass() == BankaccountDeEntity.class) {
			newBankAccount = (BankaccountDeEntity) bankaccount;
		} else {
			throw new DALException("Entity class doesn't match");
		}
		
		try {
			return (Integer) session.save(newBankAccount);
		} catch (Exception e) {
			throw new DALException(e);
		}
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(int id) throws DALException {
		
		IDALBankaccountEntity bankaccount = null;
		try {
			bankaccount = (BankaccountDeEntity) session
					.createCriteria(BankaccountDeEntity.class)
					.add(Restrictions.eq("id", id)).uniqueResult();
			
			session.delete(bankaccount);
		} catch (Exception e) {
			throw new DALException(e);
		}
		
	}
}
