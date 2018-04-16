package dal.ientites;

import dal.entities.pgsql.ClientPgEntity;

import javax.persistence.*;

public interface IDALBankaccountEntity {
    @Id
    @Column(name = "ID", nullable = false)
    int getId();

    void setId(int id);

    @Basic
    @Column(name = "NAME", nullable = false, length = 50)
    String getName();

    void setName(String name);

    @Basic
    @Column(name = "NAMEBANK", nullable = true, length = 50)
    String getNamebank();

    void setNamebank(String namebank);

    @Basic
    @Column(name = "TYPEACCOUNT", nullable = false, length = 100)
    String getTypeaccount();

    void setTypeaccount(String typeaccount);

    @Basic
    @Column(name = "AMOUNT", nullable = false, precision = 0)
    double getAmount();

    void setAmount(double amount);

    @Basic
    @Column(name = "ISDEFAULT", nullable = false)
    boolean isIsdefault();

    void setIsdefault(boolean isdefault);

    @Basic
    @Column(name = "ISVISIBLE", nullable = false)
    boolean isIsvisible();

    void setIsvisible(boolean isvisible);

    @Basic
    @Column(name = "CLIENT_ID", nullable = false)
    int getClientId();

    void setClientId(int clientId);

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "ID", nullable = false)
    IDALClientEntity getClientByClientId();

    void setClientByClientId(IDALClientEntity clientByClientId);
}
