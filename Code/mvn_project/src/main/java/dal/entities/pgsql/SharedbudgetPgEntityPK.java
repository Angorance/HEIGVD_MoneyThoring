package dal.entities.pgsql;

import dal.ientites.IDALSharedbudgetEntityPK;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Guillaume zaretti
 * @version 1.2
 * @see IDALSharedbudgetEntityPK
 */
public class SharedbudgetPgEntityPK implements Serializable, IDALSharedbudgetEntityPK {
	
	private int clientId;
	private int budgetId;
	
	@Column(name = "client_id", nullable = false)
	@Id
	public int getClientId() {
		
		return clientId;
	}
	
	public void setClientId(int clientId) {
		
		this.clientId = clientId;
	}
	
	@Column(name = "budget_id", nullable = false)
	@Id
	public int getBudgetId() {
		
		return budgetId;
	}
	
	public void setBudgetId(int budgetId) {
		
		this.budgetId = budgetId;
	}
	
	@Override
	public boolean equals(Object o) {
		
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		SharedbudgetPgEntityPK that = (SharedbudgetPgEntityPK) o;
		return clientId == that.clientId && budgetId == that.budgetId;
	}
	
	@Override
	public int hashCode() {
		
		return Objects.hash(clientId, budgetId);
	}
}
