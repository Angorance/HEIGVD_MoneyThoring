package dal.ientites;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;

/**
 * IDALClientEntity interface.
 * interface who represent a client entity
 *
 * @author Guillaume Zaretti
 * @version 1.2
 */
public interface IDALClientEntity {
	
	/**
	 * get the client id
	 *
	 * @return the client id
	 */
	@Id
	@Column(name = "id", nullable = false)
	int getId();
	
	/**
	 * set the client id
	 *
	 * @param id of the client
	 */
	void setId(int id);
	
	/**
	 * get username of the client
	 *
	 * @return username of the client
	 */
	@Basic
	@Column(name = "username", nullable = false, length = 50)
	String getUsername();
	
	/**
	 * set username of the client
	 *
	 * @param username of the client
	 */
	void setUsername(String username);
	
	/**
	 * get email of the client
	 *
	 * @return the mail of the client
	 */
	@Basic
	@Column(name = "email", nullable = false, length = 100)
	String getEmail();
	
	/**
	 * set mail of the client
	 *
	 * @param email of the client
	 */
	void setEmail(String email);
	
	/**
	 * get the password of the client
	 *
	 * @return the client password
	 */
	@Basic
	@Column(name = "password", nullable = false, length = 250)
	String getPassword();
	
	/**
	 * set the password of the client
	 *
	 * @param password of the client
	 */
	void setPassword(String password);
	
	/**
	 * get if the client is activated
	 *
	 * @return boolean true if the client is activated else fales
	 */
	@Basic
	@Column(name = "isactivated", nullable = false)
	boolean getIsactivated();
	
	/**
	 * set if the client is activated
	 *
	 * @param isactivated true if the client is activated else fales
	 */
	void setIsactivated(boolean isactivated);
	
	/**
	 * get activation key
	 *
	 * @return the activation key
	 */
	@Basic
	@Column(name = "activationkey", nullable = true, length = 50)
	String getActivationkey();
	
	/**
	 * set the activation key
	 *
	 * @param activationkey of the client
	 */
	void setActivationkey(String activationkey);
	
	/**
	 * get salt
	 *
	 * @return the salt of the client
	 */
	@Basic
	@Column(name = "salt", nullable = false, length = 50)
	String getSalt();
	
	/**
	 * set salt of the client
	 *
	 * @param salt of the client
	 */
	void setSalt(String salt);
}
