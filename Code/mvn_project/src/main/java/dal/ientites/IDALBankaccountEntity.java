package dal.ientites;

import javax.persistence.*;

/**
 * IDALBankaccountEntity interface.
 * The interface present the methods allow to
 * change the attributes of the client into database, this interface
 * gives the CRUD methode and more.
 *
 * @version 1.0
 * @authors Guillaume Zaretti
 */
public interface IDALBankaccountEntity {

    int getId();

    void setId(int id);

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

}
