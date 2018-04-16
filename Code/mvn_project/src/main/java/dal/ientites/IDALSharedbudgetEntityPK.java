package dal.ientites;

import javax.persistence.Column;
import javax.persistence.Id;

public interface IDALSharedbudgetEntityPK {
    @Column(name = "client_id", nullable = false)
    @Id
    int getClientId();

    void setClientId(int clientId);

    @Column(name = "budget_id", nullable = false)
    @Id
    int getBudgetId();

    void setBudgetId(int budgetId);
}
