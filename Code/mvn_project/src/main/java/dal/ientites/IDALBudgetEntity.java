package dal.ientites;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import java.sql.Date;

/**
 * IDALBudgetEntity interface.
 * interface who represent a budget entity
 *
 * @author Guillaume Zaretti
 */
public interface IDALBudgetEntity {
    /**
     * get budget id
     * @return budget id
     */
    @Id
    @Column(name = "id", nullable = false)
    int getId();

    /**
     * set budget id
     * @param id of the budget
     */
    void setId(int id);

    /**
     * get the name of the budget
     * @return name of the budget
     */
    @Basic
    @Column(name = "name", nullable = false, length = 50)
    String getName();

    /**
     * set the name of the budget
     * @param name of the budget
     */
    void setName(String name);

    /**
     * get ammount of the budget
     * @return the ammount of the budget
     */
    @Basic
    @Column(name = "amount", nullable = false, precision = 0)
    double getAmount();

    /**
     * set amount of the budget
     * @param amount of the budget
     */
    void setAmount(double amount);

    /**
     * get if the budget is a shared budget
     * @return true if the budget is shared otherwise false
     */
    @Basic
    @Column(name = "isshared", nullable = false)
    boolean isIsshared();

    /**
     * set isshared budget
     * @param isshared if the budget isshared = true otherwise isshared = false
     */
    void setIsshared(boolean isshared);

    /**
     * get is a recurent budget
     * @return boolean if the budget is recurent return true else false
     */
    @Basic
    @Column(name = "isrecurrent", nullable = false)
    boolean isIsrecurrent();

    /**
     * set is recurrent
     * @param isrecurrent
     */
    void setIsrecurrent(boolean isrecurrent);

    /**
     * get starting date
     * @return Date when the budget started
     */
    @Basic
    @Column(name = "startingdate", nullable = false)
    Date getStartingdate();

    /**
     * set starting date
     * @param startingdate starting date of the budget
     */
    void setStartingdate(Date startingdate);

    /**
     * get the termination date
     * @return the termination date
     */
    @Basic
    @Column(name = "endingdate", nullable = false)
    Date getEndingdate();

    /**
     * set the termination date
     * @param endingdate termination date
     */
    void setEndingdate(Date endingdate);

    /**
     * get gap
     * @return the gap of the budget
     */
    @Basic
    @Column(name = "gap", nullable = true)
    Integer getGap();

    /**
     * set gap
     * @param gap of the budget
     */
    void setGap(Integer gap);

    /**
     * get client id
     * @return the client id
     */
    @Basic
    @Column(name = "client_id", nullable = false)
    int getClientId();

    /**
     * set the client id
     * @param clientId the client id
     */
    void setClientId(int clientId);
}
