package entities.test1;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "category", schema = "public", catalog = "moneythoring")
public class CategoryEntity {
    private int id;
    private String name;
    private String colour;
    private boolean isdefault;
    private int clientId;
    private Collection<CategoriesbudgetEntity> categoriesbudgetsById;
    private ClientEntity clientByClientId;
    private Collection<IotransactionEntity> iotransactionsById;

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

        CategoryEntity that = (CategoryEntity) o;

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
    public Collection<CategoriesbudgetEntity> getCategoriesbudgetsById() {
        return categoriesbudgetsById;
    }

    public void setCategoriesbudgetsById(Collection<CategoriesbudgetEntity> categoriesbudgetsById) {
        this.categoriesbudgetsById = categoriesbudgetsById;
    }

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    public ClientEntity getClientByClientId() {
        return clientByClientId;
    }

    public void setClientByClientId(ClientEntity clientByClientId) {
        this.clientByClientId = clientByClientId;
    }

    @OneToMany(mappedBy = "categoryByCategoryId")
    public Collection<IotransactionEntity> getIotransactionsById() {
        return iotransactionsById;
    }

    public void setIotransactionsById(Collection<IotransactionEntity> iotransactionsById) {
        this.iotransactionsById = iotransactionsById;
    }
}
