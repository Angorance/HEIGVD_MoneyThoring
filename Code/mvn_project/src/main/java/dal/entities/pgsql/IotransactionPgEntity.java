package dal.entities.pgsql;

import dal.ientites.IDALIotransactionEntity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "iotransaction", schema = "moneythoring", catalog = "moneythoring")
public class IotransactionPgEntity implements IDALIotransactionEntity {
    private int id;
    private String name;
    private String description;
    private Date datetransaction;
    private double amount;
    private String currency;
    private boolean isincome;
    private int categoryId;
    private int bankaccountId;
    private Integer budgetId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "datetransaction", nullable = false)
    public Date getDatetransaction() {
        return datetransaction;
    }

    public void setDatetransaction(Date datetransaction) {
        this.datetransaction = datetransaction;
    }

    @Basic
    @Column(name = "amount", nullable = false, precision = 0)
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "currency", nullable = false, length = 50)
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Basic
    @Column(name = "isincome", nullable = false)
    public boolean isIsincome() {
        return isincome;
    }

    public void setIsincome(boolean isincome) {
        this.isincome = isincome;
    }

    @Basic
    @Column(name = "category_id", nullable = false)
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "bankaccount_id", nullable = false)
    public int getBankaccountId() {
        return bankaccountId;
    }

    public void setBankaccountId(int bankaccountId) {
        this.bankaccountId = bankaccountId;
    }

    @Basic
    @Column(name = "budget_id", nullable = true)
    public Integer getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Integer budgetId) {
        this.budgetId = budgetId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IotransactionPgEntity that = (IotransactionPgEntity) o;
        return id == that.id &&
                Double.compare(that.amount, amount) == 0 &&
                isincome == that.isincome &&
                categoryId == that.categoryId &&
                bankaccountId == that.bankaccountId &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(datetransaction, that.datetransaction) &&
                Objects.equals(currency, that.currency) &&
                Objects.equals(budgetId, that.budgetId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, description, datetransaction, amount, currency, isincome, categoryId, bankaccountId, budgetId);
    }
}
