/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pgsqlite.entities.pgsql;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ZEED
 */
@Entity
@Table(name = "Voiture")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Voiture.findAll", query = "SELECT v FROM Voiture v")
    , @NamedQuery(name = "Voiture.findById", query = "SELECT v FROM Voiture v WHERE v.id = :id")
    , @NamedQuery(name = "Voiture.findByUsername", query = "SELECT v FROM Voiture v WHERE v.username = :username")
    , @NamedQuery(name = "Voiture.findByEmail", query = "SELECT v FROM Voiture v WHERE v.email = :email")
    , @NamedQuery(name = "Voiture.findByPassword", query = "SELECT v FROM Voiture v WHERE v.password = :password")
    , @NamedQuery(name = "Voiture.findByIsactivated", query = "SELECT v FROM Voiture v WHERE v.isactivated = :isactivated")
    , @NamedQuery(name = "Voiture.findByActivationkey", query = "SELECT v FROM Voiture v WHERE v.activationkey = :activationkey")
    , @NamedQuery(name = "Voiture.findBySalt", query = "SELECT v FROM Voiture v WHERE v.salt = :salt")})
public class Voiture implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "isactivated")
    private boolean isactivated;
    @Column(name = "activationkey")
    private String activationkey;
    @Basic(optional = false)
    @Column(name = "salt")
    private String salt;

    public Voiture() {
    }

    public Voiture(Integer id) {
        this.id = id;
    }

    public Voiture(Integer id, String username, String email, String password, boolean isactivated, String salt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.isactivated = isactivated;
        this.salt = salt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsactivated() {
        return isactivated;
    }

    public void setIsactivated(boolean isactivated) {
        this.isactivated = isactivated;
    }

    public String getActivationkey() {
        return activationkey;
    }

    public void setActivationkey(String activationkey) {
        this.activationkey = activationkey;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Voiture)) {
            return false;
        }
        Voiture other = (Voiture) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pgsqlite.entities.pgsql.Voiture[ id=" + id + " ]";
    }
    
}
