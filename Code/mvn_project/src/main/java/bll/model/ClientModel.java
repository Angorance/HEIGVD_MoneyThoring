package bll.model;

/**
 * ClientModel class.
 * Allows the mapping between the DAL entities and the Business Logic.
 * Only implements the constructors, the getters and the setters.
 *
 * @authors Daniel Gonzalez Lopez
 * @version 1.0
 */
public class ClientModel {

    private int id;
    private int salt;
    private int key;

    private String email;
    private String username;
    private String password;

    // Flag to know if the email was verified.
    private boolean isActivated;


    // CONSTRUCTORS

    public ClientModel() { }

    /**
     * Create a ClientModel instance with the given parameters.
     *
     * @param email Email of the client.
     * @param username Username of the client.
     * @param password Password of the client.
     */
    @Deprecated
    public ClientModel(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }


    // GETTERS

    /**
     * Get the email of the client.
     *
     * @return String containing the email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Get the username of the client.
     *
     * @return String containing the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get the ID of the client.
     *
     * @return Int containing the ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Get the salt used for the password's hash.
     *
     * @return Salt used to hash client's password.
     */
    public int getSalt() {
        return salt;
    }

    /**
     * Get the validation key generated to validate email.
     *
     * @return Key for email validation.
     */
    public int getKey() {
        return key;
    }


    // SETTERS

    /**
     * Change the email by the given parameter.
     *
     * @param email New email to set.
     */
    protected void setEmail(String email) {
        this.email = email;
    }

    /**
     * Change the username by the given parameter.
     *
     * @param username New username to set.
     */
    protected void setUsername(String username) {
        this.username = username;
    }

    /**
     * Change the password by the given parameter.
     *
     * @param password New password to set.
     */
    protected void setPassword(String password) {
        this.password = password;
    }

    /**
     * Change the isActivated flag to true.
     */
    protected void setActivated(boolean newFlag) {
        isActivated = newFlag;
    }

    /**
     * Set the salt for the password's hash.
     * Can be set only one time.
     *
     * @param salt Salt generated to hash the password.
     */
    protected void setSalt(int salt) {
        this.salt = salt;
    }

    /**
     * Change the key for the email's validation.
     * Key changed for each new email needing validation.
     *
     * @param key Key generated to validate the email.
     */
    protected void setKey(int key) {
        this.key = key;
    }
}
