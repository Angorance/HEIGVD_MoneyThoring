package dal.entities.pgsql;

import dal.ientites.IDALSharedbudgetEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sharedbudget", schema = "moneythoring", catalog = "moneythoring")
@IdClass(SharedbudgetPgEntityPK.class)
public class SharedbudgetPgEntity implements IDALSharedbudgetEntity {
    private int clientId;
    private int budgetId;

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
        SharedbudgetPgEntity that = (SharedbudgetPgEntity) o;
        return clientId == that.clientId &&
                budgetId == that.budgetId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(clientId, budgetId);
    }
}
