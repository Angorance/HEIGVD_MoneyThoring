package mt.jpajpa.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class SharedbudgetEntityPK implements Serializable {
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SharedbudgetEntityPK that = (SharedbudgetEntityPK) o;

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
