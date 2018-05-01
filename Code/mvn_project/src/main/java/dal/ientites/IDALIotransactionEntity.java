package dal.ientites;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import java.sql.Date;

public interface IDALIotransactionEntity {
    @Id
    @Column(name = "id", nullable = false)
    int getId();

    void setId(int id);

    @Basic
    @Column(name = "name", nullable = false, length = 50)
    String getName();

    void setName(String name);

    @Basic
    @Column(name = "description", nullable = true, length = 255)
    String getDescription();

    void setDescription(String description);

    @Basic
    @Column(name = "datetransaction", nullable = false)
    Date getDatetransaction();

    void setDatetransaction(Date datetransaction);

    @Basic
    @Column(name = "amount", nullable = false, precision = 0)
    double getAmount();

    void setAmount(double amount);

    @Basic
    @Column(name = "currency", nullable = false, length = 50)
    String getCurrency();

    void setCurrency(String currency);

    @Basic
    @Column(name = "isincome", nullable = false)
    boolean isIsincome();

    void setIsincome(boolean isincome);

    @Basic
    @Column(name = "category_id", nullable = false)
    int getCategoryId();

    void setCategoryId(int categoryId);

    @Basic
    @Column(name = "bankaccount_id", nullable = false)
    int getBankaccountId();

    void setBankaccountId(int bankaccountId);

    @Basic
    @Column(name = "budget_id", nullable = true)
    Integer getBudgetId();

    void setBudgetId(Integer budgetId);
}
