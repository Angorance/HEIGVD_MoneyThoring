package dal.ientites;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * IDALSharedbudgetEntityPK interface.
 * interface who represent a shared budget pk entity
 *
 * @author Guillaume Zaretti
 * @version 1.2
 */
public interface IDALSharedbudgetEntityPK {
	
	/**
	 * get client id of the shared budget entity pk
	 *
	 * @return the client id of the shared budget entity pk
	 */
	@Id
	@Column(name = "client_id", nullable = false)
	int getClientId();
	
	/**
	 * set client id of the shared budget entity pk
	 *
	 * @param clientId of the shared budget entity pk
	 */
	void setClientId(int clientId);
	
	/**
	 * get budget id of the shared budget entity pk
	 *
	 * @return the budget id of the shared budget entitiy pk
	 */
	@Id
	@Column(name = "budget_id", nullable = false)
	int getBudgetId();
	
	/**
	 * set the budget id of the shared budget entity pk
	 *
	 * @param budgetId of the shared budget entity pk
	 */
	void setBudgetId(int budgetId);
}
