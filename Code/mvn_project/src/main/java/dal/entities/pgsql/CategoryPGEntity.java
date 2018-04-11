package dal.entities.pgsql;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "category", schema = "moneythoring", catalog = "moneythoring")
public class CategoryPGEntity {
    private int id;
    private String name;
    private String colour;
    private boolean isdefault;
    private int clientId;
    private Collection<CategoriesbudgetPGEntity> categoriesbudgetsById;
    private ClientPGEntity clientByClientId;
    private Collection<IotransactionPGEntity> iotransactionsById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "colour", nullable = false, length = 50)
    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    @Basic
    @Column(name = "isdefault", nullable = false)
    public boolean isIsdefault() {
        return isdefault;
    }

    public void setIsdefault(boolean isdefault) {
        this.isdefault = isdefault;
    }

    @Basic
    @Column(name = "client_id", nullable = false)
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryPGEntity that = (CategoryPGEntity) o;

        if (id != that.id) return false;
        if (isdefault != that.isdefault) return false;
        if (clientId != that.clientId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (colour != null ? !colour.equals(that.colour) : that.colour != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (colour != null ? colour.hashCode() : 0);
        result = 31 * result + (isdefault ? 1 : 0);
        result = 31 * result + clientId;
        return result;
    }

    @OneToMany(mappedBy = "categoryByCategoryId")
    public Collection<CategoriesbudgetPGEntity> getCategoriesbudgetsById() {
        return categoriesbudgetsById;
    }

    public void setCategoriesbudgetsById(Collection<CategoriesbudgetPGEntity> categoriesbudgetsById) {
        this.categoriesbudgetsById = categoriesbudgetsById;
    }

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    public ClientPGEntity getClientByClientId() {
        return clientByClientId;
    }

    public void setClientByClientId(ClientPGEntity clientByClientId) {
        this.clientByClientId = clientByClientId;
    }

    @OneToMany(mappedBy = "categoryByCategoryId")
    public Collection<IotransactionPGEntity> getIotransactionsById() {
        return iotransactionsById;
    }

    public void setIotransactionsById(Collection<IotransactionPGEntity> iotransactionsById) {
        this.iotransactionsById = iotransactionsById;
    }
}
