package dal.ientites;

import javax.persistence.Basic;
import javax.persistence.Column;

/**
 * IDALBankaccountEntity interface.
 * interface who represent a Bankaccount entity
 *
 * @author Guillaume Zaretti
 * @version 1.2
 */
public interface IDALBankaccountEntity {
	
	/**
	 * get the bankaccount id
	 *
	 * @return int id of the bankaccount
	 */
	int getId();
	
	
	/**
	 * set the bank account id
	 *
	 * @param id id of the bankaccount
	 */
	void setId(int id);
	
	/**
	 * get the bankaccount name
	 *
	 * @return String teturn the name of the bankaccount
	 */
	String getName();
	
	/**
	 * set the bankaccount name
	 *
	 * @param name the name of bankaccount
	 */
	void setName(String name);
	
	/**
	 * get the bank name of the bankaccount
	 *
	 * @return
	 */
	@Basic
	@Column(name = "NAMEBANK", nullable = true, length = 50)
	String getNamebank();
	
	/**
	 * set the bank name of the bankaccount
	 *
	 * @param namebank
	 */
	void setNamebank(String namebank);
	
	/**
	 * get the type of bankaccount
	 *
	 * @return the type of bankaccount
	 */
	@Basic
	@Column(name = "TYPEACCOUNT", nullable = false, length = 100)
	String getTypeaccount();
	
	/**
	 * set the type of bankaccount
	 *
	 * @param typeaccount type of the bankaccount
	 */
	void setTypeaccount(String typeaccount);
	
	/**
	 * get amount of the bank account
	 *
	 * @return the amount of the bankaccount
	 */
	@Basic
	@Column(name = "AMOUNT", nullable = false, precision = 0)
	double getAmount();
	
	/**
	 * set the bankaccount amount
	 *
	 * @param amount of the bankaccount
	 */
	void setAmount(double amount);
	
	/**
	 * get is default bank account
	 *
	 * @return boolean if the bankaccount is an account by default true, otherwise
	 * 		false
	 */
	@Basic
	@Column(name = "ISDEFAULT", nullable = false)
	boolean isIsdefault();
	
	/**
	 * set if the defautl bankaccount
	 *
	 * @param isdefault
	 */
	void setIsdefault(boolean isdefault);
	
	/**
	 * get if the bankaccount isVisible
	 *
	 * @return boolean if the bankaccount is visible return true else
	 * 		return false
	 */
	@Basic
	@Column(name = "ISVISIBLE", nullable = false)
	boolean isIsvisible();
	
	/**
	 * set if the bankaccount isVisible
	 *
	 * @param isvisible if the bankaccount is visible = true esle isvisible =
	 * 		false
	 */
	void setIsvisible(boolean isvisible);
	
	/**
	 * get the client id
	 *
	 * @return id of the client
	 */
	@Basic
	@Column(name = "CLIENT_ID", nullable = false)
	int getClientId();
	
	/**
	 * set the client id
	 *
	 * @param clientId id of the client
	 */
	void setClientId(int clientId);
	
}
