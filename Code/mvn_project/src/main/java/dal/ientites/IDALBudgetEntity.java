package dal.ientites;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import java.sql.Date;

public interface IDALBudgetEntity {
    @Id
    @Column(name = "id", nullable = false)
    int getId();

    void setId(int id);

    @Basic
    @Column(name = "name", nullable = false, length = 50)
    String getName();

    void setName(String name);

    @Basic
    @Column(name = "amount", nullable = false, precision = 0)
    double getAmount();

    void setAmount(double amount);

    @Basic
    @Column(name = "isshared", nullable = false)
    boolean isIsshared();

    void setIsshared(boolean isshared);

    @Basic
    @Column(name = "isrecurrent", nullable = false)
    boolean isIsrecurrent();

    void setIsrecurrent(boolean isrecurrent);

    @Basic
    @Column(name = "startingdate", nullable = false)
    Date getStartingdate();

    void setStartingdate(Date startingdate);

    @Basic
    @Column(name = "endingdate", nullable = false)
    Date getEndingdate();

    void setEndingdate(Date endingdate);

    @Basic
    @Column(name = "gap", nullable = true)
    Integer getGap();

    void setGap(Integer gap);

    @Basic
    @Column(name = "client_id", nullable = false)
    int getClientId();

    void setClientId(int clientId);
}
