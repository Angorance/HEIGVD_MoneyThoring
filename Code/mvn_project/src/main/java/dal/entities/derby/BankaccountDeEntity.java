package dal.entities.derby;

import dal.ientites.IDALBankaccountEntity;
import dal.ientites.IDALClientEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "BANKACCOUNT", schema = "MONEYTHORING", catalog = "")
public class BankaccountDeEntity implements IDALBankaccountEntity {
    private int id;
    private String name;
    private String namebank;
    private String typeaccount;
    private double amount;
    private boolean isdefault;
    private boolean isvisible;
    private int clientId;
    private ClientDeEntity clientByClientId;

    /**
     * {@inheritDoc}
     */
    @Override
    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(int id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Basic
    @Column(name = "NAME", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Basic
    @Column(name = "NAMEBANK", nullable = true, length = 50)
    public String getNamebank() {
        return namebank;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNamebank(String namebank) {
        this.namebank = namebank;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Basic
    @Column(name = "TYPEACCOUNT", nullable = false, length = 100)
    public String getTypeaccount() {
        return typeaccount;
    }

    public void setTypeaccount(String typeaccount) {
        this.typeaccount = typeaccount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Basic
    @Column(name = "AMOUNT", nullable = false, precision = 0)
    public double getAmount() {
        return amount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Basic
    @Column(name = "ISDEFAULT", nullable = false)
    public boolean isIsdefault() {
        return isdefault;
    }

    public void setIsdefault(boolean isdefault) {
        this.isdefault = isdefault;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Basic
    @Column(name = "ISVISIBLE", nullable = false)
    public boolean isIsvisible() {
        return isvisible;
    }

    public void setIsvisible(boolean isvisible) {
        this.isvisible = isvisible;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Basic
    @Column(name = "CLIENT_ID", nullable = false)
    public int getClientId() {
        return clientId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankaccountDeEntity that = (BankaccountDeEntity) o;
        return id == that.id &&
                Double.compare(that.amount, amount) == 0 &&
                isdefault == that.isdefault &&
                isvisible == that.isvisible &&
                clientId == that.clientId &&
                Objects.equals(name, that.name) &&
                Objects.equals(namebank, that.namebank) &&
                Objects.equals(typeaccount, that.typeaccount);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {

        return Objects.hash(id, name, namebank, typeaccount, amount, isdefault, isvisible, clientId);
    }

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "ID", nullable = false)
    public IDALClientEntity getClientByClientId() {
        return clientByClientId;
    }

    public void setClientByClientId(IDALClientEntity clientByClientId) {

        this.clientByClientId = (ClientDeEntity) clientByClientId;
    }
}
