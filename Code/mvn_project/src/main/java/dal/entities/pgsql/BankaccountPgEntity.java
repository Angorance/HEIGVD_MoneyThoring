package dal.entities.pgsql;

import dal.ientites.IDALBankaccountEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "bankaccount", schema = "moneythoring", catalog = "moneythoring")
public class BankaccountPgEntity implements IDALBankaccountEntity {
    private int id;
    private String name;
    private String namebank;
    private String typeaccount;
    private double amount;
    private boolean isdefault;
    private boolean isvisible;
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
    @Column(name = "amount", nullable = false, precision = 0)
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
        BankaccountPgEntity that = (BankaccountPgEntity) o;
        return id == that.id &&
                Double.compare(that.amount, amount) == 0 &&
                isdefault == that.isdefault &&
                isvisible == that.isvisible &&
                clientId == that.clientId &&
                Objects.equals(name, that.name) &&
                Objects.equals(namebank, that.namebank) &&
                Objects.equals(typeaccount, that.typeaccount);
    }


    @Override
    public int hashCode() {

        return Objects.hash(id, name, namebank, typeaccount, amount, isdefault, isvisible, clientId);
    }
}
