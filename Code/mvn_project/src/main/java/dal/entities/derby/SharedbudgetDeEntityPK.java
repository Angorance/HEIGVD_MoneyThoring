package dal.entities.derby;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class SharedbudgetDeEntityPK implements Serializable {
    private int clientId;
    private int budgetId;

    @Column(name = "CLIENT_ID", nullable = false)
    @Id
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Column(name = "BUDGET_ID", nullable = false)
    @Id
    public int getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(int budgetId) {
        this.budgetId = budgetId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SharedbudgetDeEntityPK that = (SharedbudgetDeEntityPK) o;

        if (clientId != that.clientId) return false;
        if (budgetId != that.budgetId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = clientId;
        result = 31 * result + budgetId;
        return result;
    }
}
