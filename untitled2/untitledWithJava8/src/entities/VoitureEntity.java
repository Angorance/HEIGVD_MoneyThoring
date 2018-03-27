package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Voiture", schema = "main", catalog = "")
public class VoitureEntity {
    private short id;
    private Serializable username;
    private Serializable email;
    private Serializable password;
    private Serializable isActivated;
    private Serializable activationKey;
    private Serializable salt;

    @Id
    @Column(name = "id", nullable = false)
    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 50)
    public Serializable getUsername() {
        return username;
    }

    public void setUsername(Serializable username) {
        this.username = username;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 100)
    public Serializable getEmail() {
        return email;
    }

    public void setEmail(Serializable email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 50)
    public Serializable getPassword() {
        return password;
    }

    public void setPassword(Serializable password) {
        this.password = password;
    }

    @Basic
    @Column(name = "isActivated", nullable = false)
    public Serializable getIsActivated() {
        return isActivated;
    }

    public void setIsActivated(Serializable isActivated) {
        this.isActivated = isActivated;
    }

    @Basic
    @Column(name = "activationKey", nullable = true, length = 50)
    public Serializable getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(Serializable activationKey) {
        this.activationKey = activationKey;
    }

    @Basic
    @Column(name = "salt", nullable = false, length = 50)
    public Serializable getSalt() {
        return salt;
    }

    public void setSalt(Serializable salt) {
        this.salt = salt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoitureEntity that = (VoitureEntity) o;
        return id == that.id &&
                Objects.equals(username, that.username) &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password) &&
                Objects.equals(isActivated, that.isActivated) &&
                Objects.equals(activationKey, that.activationKey) &&
                Objects.equals(salt, that.salt);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, username, email, password, isActivated, activationKey, salt);
    }
}
