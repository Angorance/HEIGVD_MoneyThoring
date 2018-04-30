package dal.ientites;

import dal.entities.derby.BankaccountDeEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.List;

public interface IDALClientEntity {
    @Id
    @Column(name = "id", nullable = false)
    int getId();

    void setId(int id);

    @Basic
    @Column(name = "username", nullable = false, length = 50)
    String getUsername();

    void setUsername(String username);

    @Basic
    @Column(name = "email", nullable = false, length = 100)
    String getEmail();

    void setEmail(String email);

    @Basic
    @Column(name = "password", nullable = false, length = 250)
    String getPassword();

    void setPassword(String password);

    @Basic
    @Column(name = "isactivated", nullable = false)
    boolean getIsactivated();

    void setIsactivated(boolean isactivated);

    @Basic
    @Column(name = "activationkey", nullable = true, length = 50)
    String getActivationkey();

    void setActivationkey(String activationkey);

    @Basic
    @Column(name = "salt", nullable = false, length = 50)
    String getSalt();

    void setSalt(String salt);


    @Override
    boolean equals(Object o);

    @Override
    int hashCode();
}
