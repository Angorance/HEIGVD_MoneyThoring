package dal.ientites;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * TODO
 */
public interface IDALSharedbudgetEntity {
	
	@Id
	@Column(name = "client_id", nullable = false)
	int getClientId();
	
	void setClientId(int clientId);
	
	@Id
	@Column(name = "budget_id", nullable = false)
	int getBudgetId();
	
	void setBudgetId(int budgetId);
}
