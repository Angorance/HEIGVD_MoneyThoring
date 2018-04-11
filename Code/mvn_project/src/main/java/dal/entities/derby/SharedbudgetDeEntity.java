package dal.entities.derby;

import javax.persistence.*;

@Entity
@Table(name = "SHAREDBUDGET", schema = "MONEYTHORING", catalog = "")
@IdClass(SharedbudgetDeEntityPK.class)
public class SharedbudgetDeEntity {
    private int clientId;
    private int budgetId;
    private ClientEntityDeEntity clientByClientId;
    private BudgetDeEntity budgetByBudgetId;

    @Id
    @Column(name = "CLIENT_ID", nullable = false)
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Id
    @Column(name = "BUDGET_ID", nullable = false)
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

        SharedbudgetDeEntity that = (SharedbudgetDeEntity) o;

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

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "ID", nullable = false)
    public ClientEntityDeEntity getClientByClientId() {
        return clientByClientId;
    }

    public void setClientByClientId(ClientEntityDeEntity clientByClientId) {
        this.clientByClientId = clientByClientId;
    }

    @ManyToOne
    @JoinColumn(name = "BUDGET_ID", referencedColumnName = "ID", nullable = false)
    public BudgetDeEntity getBudgetByBudgetId() {
        return budgetByBudgetId;
    }

    public void setBudgetByBudgetId(BudgetDeEntity budgetByBudgetId) {
        this.budgetByBudgetId = budgetByBudgetId;
    }
}
