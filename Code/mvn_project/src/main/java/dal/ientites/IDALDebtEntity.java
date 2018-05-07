package dal.ientites;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import java.sql.Date;

/**
 * IDALDebtEntity interface.
 * interface who represent a dbt entity
 *
 * @author Guillaume Zaretti
 */
public interface IDALDebtEntity {
    /**
     * get the debt id
     *
     * @return debt id
     */
    @Id
    @Column(name = "id", nullable = false)
    int getId();

    /**
     * set the debt id
     *
     * @param id of the debt
     */
    void setId(int id);

    /**
     * get the name of the debt
     *
     * @return the name of the debt
     */
    @Basic
    @Column(name = "name", nullable = false, length = 50)
    String getName();

    /**
     * set the name of the debt
     *
     * @param name of the debt
     */
    void setName(String name);

    /**
     * get the description dbet
     *
     * @return description of the debt
     */
    @Basic
    @Column(name = "description", nullable = true, length = 255)
    String getDescription();

    /**
     * set the descrition of the debt
     *
     * @param description of the debt
     */
    void setDescription(String description);

    /**
     * get ammount
     *
     * @return the ammount of the debt
     */
    @Basic
    @Column(name = "amount", nullable = false, precision = 0)
    double getAmount();

    /**
     * set the amount debt
     *
     * @param amount of the debt
     */
    void setAmount(double amount);

    /**
     * if the dbt is income
     *
     * @return boolean true if the debt is income esle false
     */
    @Basic
    @Column(name = "isincome", nullable = false)
    boolean isIsincome();

    /**
     * set if the detbt is income
     *
     * @param isincome true is income else false
     */
    void setIsincome(boolean isincome);

    /**
     * get expiration date of the debt
     *
     * @return the expration date of the debt
     */
    @Basic
    @Column(name = "expirationdate", nullable = false)
    Date getExpirationdate();

    /**
     * set the expiration date of the debt
     *
     * @param expirationdate of the debt
     */
    void setExpirationdate(Date expirationdate);

    /**
     * get client id of the debt
     *
     * @return the client id of the debt
     */
    @Basic
    @Column(name = "client_id", nullable = false)
    int getClientId();

    /**
     * set the client id of the debt
     *
     * @param clientId of the debt
     */
    void setClientId(int clientId);

    /**
     * get client id of the debt
     *
     * @return the debt of the client
     */
    @Basic
    @Column(name = "client_id1", nullable = true)
    Integer getClientId1();

    /**
     * set client id of the debt
     *
     * @param clientId1 of the debt
     */
    void setClientId1(Integer clientId1);
}
