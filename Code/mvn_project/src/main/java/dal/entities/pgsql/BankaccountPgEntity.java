package dal.entities.pgsql;

import dal.ientites.IDALBankaccountEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "BANKACCOUNT", schema = "MONEYTHORING", catalog = "")
public class BankaccountPgEntity implements IDALBankaccountEntity {
    private int id;
    private String name;
    private String namebank;
    private String typeaccount;
    private double amount;
    private boolean isdefault;
    private boolean isvisible;
    private int clientId;
    private ClientPgEntity clientByClientId;

    @Override
    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    @Basic
    @Column(name = "NAME", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    @Basic
    @Column(name = "NAMEBANK", nullable = true, length = 50)
    public String getNamebank() {
        return namebank;
    }

    @Override
    public void setNamebank(String namebank) {
        this.namebank = namebank;
    }

    @Override
    @Basic
    @Column(name = "TYPEACCOUNT", nullable = false, length = 100)
    public String getTypeaccount() {
        return typeaccount;
    }

    @Override
    public void setTypeaccount(String typeaccount) {
        this.typeaccount = typeaccount;
    }

    @Override
    @Basic
    @Column(name = "AMOUNT", nullable = false, precision = 0)
    public double getAmount() {
        return amount;
    }

    @Override
    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    @Basic
    @Column(name = "ISDEFAULT", nullable = false)
    public boolean isIsdefault() {
        return isdefault;
    }

    @Override
    public void setIsdefault(boolean isdefault) {
        this.isdefault = isdefault;
    }

    @Override
    @Basic
    @Column(name = "ISVISIBLE", nullable = false)
    public boolean isIsvisible() {
        return isvisible;
    }

    @Override
    public void setIsvisible(boolean isvisible) {
        this.isvisible = isvisible;
    }

    @Override
    @Basic
    @Column(name = "CLIENT_ID", nullable = false)
    public int getClientId() {
        return clientId;
    }

    @Override
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

    @Override
    @ManyToOne
    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "ID", nullable = false)
    public ClientPgEntity getClientByClientId() {
        return clientByClientId;
    }

    @Override
    public void setClientByClientId(ClientPgEntity clientByClientId) {
        this.clientByClientId = clientByClientId;
    }
}
