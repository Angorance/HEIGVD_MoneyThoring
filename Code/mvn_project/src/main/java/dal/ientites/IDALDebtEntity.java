package dal.ientites;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import java.sql.Date;

public interface IDALDebtEntity {
    @Id
    @Column(name = "id", nullable = false)
    int getId();

    void setId(int id);

    @Basic
    @Column(name = "name", nullable = false, length = 50)
    String getName();

    void setName(String name);

    @Basic
    @Column(name = "description", nullable = true, length = 255)
    String getDescription();

    void setDescription(String description);

    @Basic
    @Column(name = "amount", nullable = false, precision = 0)
    double getAmount();

    void setAmount(double amount);

    @Basic
    @Column(name = "isincome", nullable = false)
    boolean isIsincome();

    void setIsincome(boolean isincome);

    @Basic
    @Column(name = "expirationdate", nullable = false)
    Date getExpirationdate();

    void setExpirationdate(Date expirationdate);

    @Basic
    @Column(name = "client_id", nullable = false)
    int getClientId();

    void setClientId(int clientId);

    @Basic
    @Column(name = "client_id1", nullable = true)
    Integer getClientId1();

    void setClientId1(Integer clientId1);
}
