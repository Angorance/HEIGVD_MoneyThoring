package mt.jpajpa.entities;

import javax.persistence.*;

@Entity
@Table(name = "sharedbudget", schema = "moneythoring", catalog = "moneythoring")
@IdClass(SharedbudgetEntityPK.class)
public class SharedbudgetEntity {
    private int clientId;
    private int budgetId;
    private ClientEntity clientByClientId;
    private BudgetEntity budgetByBudgetId;

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

        SharedbudgetEntity that = (SharedbudgetEntity) o;

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
    public ClientEntity getClientByClientId() {
        return clientByClientId;
    }

    public void setClientByClientId(ClientEntity clientByClientId) {
        this.clientByClientId = clientByClientId;
    }

    @ManyToOne
    @JoinColumn(name = "budget_id", referencedColumnName = "id", nullable = false)
    public BudgetEntity getBudgetByBudgetId() {
        return budgetByBudgetId;
    }

    public void setBudgetByBudgetId(BudgetEntity budgetByBudgetId) {
        this.budgetByBudgetId = budgetByBudgetId;
    }
}
