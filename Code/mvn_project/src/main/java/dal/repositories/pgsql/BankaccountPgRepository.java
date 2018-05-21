package dal.repositories.pgsql;

import dal.dalexception.DALException;
import dal.entities.pgsql.BankaccountPgEntity;
import dal.ientites.IDALBankaccountEntity;
import dal.irepositories.IBankaccountRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * BankaccountDeRepository give the access methodes for handle the bank account
 * into postgres persistence
 *
 * @author Guillaume Zaretti
 * @version 1.2
 */
public class BankaccountPgRepository implements IBankaccountRepository {
	
	private Session session;
	private Transaction transaction;
	
	/**
	 * Constructor of BankaccountDeRepository
	 *
	 * @param session current session used
	 * @param transaction current transaction used into the same session
	 */
	public BankaccountPgRepository(Session session, Transaction transaction) {
		
		this.session = session;
		this.transaction = transaction;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IDALBankaccountEntity getBankaccount(int id) throws DALException {
		
		BankaccountPgEntity bankaccount = null;
		
		try {
			bankaccount = (BankaccountPgEntity) session
					.createCriteria(BankaccountPgEntity.class)
					.add(Restrictions.eq("id", id)).uniqueResult();
		} catch (Exception e) {
			throw new DALException(e);
		}
		
		return bankaccount;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<IDALBankaccountEntity> getBankaccounts() throws DALException {
		
		List<IDALBankaccountEntity> bankaccounts = null;
		try {
			bankaccounts = session.createQuery("from BankaccountPgEntity")
					.list();
			
		} catch (Exception e) {
			throw new DALException(e);
		}
		return bankaccounts;
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
					"from BankaccountPgEntity where clientId = :clientid")
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
		
		BankaccountPgEntity bankAccountPg = null;
		if (bankaccount.getClass() == BankaccountPgEntity.class) {
			bankAccountPg = (BankaccountPgEntity) bankaccount;
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
		
		BankaccountPgEntity newBankAccount = null;
		if (bankaccount.getClass() == BankaccountPgEntity.class) {
			newBankAccount = (BankaccountPgEntity) bankaccount;
		} else {
			throw new DALException();
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
		
		IDALBankaccountEntity bankAccount = null;
		try {
			bankAccount = (BankaccountPgEntity) session
					.createCriteria(BankaccountPgEntity.class)
					.add(Restrictions.eq("id", id)).uniqueResult();
			
			session.delete(bankAccount);
		} catch (Exception e) {
			throw new DALException(e);
		}
		
	}
	
	@Override
	public IDALBankaccountEntity getDefaultBankAccountByClient(int id)
			throws DALException {
		
		BankaccountPgEntity bankaccount = null;
		
		try {
			bankaccount = (BankaccountPgEntity) session
					.createCriteria(BankaccountPgEntity.class).add(Restrictions
							.and(Restrictions.eq("clientId", id),
									Restrictions.eq("isdefault", true)))
					.uniqueResult();
			
			if (bankaccount == null) {
				bankaccount = (BankaccountPgEntity) getBankAccoutsByClient(id)
						.get(0);
			}
		} catch (Exception e) {
			throw new DALException(e);
		}
		
		return bankaccount;
	}
}
