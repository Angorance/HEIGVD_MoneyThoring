package mt.jpajpa.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "client", schema = "moneythoring", catalog = "moneythoring")
public class ClientEntity {
    private int id;
    private String username;
    private String email;
    private String password;
    private boolean isactivated;
    private String activationkey;
    private String salt;
    private Collection<BankaccountEntity> bankaccountsById;
    private Collection<BudgetEntity> budgetsById;
    private Collection<CategoryEntity> categoriesById;
    private Collection<DebtEntity> debtsById;
    private Collection<DebtEntity> debtsById_0;
    private Collection<SharedbudgetEntity> sharedbudgetsById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 50)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 50)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "isactivated", nullable = false)
    public boolean isIsactivated() {
        return isactivated;
    }

    public void setIsactivated(boolean isactivated) {
        this.isactivated = isactivated;
    }

    @Basic
    @Column(name = "activationkey", nullable = true, length = 50)
    public String getActivationkey() {
        return activationkey;
    }

    public void setActivationkey(String activationkey) {
        this.activationkey = activationkey;
    }

    @Basic
    @Column(name = "salt", nullable = false, length = 50)
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

        ClientEntity that = (ClientEntity) o;

        if (id != that.id) return false;
        if (isactivated != that.isactivated) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (activationkey != null ? !activationkey.equals(that.activationkey) : that.activationkey != null)
            return false;
        if (salt != null ? !salt.equals(that.salt) : that.salt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (isactivated ? 1 : 0);
        result = 31 * result + (activationkey != null ? activationkey.hashCode() : 0);
        result = 31 * result + (salt != null ? salt.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "clientByClientId")
    public Collection<BankaccountEntity> getBankaccountsById() {
        return bankaccountsById;
    }

    public void setBankaccountsById(Collection<BankaccountEntity> bankaccountsById) {
        this.bankaccountsById = bankaccountsById;
    }

    @OneToMany(mappedBy = "clientByClientId")
    public Collection<BudgetEntity> getBudgetsById() {
        return budgetsById;
    }

    public void setBudgetsById(Collection<BudgetEntity> budgetsById) {
        this.budgetsById = budgetsById;
    }

    @OneToMany(mappedBy = "clientByClientId")
    public Collection<CategoryEntity> getCategoriesById() {
        return categoriesById;
    }

    public void setCategoriesById(Collection<CategoryEntity> categoriesById) {
        this.categoriesById = categoriesById;
    }

    @OneToMany(mappedBy = "clientByClientId")
    public Collection<DebtEntity> getDebtsById() {
        return debtsById;
    }

    public void setDebtsById(Collection<DebtEntity> debtsById) {
        this.debtsById = debtsById;
    }

    @OneToMany(mappedBy = "clientByClientId1")
    public Collection<DebtEntity> getDebtsById_0() {
        return debtsById_0;
    }

    public void setDebtsById_0(Collection<DebtEntity> debtsById_0) {
        this.debtsById_0 = debtsById_0;
    }

    @OneToMany(mappedBy = "clientByClientId")
    public Collection<SharedbudgetEntity> getSharedbudgetsById() {
        return sharedbudgetsById;
    }

    public void setSharedbudgetsById(Collection<SharedbudgetEntity> sharedbudgetsById) {
        this.sharedbudgetsById = sharedbudgetsById;
    }
}
