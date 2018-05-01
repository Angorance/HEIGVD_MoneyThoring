package dal.entities.derby;

import dal.ientites.IDALIotransactionEntity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "IOTRANSACTION", schema = "MONEYTHORING", catalog = "")
public class IotransactionDeEntity implements IDALIotransactionEntity {
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
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "DESCRIPTION", nullable = true, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "DATETRANSACTION", nullable = false)
    public Date getDatetransaction() {
        return datetransaction;
    }

    public void setDatetransaction(Date datetransaction) {
        this.datetransaction = datetransaction;
    }

    @Basic
    @Column(name = "AMOUNT", nullable = false, precision = 0)
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "CURRENCY", nullable = false, length = 50)
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Basic
    @Column(name = "ISINCOME", nullable = false)
    public boolean isIsincome() {
        return isincome;
    }

    public void setIsincome(boolean isincome) {
        this.isincome = isincome;
    }

    @Basic
    @Column(name = "CATEGORY_ID", nullable = false)
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "BANKACCOUNT_ID", nullable = false)
    public int getBankaccountId() {
        return bankaccountId;
    }

    public void setBankaccountId(int bankaccountId) {
        this.bankaccountId = bankaccountId;
    }

    @Basic
    @Column(name = "BUDGET_ID", nullable = true)
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
        IotransactionDeEntity that = (IotransactionDeEntity) o;
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
