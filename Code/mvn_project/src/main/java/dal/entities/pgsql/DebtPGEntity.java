package dal.entities.pgsql;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "debt", schema = "moneythoring", catalog = "moneythoring")
public class DebtPGEntity {
    private int id;
    private String name;
    private String description;
    private double amount;
    private boolean isincome;
    private Date expirationdate;
    private int clientId;
    private Integer clientId1;
    private ClientPGEntity clientByClientId;
    private ClientPGEntity clientByClientId1;

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
    @Column(name = "amount", nullable = false, precision = 0)
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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
    @Column(name = "expirationdate", nullable = false)
    public Date getExpirationdate() {
        return expirationdate;
    }

    public void setExpirationdate(Date expirationdate) {
        this.expirationdate = expirationdate;
    }

    @Basic
    @Column(name = "client_id", nullable = false)
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Basic
    @Column(name = "client_id1", nullable = true)
    public Integer getClientId1() {
        return clientId1;
    }

    public void setClientId1(Integer clientId1) {
        this.clientId1 = clientId1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DebtPGEntity that = (DebtPGEntity) o;

        if (id != that.id) return false;
        if (Double.compare(that.amount, amount) != 0) return false;
        if (isincome != that.isincome) return false;
        if (clientId != that.clientId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (expirationdate != null ? !expirationdate.equals(that.expirationdate) : that.expirationdate != null)
            return false;
        if (clientId1 != null ? !clientId1.equals(that.clientId1) : that.clientId1 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (isincome ? 1 : 0);
        result = 31 * result + (expirationdate != null ? expirationdate.hashCode() : 0);
        result = 31 * result + clientId;
        result = 31 * result + (clientId1 != null ? clientId1.hashCode() : 0);
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
    @JoinColumn(name = "client_id1", referencedColumnName = "id")
    public ClientPGEntity getClientByClientId1() {
        return clientByClientId1;
    }

    public void setClientByClientId1(ClientPGEntity clientByClientId1) {
        this.clientByClientId1 = clientByClientId1;
    }
}
