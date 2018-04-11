package bll;

/**
 * @authors Daniel Gonzalez Lopez
 */
public class User {

    private int id;

    private String email;
    private String username;
    private String password;

    private boolean isActivated;

    private User() {

    }

    // SETTERS

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // GETTERS

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    // SUPPRESSORS

    public void supp() {
        // TODO - Suppress the account !
    }


    // INNER CLASS

    public class Connect {

    }
}
