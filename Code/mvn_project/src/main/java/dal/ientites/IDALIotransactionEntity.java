package dal.ientites;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import java.sql.Date;

/**
 * TODO
 */
public interface IDALIotransactionEntity {
	
	/**
	 * get id of the iotransaction
	 *
	 * @return id of the iotransaction
	 */
	@Id
	@Column(name = "id", nullable = false)
	int getId();
	
	/**
	 * set the iotransaction id
	 *
	 * @param id of the iotransaction
	 */
	void setId(int id);
	
	/**
	 * get the name of the iotransaction
	 *
	 * @return the name of iotransaction
	 */
	@Basic
	@Column(name = "name", nullable = false, length = 50)
	String getName();
	
	/**
	 * set the name of iotransaction
	 *
	 * @param name of the iotransaction
	 */
	void setName(String name);
	
	/**
	 * get desciption of the iotransaction
	 *
	 * @return iotransaction of the description
	 */
	@Basic
	@Column(name = "description", nullable = true, length = 255)
	String getDescription();
	
	/**
	 * set de iotransaction descritpion
	 *
	 * @param description of the iotransactio
	 */
	void setDescription(String description);
	
	/**
	 * get date transaction
	 *
	 * @return the date of the transaction
	 */
	@Basic
	@Column(name = "datetransaction", nullable = false)
	Date getDatetransaction();
	
	/**
	 * set the date transaction of the iotransaction
	 *
	 * @param datetransaction of the iotransaction
	 */
	void setDatetransaction(Date datetransaction);
	
	/**
	 * get the amount of the iotransaction
	 *
	 * @return the amount of the iotransaction
	 */
	@Basic
	@Column(name = "amount", nullable = false, precision = 0)
	double getAmount();
	
	/**
	 * set the amount of the iotransaction
	 *
	 * @param amount of the iotransaction
	 */
	void setAmount(double amount);
	
	/**
	 * get currency iotransaction
	 *
	 * @return the currency iotransaction
	 */
	@Basic
	@Column(name = "currency", nullable = false, length = 50)
	String getCurrency();
	
	/**
	 * set currency iotransaction
	 *
	 * @param currency of the iotransaction
	 */
	void setCurrency(String currency);
	
	/**
	 * get if the iotransction is income
	 *
	 * @return if the iotransaction is income
	 */
	@Basic
	@Column(name = "isincome", nullable = false)
	boolean isIsincome();
	
	/**
	 * set if the iotransaction is income
	 *
	 * @param isincome if the iotransaction is income
	 */
	void setIsincome(boolean isincome);
	
	/**
	 * get category id of the iotransaction
	 *
	 * @return the category id og iotransaction
	 */
	@Basic
	@Column(name = "category_id", nullable = false)
	int getCategoryId();
	
	/**
	 * set category id of the iotransaction
	 *
	 * @param categoryId
	 */
	void setCategoryId(int categoryId);
	
	/**
	 * get the bank account id of the iotransaction
	 *
	 * @return the bank account id of the iotransaction
	 */
	@Basic
	@Column(name = "bankaccount_id", nullable = false)
	int getBankaccountId();
	
	/**
	 * set the iotransaction of the bankaccount id
	 *
	 * @param bankaccountId of the transaction
	 */
	void setBankaccountId(int bankaccountId);
	
	/**
	 * set the budget id of the iotransaction
	 *
	 * @return the budget id of the iotransaction
	 */
	@Basic
	@Column(name = "budget_id", nullable = true)
	Integer getBudgetId();
	
	void setBudgetId(Integer budgetId);
}
