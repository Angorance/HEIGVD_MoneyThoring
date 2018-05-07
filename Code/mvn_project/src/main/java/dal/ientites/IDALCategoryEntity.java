package dal.ientites;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;

/**
 * IDALCategoryEntity interface.
 * interface who represent a category entity
 *
 * @author Guillaume Zaretti
 */
public interface IDALCategoryEntity {
    /**
     * get categorie id
     * @return id of the category
     */
    @Id
    @Column(name = "id", nullable = false)
    int getId();

    /**
     * set the categorie id
     * @param id id of the category
     */
    void setId(int id);

    /**
     * get name of the category
     * @return String the name of the category
     */
    @Basic
    @Column(name = "name", nullable = false, length = 50)
    String getName();

    /**
     * set the name of the category
     * @param name of the category
     */
    void setName(String name);

    /**
     * get colour of the category
     * @return String clour of the categorys
     */
    @Basic
    @Column(name = "colour", nullable = false, length = 50)
    String getColour();

    /**
     * set the category colour
     * @param colour of the category
     */
    void setColour(String colour);

    /**
     * get if is a default category
     * @return if the is a defaulte categor ture else false
     */
    @Basic
    @Column(name = "isdefault", nullable = false)
    boolean isIsdefault();

    /**
     * set is default categorie
     * @param isdefault true if is defaulte esle false
     */
    void setIsdefault(boolean isdefault);

    /**
     * get client id
     * @return the client id
     */
    @Basic
    @Column(name = "client_id", nullable = false)
    int getClientId();

    /**
     * set the client id
     * @param clientId id of the client
     */
    void setClientId(int clientId);
}
