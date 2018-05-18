package dal.ientites;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * IDALSharedbudgetEntity interface.
 * interface who represent a shared budget entity
 *
 * @author Guillaume Zaretti
 * @version 1.2
 */
public interface IDALSharedbudgetEntity {

	/**
	 * get client id of the shared budget entity
	 * @return the id of the shared budget
	 */
	@Id
	@Column(name = "client_id", nullable = false)
	int getClientId();

	/**
	 * set client id of the shared budget
	 * @param clientId of the shared budget
	 */
	void setClientId(int clientId);

	/**
	 * get budget id of the shared budget
	 * @return budget id
	 */
	@Id
	@Column(name = "budget_id", nullable = false)
	int getBudgetId();

	/**
	 * set the budget id of the shared budget
	 * @param budgetId of the shared budget
	 */
	void setBudgetId(int budgetId);
}
