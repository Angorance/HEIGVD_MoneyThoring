package dal.repositories.pgsql;

import dal.dalexception.DALException;
import dal.entities.pgsql.DebtPgEntity;
import dal.ientites.IDALDebtEntity;
import dal.irepositories.IDebtRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;


/**
 * DebtPgRepository give the access methodes for handle the client into postgres
 * persistence
 */
public class DebtPgRepository implements IDebtRepository {
	
	private Session session;
	private Transaction transaction;
	
	/**
	 * Constructor of DebtPgRepository
	 *
	 * @param session current session used
	 * @param transaction current transaction used into the same session
	 */
	public DebtPgRepository(Session session, Transaction transaction) {
		
		this.session = session;
		this.transaction = transaction;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IDALDebtEntity getDebt(int id) throws DALException {
		
		DebtPgEntity Debt = null;
		
		try {
			Debt = (DebtPgEntity) session.createCriteria(DebtPgEntity.class)
					.add(Restrictions.eq("id", id)).uniqueResult();
		} catch (Exception e) {
			throw new DALException(e);
		}
		
		return Debt;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<IDALDebtEntity> getDebts() throws DALException {
		
		List<IDALDebtEntity> dbt = null;
		try {
			dbt = session.createQuery("from DebtPgEntity").list();
			
		} catch (Exception e) {
			throw new DALException(e);
		}
		return dbt;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(IDALDebtEntity debt) throws DALException {
		
		DebtPgEntity debtPg = null;
		if (debt.getClass() == DebtPgEntity.class) {
			debtPg = (DebtPgEntity) debt;
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
	public Integer addDebt(IDALDebtEntity debt) throws DALException {
		
		DebtPgEntity newDebt = null;
		if (newDebt.getClass() == DebtPgEntity.class) {
			newDebt = (DebtPgEntity) debt;
		} else {
			throw new DALException();
		}
		
		try {
			return (Integer) session.save(newDebt);
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
			debt = (DebtPgEntity) session.createCriteria(DebtPgEntity.class)
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
