package bll.model;

import bll.mappers.DAL.DALIOTransactionMapper;
import dal.irepositories.IIotransactionRepository;
import dal.orm.IORM;

import java.sql.Date;

/**
 * IOTransactionModel class.
 * Allows the mapping between the DAL entities and the Business Logic.
 * Only implements the constructors, the getters and the setters.
 *
 * @author Daniel Gonzalez Lopez
 * @version 1.3
 */
public class IOTransactionModel {
	
	private int id;
	private int BankAccountID;
	private int categoryID;
	private Integer budgetID;
	
	private double amount;
	
	private String name;
	private String description;
	private Date date;
	private String currency;
	
	private boolean isIncome;
	
	
	// Protected default constructor. Avoid instances creation outside package.
	protected IOTransactionModel() {}
	
	
	/**
	 * Construct an instance with given parameters.
	 *
	 * @param amount Amount of the transaction.
	 * @param name Name of the transaction.
	 * @param description Description of the transaction.
	 * @param currency Currency used.
	 * @param isIncome Flag to know if it is an income.
	 */
	public IOTransactionModel(double amount, String name, String description,
			String currency, boolean isIncome) {
		
		this.amount = amount;
		this.name = name;
		this.description = description;
		this.currency = currency;
		this.isIncome = isIncome;
	}
	
	/**
	 * Create a transaction entry for the user into the database.
	 *
	 * @param orm ORM instance to use.
	 */
	public void createIOTransaction(IORM orm) {
		
		try {
			
			orm.beginTransaction();
			
			IIotransactionRepository repo = orm.getIotransactionRepository();
			setId(repo.addIotransaction(DALIOTransactionMapper.toDbo(this)));
			
			orm.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Update transaction entry with new data.
	 *
	 * @param orm ORM instance to use.
	 */
	public void updateIOTransaction(IORM orm) {
		
		try {
			
			orm.beginTransaction();
			
			IIotransactionRepository repo = orm.getIotransactionRepository();
			repo.update(DALIOTransactionMapper.toDbo(this));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// -------------------------------------------------------------------------
	// GETTERS -----------------------------------------------------------------
	
	/**
	 * Get the id of the transaction.
	 *
	 * @return ID of transaction.
	 */
	public int getId() {
		
		return id;
	}
	
	/**
	 * Get the amount of the transaction.
	 *
	 * @return Amount of the transaction.
	 */
	public double getAmount() {
		
		return amount;
	}
	
	/**
	 * Get the name of the transaction.
	 *
	 * @return Name of the transaction.
	 */
	public String getName() {
		
		return name;
	}
	
	/**
	 * Get the description of the transaction.
	 *
	 * @return Description of the transaction.
	 */
	public String getDescription() {
		
		return description;
	}
	
	/**
	 * Get the date of the transaction.
	 *
	 * @return Date of the transaction.
	 */
	public Date getDate() {
		
		return date;
	}
	
	/**
	 * Get the currency used for the transaction.
	 *
	 * @return Currency of the transaction.
	 */
	public String getCurrency() {
		
		return currency;
	}
	
	/**
	 * Get the flag whether the transaction was an income or an outgo.
	 *
	 * @return Flag to know if it is an income or an outgo.
	 */
	public boolean isIncome() {
		
		return isIncome;
	}
	
	/**
	 * Get the bank account ID of the transaction.
	 *
	 * @return Bank account ID of the transaction.
	 */
	public int getBankAccountID() {
		
		return BankAccountID;
	}
	
	/**
	 * Get the category ID of the transaction.
	 *
	 * @return ID of the category.
	 */
	public int getCategoryID() {
		
		return categoryID;
	}
	
	/**
	 * Get the budget ID of the category.
	 *
	 * @return ID of the budget.
	 */
	public Integer getBudgetID() {
		
		return budgetID;
	}
	
	
	// -------------------------------------------------------------------------
	// SETTERS -----------------------------------------------------------------
	
	/**
	 * Set the id of the transaction.
	 *
	 * @param id New ID to set.
	 */
	public void setId(int id) {
		
		this.id = id;
	}
	
	/**
	 * Set the amount of the transaction.
	 *
	 * @param amount New amount to set.
	 */
	public void setAmount(double amount) {
		
		this.amount = amount;
	}
	
	/**
	 * Set the name of the transaction.
	 *
	 * @param name New name to set.
	 */
	public void setName(String name) {
		
		this.name = name;
	}
	
	/**
	 * Set the description of the transaction.
	 *
	 * @param description New description of the transaction.
	 */
	public void setDescription(String description) {
		
		this.description = description;
	}
	
	/**
	 * Set the date of the transaction.
	 *
	 * @param date New date of the transaction.
	 */
	public void setDate(Date date) {
		
		this.date = date;
	}
	
	/**
	 * Set the currency of the transaction.
	 *
	 * @param currency New currency to set.
	 */
	public void setCurrency(String currency) {
		
		this.currency = currency;
	}
	
	/**
	 * Set the flag for the income / outgo info.
	 *
	 * @param income New income flag to set.
	 */
	public void setIncome(boolean income) {
		
		isIncome = income;
	}
	
	/**
	 * Set the bank account ID of the transaction.
	 *
	 * @param bankAccountID New bank account ID to set.
	 */
	public void setBankAccountID(int bankAccountID) {
		
		BankAccountID = bankAccountID;
	}
	
	/**
	 * Set the category ID of the transaction.
	 *
	 * @param categoryID New ID to set.
	 */
	public void setCategoryID(int categoryID) {
		
		this.categoryID = categoryID;
	}
	
	/**
	 * Set the budget ID of the transaction.
	 *
	 * @param budgetID New ID to set.
	 */
	public void setBudgetID(Integer budgetID) {
		
		this.budgetID = budgetID;
	}
}
