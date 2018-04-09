package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "User", schema = "", catalog = "")
public class UserEntity {
    private int id;
    private String username;
    private String email;
    private String password;
    private int isActivated;
    private String activationKey;
    private String salt;

    public UserEntity(String username, String email, String password, int isActivated, String activationKey,
                      String salt){
        this.username = username;
        this.email = email;
        this.password = password;
        this.isActivated = isActivated;
        this.activationKey = activationKey;

    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username", nullable = false, length = -1)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "email", nullable = false, length = -1)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password", nullable = false, length = -1)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "isActivated", nullable = false)
    public int getIsActivated() {
        return isActivated;
    }

    public void setIsActivated(int isActivated) {
        this.isActivated = isActivated;
    }

    @Basic
    @Column(name = "activationKey", nullable = true, length = -1)
    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    @Basic
    @Column(name = "salt", nullable = false, length = -1)
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id == that.id &&
                isActivated == that.isActivated &&
                Objects.equals(username, that.username) &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password) &&
                Objects.equals(activationKey, that.activationKey) &&
                Objects.equals(salt, that.salt);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, username, email, password, isActivated, activationKey, salt);
    }
}
