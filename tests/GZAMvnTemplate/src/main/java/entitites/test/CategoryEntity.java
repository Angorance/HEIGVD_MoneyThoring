package entitites.test;

import javax.persistence.*;

@Entity
@Table(name = "category", schema = "public", catalog = "moneythoring")
public class CategoryEntity {
    private int id;
    private String name;
    private String colour;
    private boolean isdefault;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryEntity that = (CategoryEntity) o;

        if (id != that.id) return false;
        if (isdefault != that.isdefault) return false;
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
        return result;
    }
}
