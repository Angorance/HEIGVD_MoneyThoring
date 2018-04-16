package dal.ientites;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;

public interface IDALCategoryEntity {
    @Id
    @Column(name = "id", nullable = false)
    int getId();

    void setId(int id);

    @Basic
    @Column(name = "name", nullable = false, length = 50)
    String getName();

    void setName(String name);

    @Basic
    @Column(name = "colour", nullable = false, length = 50)
    String getColour();

    void setColour(String colour);

    @Basic
    @Column(name = "isdefault", nullable = false)
    boolean isIsdefault();

    void setIsdefault(boolean isdefault);

    @Basic
    @Column(name = "client_id", nullable = false)
    int getClientId();

    void setClientId(int clientId);
}
