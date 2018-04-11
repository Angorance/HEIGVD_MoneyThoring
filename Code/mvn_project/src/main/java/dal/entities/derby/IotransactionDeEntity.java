package dal.entities.derby;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "IOTRANSACTION", schema = "MONEYTHORING", catalog = "")
public class IotransactionDeEntity {
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
    private CategoryDeEntity categoryByCategoryId;
    private BankaccountDeEntity bankaccountByBankaccountId;
    private BudgetDeEntity budgetByBudgetId;
    private Collection<RecurrenceDeEntity> recurrencesById;

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

        if (id != that.id) return false;
        if (Double.compare(that.amount, amount) != 0) return false;
        if (isincome != that.isincome) return false;
        if (categoryId != that.categoryId) return false;
        if (bankaccountId != that.bankaccountId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (datetransaction != null ? !datetransaction.equals(that.datetransaction) : that.datetransaction != null)
            return false;
        if (currency != null ? !currency.equals(that.currency) : that.currency != null) return false;
        if (budgetId != null ? !budgetId.equals(that.budgetId) : that.budgetId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (datetransaction != null ? datetransaction.hashCode() : 0);
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (isincome ? 1 : 0);
        result = 31 * result + categoryId;
        result = 31 * result + bankaccountId;
        result = 31 * result + (budgetId != null ? budgetId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID", nullable = false)
    public CategoryDeEntity getCategoryByCategoryId() {
        return categoryByCategoryId;
    }

    public void setCategoryByCategoryId(CategoryDeEntity categoryByCategoryId) {
        this.categoryByCategoryId = categoryByCategoryId;
    }

    @ManyToOne
    @JoinColumn(name = "BANKACCOUNT_ID", referencedColumnName = "ID", nullable = false)
    public BankaccountDeEntity getBankaccountByBankaccountId() {
        return bankaccountByBankaccountId;
    }

    public void setBankaccountByBankaccountId(BankaccountDeEntity bankaccountByBankaccountId) {
        this.bankaccountByBankaccountId = bankaccountByBankaccountId;
    }

    @ManyToOne
    @JoinColumn(name = "BUDGET_ID", referencedColumnName = "ID")
    public BudgetDeEntity getBudgetByBudgetId() {
        return budgetByBudgetId;
    }

    public void setBudgetByBudgetId(BudgetDeEntity budgetByBudgetId) {
        this.budgetByBudgetId = budgetByBudgetId;
    }

    @OneToMany(mappedBy = "iotransactionByIotransactionId")
    public Collection<RecurrenceDeEntity> getRecurrencesById() {
        return recurrencesById;
    }

    public void setRecurrencesById(Collection<RecurrenceDeEntity> recurrencesById) {
        this.recurrencesById = recurrencesById;
    }
}
