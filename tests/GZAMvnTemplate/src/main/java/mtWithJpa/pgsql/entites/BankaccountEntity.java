package mtWithJpa.pgsql.entites;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "bankaccount", schema = "moneythoring", catalog = "moneythoring")
public class BankaccountEntity {
    private int id;
    private String name;
    private String namebank;
    private String typeaccount;
    private double amount;
    private boolean isdefault;
    private boolean isvisible;
    private int clientId;
    private ClientEntity clientByClientId;
    private Collection<IotransactionEntity> iotransactionsById;

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
    @Column(name = "namebank", nullable = true, length = 50)
    public String getNamebank() {
        return namebank;
    }

    public void setNamebank(String namebank) {
        this.namebank = namebank;
    }

    @Basic
    @Column(name = "typeaccount", nullable = false, length = 100)
    public String getTypeaccount() {
        return typeaccount;
    }

    public void setTypeaccount(String typeaccount) {
        this.typeaccount = typeaccount;
    }

    @Basic
    @Column(name = "amount", nullable = false, precision = 17)
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "isdefault", nullable = false)
    public boolean isIsdefault() {
        return isdefault;
    }

    public void setIsdefault(boolean isdefault) {
        this.isdefault = isdefault;
    }

    @Basic
    @Column(name = "isvisible", nullable = false)
    public boolean isIsvisible() {
        return isvisible;
    }

    public void setIsvisible(boolean isvisible) {
        this.isvisible = isvisible;
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

        BankaccountEntity that = (BankaccountEntity) o;

        if (id != that.id) return false;
        if (Double.compare(that.amount, amount) != 0) return false;
        if (isdefault != that.isdefault) return false;
        if (isvisible != that.isvisible) return false;
        if (clientId != that.clientId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (namebank != null ? !namebank.equals(that.namebank) : that.namebank != null) return false;
        if (typeaccount != null ? !typeaccount.equals(that.typeaccount) : that.typeaccount != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (namebank != null ? namebank.hashCode() : 0);
        result = 31 * result + (typeaccount != null ? typeaccount.hashCode() : 0);
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (isdefault ? 1 : 0);
        result = 31 * result + (isvisible ? 1 : 0);
        result = 31 * result + clientId;
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

    @OneToMany(mappedBy = "bankaccountByBankaccountId")
    public Collection<IotransactionEntity> getIotransactionsById() {
        return iotransactionsById;
    }

    public void setIotransactionsById(Collection<IotransactionEntity> iotransactionsById) {
        this.iotransactionsById = iotransactionsById;
    }
}
