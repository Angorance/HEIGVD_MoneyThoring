package dal.entities.pgsql;

import dal.ientites.IDALClientEntity;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "client", schema = "moneythoring", catalog = "moneythoring")
public class ClientPgEntity implements IDALClientEntity {
    private int id;
    private String username;
    private String email;
    private String password;
    private boolean isactivated;
    private String activationkey;
    private String salt;

    /**
     * {@inheritDoc}
     */
    @Override
    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(int id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Basic
    @Column(name = "username", nullable = false, length = 50)
    public String getUsername() {
        return username;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Basic
    @Column(name = "email", nullable = false, length = 100)
    public String getEmail() {
        return email;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Basic
    @Column(name = "password", nullable = false, length = 250)
    public String getPassword() {
        return password;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Basic
    @Column(name = "isactivated", nullable = false)
    public boolean getIsactivated() {
        return isactivated;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setIsactivated(boolean isactivated) {
        this.isactivated = isactivated;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Basic
    @Column(name = "activationkey", nullable = true, length = 50)
    public String getActivationkey() {
        return activationkey;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setActivationkey(String activationkey) {
        this.activationkey = activationkey;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Basic
    @Column(name = "salt", nullable = false, length = 50)
    public String getSalt() {
        return salt;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientPgEntity that = (ClientPgEntity) o;
        return id == that.id &&
                isactivated == that.isactivated &&
                Objects.equals(username, that.username) &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password) &&
                Objects.equals(activationkey, that.activationkey) &&
                Objects.equals(salt, that.salt);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {

        return Objects.hash(id, username, email, password, isactivated, activationkey, salt);
    }
}
