package dal.repositories.pgsql;

import dal.dalexception.DALException;
import dal.entities.pgsql.IotransactionPgEntity;
import dal.ientites.IDALIotransactionEntity;
import dal.irepositories.IIotransactionRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * TODO
 */
public class IotransactionPgRepository implements IIotransactionRepository {
	
	private Session session;
	private Transaction transaction;
	
	/**
	 * Constructor of IotransactionPgRepository
	 *
	 * @param session current session used
	 * @param transaction current transaction used into the same session
	 */
	public IotransactionPgRepository(Session session, Transaction transaction) {
		
		this.session = session;
		this.transaction = transaction;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IDALIotransactionEntity getIotransaction(int id)
			throws DALException {
		
		IotransactionPgEntity iotransaction = null;
		
		try {
			iotransaction = (IotransactionPgEntity) session
					.createCriteria(IotransactionPgEntity.class)
					.add(Restrictions.eq("id", id)).uniqueResult();
		} catch (Exception e) {
			throw new DALException(e);
		}
		
		return iotransaction;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<IDALIotransactionEntity> getIotransactions()
			throws DALException {
		
		List<IDALIotransactionEntity> iotransaction = null;
		try {
			iotransaction = session.createQuery("from IotransactionPgEntity")
					.list();
			
		} catch (Exception e) {
			throw new DALException(e);
		}
		return iotransaction;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer addIotransaction(IDALIotransactionEntity iotransaction)
			throws DALException {
		
		IotransactionPgEntity newIotransaction = null;
		if (iotransaction.getClass() == IotransactionPgEntity.class) {
			newIotransaction = (IotransactionPgEntity) iotransaction;
		} else {
			throw new DALException();
		}
		
		try {
			return (Integer) session.save(newIotransaction);
		} catch (Exception e) {
			throw new DALException(e);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(IDALIotransactionEntity iotransaction)
			throws DALException {
		
		IotransactionPgEntity iotransactionDe = null;
		if (iotransaction.getClass() == IotransactionPgEntity.class) {
			iotransactionDe = (IotransactionPgEntity) iotransaction;
		} else {
			throw new DALException();
		}
		
		try {
			
			
			session.update(iotransactionDe);
			
		} catch (Exception e) {
			throw new DALException(e);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(int id) throws DALException {
		
		IDALIotransactionEntity iotransaction = null;
		try {
			iotransaction = (IotransactionPgEntity) session
					.createCriteria(IotransactionPgEntity.class)
					.add(Restrictions.eq("id", id)).uniqueResult();
			session.delete(iotransaction);
		} catch (Exception e) {
			throw new DALException(e);
		}
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<IDALIotransactionEntity> getIotransactionsByBankaccount(
			int bankaccountId) throws DALException {
		
		List<IDALIotransactionEntity> iotransaction = null;
		
		try {
			iotransaction = session.createQuery(
					"from IotransactionPgEntity where  bankaccountId = :bankaccountId")
					.setParameter("bankaccountId", bankaccountId).list();
		} catch (Exception e) {
			throw new DALException(e);
		}
		return iotransaction;
	}
	
	@Override
	public List<IDALIotransactionEntity> getIotransactionsByBudget(int budgetId) throws DALException {
		
		List<IDALIotransactionEntity> iotransaction = null;
		
		try {
			iotransaction = session.createQuery(
					"from IotransactionPgEntity where  budgetId = :budgetId")
					.setParameter("budgetId", budgetId).list();
		} catch (Exception e) {
			throw new DALException(e);
		}
		return iotransaction;
	}
}
