package dal.entities.pgsql;

import javax.persistence.*;

@Entity
@Table(name = "sharedbudget", schema = "moneythoring", catalog = "moneythoring")
@IdClass(SharedbudgetPGEntityPK.class)
public class SharedbudgetPGEntity {
    private int clientId;
    private int budgetId;
    private ClientPGEntity clientByClientId;
    private BudgetPGEntity budgetByBudgetId;

    @Id
    @Column(name = "client_id", nullable = false)
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Id
    @Column(name = "budget_id", nullable = false)
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

        SharedbudgetPGEntity that = (SharedbudgetPGEntity) o;

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
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    public ClientPGEntity getClientByClientId() {
        return clientByClientId;
    }

    public void setClientByClientId(ClientPGEntity clientByClientId) {
        this.clientByClientId = clientByClientId;
    }

    @ManyToOne
    @JoinColumn(name = "budget_id", referencedColumnName = "id", nullable = false)
    public BudgetPGEntity getBudgetByBudgetId() {
        return budgetByBudgetId;
    }

    public void setBudgetByBudgetId(BudgetPGEntity budgetByBudgetId) {
        this.budgetByBudgetId = budgetByBudgetId;
    }
}
