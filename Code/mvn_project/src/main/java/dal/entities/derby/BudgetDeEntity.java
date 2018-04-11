package dal.entities.derby;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "BUDGET", schema = "MONEYTHORING", catalog = "")
public class BudgetDeEntity {
    private int id;
    private String name;
    private double amount;
    private boolean isshared;
    private boolean isrecurrent;
    private Date startingdate;
    private Date endingdate;
    private Integer gap;
    private int clientId;
    private ClientEntityDeEntity clientByClientId;
    private Collection<CategoriesbudgetDeEntity> categoriesbudgetsById;
    private Collection<IotransactionDeEntity> iotransactionsById;
    private Collection<SharedbudgetDeEntity> sharedbudgetsById;

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
    @Column(name = "AMOUNT", nullable = false, precision = 0)
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "ISSHARED", nullable = false)
    public boolean isIsshared() {
        return isshared;
    }

    public void setIsshared(boolean isshared) {
        this.isshared = isshared;
    }

    @Basic
    @Column(name = "ISRECURRENT", nullable = false)
    public boolean isIsrecurrent() {
        return isrecurrent;
    }

    public void setIsrecurrent(boolean isrecurrent) {
        this.isrecurrent = isrecurrent;
    }

    @Basic
    @Column(name = "STARTINGDATE", nullable = false)
    public Date getStartingdate() {
        return startingdate;
    }

    public void setStartingdate(Date startingdate) {
        this.startingdate = startingdate;
    }

    @Basic
    @Column(name = "ENDINGDATE", nullable = false)
    public Date getEndingdate() {
        return endingdate;
    }

    public void setEndingdate(Date endingdate) {
        this.endingdate = endingdate;
    }

    @Basic
    @Column(name = "GAP", nullable = true)
    public Integer getGap() {
        return gap;
    }

    public void setGap(Integer gap) {
        this.gap = gap;
    }

    @Basic
    @Column(name = "CLIENT_ID", nullable = false)
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BudgetDeEntity that = (BudgetDeEntity) o;

        if (id != that.id) return false;
        if (Double.compare(that.amount, amount) != 0) return false;
        if (isshared != that.isshared) return false;
        if (isrecurrent != that.isrecurrent) return false;
        if (clientId != that.clientId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (startingdate != null ? !startingdate.equals(that.startingdate) : that.startingdate != null) return false;
        if (endingdate != null ? !endingdate.equals(that.endingdate) : that.endingdate != null) return false;
        if (gap != null ? !gap.equals(that.gap) : that.gap != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (isshared ? 1 : 0);
        result = 31 * result + (isrecurrent ? 1 : 0);
        result = 31 * result + (startingdate != null ? startingdate.hashCode() : 0);
        result = 31 * result + (endingdate != null ? endingdate.hashCode() : 0);
        result = 31 * result + (gap != null ? gap.hashCode() : 0);
        result = 31 * result + clientId;
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

    @OneToMany(mappedBy = "budgetByBudgetId")
    public Collection<CategoriesbudgetDeEntity> getCategoriesbudgetsById() {
        return categoriesbudgetsById;
    }

    public void setCategoriesbudgetsById(Collection<CategoriesbudgetDeEntity> categoriesbudgetsById) {
        this.categoriesbudgetsById = categoriesbudgetsById;
    }

    @OneToMany(mappedBy = "budgetByBudgetId")
    public Collection<IotransactionDeEntity> getIotransactionsById() {
        return iotransactionsById;
    }

    public void setIotransactionsById(Collection<IotransactionDeEntity> iotransactionsById) {
        this.iotransactionsById = iotransactionsById;
    }

    @OneToMany(mappedBy = "budgetByBudgetId")
    public Collection<SharedbudgetDeEntity> getSharedbudgetsById() {
        return sharedbudgetsById;
    }

    public void setSharedbudgetsById(Collection<SharedbudgetDeEntity> sharedbudgetsById) {
        this.sharedbudgetsById = sharedbudgetsById;
    }
}
