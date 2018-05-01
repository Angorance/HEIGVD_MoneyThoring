package bll.model;

import bll.logic.ClientLogic;
import bll.mappers.DAL.DALClientMapper;
import dal.irepositories.IClientRepository;
import dal.orm.IORM;
import dal.orm.PgORM;

/**
 * ClientModel class.
 * Allows the mapping between the DAL entities and the Business Logic.
 * Only implements the constructors, the getters and the setters.
 *
 * @author Daniel Gonzalez Lopez
 * @version 1.0
 */
public class ClientModel {

    private int id;

    private String email;
    private String username;
    private String password;
    private String salt;
    private String key;

    // Flag to know if the email was verified.
    private boolean isActivated;


    // -------------------------------------------------------------------------
    // GETTERS -----------------------------------------------------------------

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
     * Get the password of the client.
     *
     * @return String containing the password.
     */
    public String getPassword() {
        return password;
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
    public String getSalt() {
        return salt;
    }

    /**
     * Get the validation key generated to validate email.
     *
     * @return Key for email validation.
     */
    public String getKey() {
        return key;
    }

    /**
     * Get the activated account flag. If this flag is true, it means the email
     * address of the client was verified.
     *
     * @return Activated account flag.
     */
    public boolean getIsActivated() {
        return isActivated;
    }


    // -------------------------------------------------------------------------
    // SETTERS -----------------------------------------------------------------

    /**
     * Change the email by the given parameter.
     *
     * @param email New email to set.
     */
    public void setEmail (String email) {
        this.email = email;
    }

    /**
     * Change the username by the given parameter.
     *
     * @param username New username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Change the password by the given parameter.
     *
     * @param password New password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Set the id of the client.
     *
     * @param id ID recovered from the database.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Change the isActivated flag to true.
     */
    public void setActivated(boolean newFlag) {
        isActivated = newFlag;
    }

    /**
     * Set the salt for the password's hash.
     * Can be set only one time.
     *
     * @param salt Salt generated to hash the password.
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * Change the key for the email's validation.
     * Key changed for each new email needing validation.
     *
     * @param key Key generated to validate the email.
     */
    public void setKey(String key) {
        this.key = key;
    }
    
    /**
     * TODO
     * @param orm
     */
    protected void createUser(IORM orm) {
        try {
            orm.beginTransaction();
            orm.getClientRepository().addClient(DALClientMapper.toDboPG(this));
	        orm.commit();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    protected void updateUser(IORM orm) {
    	try {
    		orm.beginTransaction();
    		orm.getClientRepository().update(DALClientMapper.toDboPG(this));
    		orm.commit();
    		
	    } catch (Exception e) {
		    System.out.println(e);
	    }
    }
}
