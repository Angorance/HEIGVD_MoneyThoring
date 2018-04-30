package dal.entities.pgsql;

import dal.ientites.IDALBudgetEntity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "budget", schema = "moneythoring", catalog = "moneythoring")
public class BudgetPgEntity implements IDALBudgetEntity {
    private int id;
    private String name;
    private double amount;
    private boolean isshared;
    private boolean isrecurrent;
    private Date startingdate;
    private Date endingdate;
    private Integer gap;
    private int clientId;

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
    @Column(name = "amount", nullable = false, precision = 0)
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "isshared", nullable = false)
    public boolean isIsshared() {
        return isshared;
    }

    public void setIsshared(boolean isshared) {
        this.isshared = isshared;
    }

    @Basic
    @Column(name = "isrecurrent", nullable = false)
    public boolean isIsrecurrent() {
        return isrecurrent;
    }

    public void setIsrecurrent(boolean isrecurrent) {
        this.isrecurrent = isrecurrent;
    }

    @Basic
    @Column(name = "startingdate", nullable = false)
    public Date getStartingdate() {
        return startingdate;
    }

    public void setStartingdate(Date startingdate) {
        this.startingdate = startingdate;
    }

    @Basic
    @Column(name = "endingdate", nullable = false)
    public Date getEndingdate() {
        return endingdate;
    }

    public void setEndingdate(Date endingdate) {
        this.endingdate = endingdate;
    }

    @Basic
    @Column(name = "gap", nullable = true)
    public Integer getGap() {
        return gap;
    }

    public void setGap(Integer gap) {
        this.gap = gap;
    }

    @Basic
    @Column(name = "client_id", nullable = false)
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
        BudgetPgEntity that = (BudgetPgEntity) o;
        return id == that.id &&
                Double.compare(that.amount, amount) == 0 &&
                isshared == that.isshared &&
                isrecurrent == that.isrecurrent &&
                clientId == that.clientId &&
                Objects.equals(name, that.name) &&
                Objects.equals(startingdate, that.startingdate) &&
                Objects.equals(endingdate, that.endingdate) &&
                Objects.equals(gap, that.gap);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, amount, isshared, isrecurrent, startingdate, endingdate, gap, clientId);
    }
}
