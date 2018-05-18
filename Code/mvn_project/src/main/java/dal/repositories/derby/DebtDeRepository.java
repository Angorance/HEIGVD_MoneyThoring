package dal.repositories.derby;


import dal.dalexception.DALException;
import dal.entities.derby.DebtDeEntity;
import dal.ientites.IDALDebtEntity;
import dal.irepositories.IDebtRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * DebtDeRepository give the access methodes for handle the debt into derby
 * persistence
 *
 * @author Guillaume Zaretti
 * @version 1.2
 */
public class DebtDeRepository implements IDebtRepository {
	
	private Session session;
	private Transaction transaction;
	
	/**
	 * Constructor of DebtDeRepository
	 *
	 * @param session current session used
	 * @param transaction current transaction used into the same session
	 */
	public DebtDeRepository(Session session, Transaction transaction) {
		
		this.session = session;
		this.transaction = transaction;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IDALDebtEntity getDebt(int id) throws DALException {
		
		DebtDeEntity dbt = null;
		
		try {
			dbt = (DebtDeEntity) session.createCriteria(DebtDeEntity.class)
					.add(Restrictions.eq("id", id)).uniqueResult();
		} catch (Exception e) {
			throw new DALException(e);
		}
		
		return dbt;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer addDebt(IDALDebtEntity debt) throws DALException {
		
		DebtDeEntity newDebt = null;
		if (debt.getClass() == DebtDeEntity.class) {
			newDebt = (DebtDeEntity) debt;
		} else {
			throw new DALException();
		}
		
		try {
			return (Integer) session.save(newDebt);
		} catch (Exception e) {
			throw new DALException(e);
		}
	}
	
	public List<IDALDebtEntity> getDebts() throws DALException {
		
		List<IDALDebtEntity> debts = null;
		try {
			debts = session.createQuery("from DebtDeEntity").list();
			
		} catch (Exception e) {
			throw new DALException(e);
		}
		return debts;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(IDALDebtEntity debt) throws DALException {
		
		DebtDeEntity debtPg = null;
		if (debt.getClass() == DebtDeEntity.class) {
			debtPg = (DebtDeEntity) debt;
		} else {
			throw new DALException();
		}
		
		try {
			session.update(debtPg);
		} catch (Exception e) {
			throw new DALException(e);
		}
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(int id) throws DALException {
		
		IDALDebtEntity debt = null;
		try {
			debt = (DebtDeEntity) session.createCriteria(DebtDeEntity.class)
					.add(Restrictions.eq("id", id)).uniqueResult();
			
			session.delete(debt);
		} catch (Exception e) {
			throw new DALException(e);
		}
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<IDALDebtEntity> getDebtsByClient(int clientID)
			throws DALException {
		
		List<IDALDebtEntity> debtEntities = null;
		
		try {
			debtEntities = session.createQuery(
					"from DebtDeEntity where clientId = :clientid or clientId1 = :contributorid")
					.setParameter("clientid", clientID)
					.setParameter("contributorid", clientID).list();
			
		} catch (Exception e) {
			throw new DALException(e);
		}
		
		return debtEntities;
	}
}
