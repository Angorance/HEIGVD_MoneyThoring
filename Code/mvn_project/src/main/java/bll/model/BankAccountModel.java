package bll.model;

import bll.mappers.DAL.DALBankaccountMapper;
import dal.irepositories.IBankaccountRepository;
import dal.orm.IORM;
import dal.orm.PgORM;

/**
 * BankAccountModel class.
 * Allows the mapping between the DAL entities and the Business Logic.
 * Only implements the constructors, the getters and the setters.
 *
 * @author Daniel Gonzalez Lopez
 * @version 1.2
 */
public class BankAccountModel {
	
	private int id;
	private int clientID;
	
	private double amount;
	
	private String name;
	private String bankName;
	private String type;
	
	// Flag to know if a bank account is visible or not.
	// Suppressed bank accounts are set not visible, so that we still have the
	// data for the views, but the user can't access it.
	private boolean isVisible;
	private boolean isDefault;
	
	
	// Protected default constructor. Avoid instances creation outside package.
	protected BankAccountModel() {}
	
	/**
	 * Construct a BankAccountModel instance with the given parameters.
	 *
	 * @param name Name of the account.
	 * @param bankName Name of the bank.
	 * @param type Type of account.
	 * @param amount Starting amount in the account.
	 * @param clientID Client's ID.
	 */
	protected BankAccountModel(String name, String bankName, String type,
			double amount, int clientID) {
		
		this.name = name;
		this.bankName = bankName;
		this.type = type;
		this.amount = amount;
		this.isVisible = true;
		this.clientID = clientID;
	}
	
	/**
	 * Create a bank account entry for the user into the database.
	 *
	 * @param orm ORM instance to use.
	 */
	protected void createBankAccount(IORM orm) {
		
		try {
			
			orm.beginTransaction();
			
			IBankaccountRepository repo = orm.getBankaccountRepository();
			repo.addBankaccount(DALBankaccountMapper.toDboPG(this));
			
			orm.commit();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Update bank account entry with new data.
	 *
	 * @param orm ORM instance to use.
	 */
	protected void updateBankAccount(IORM orm) {
		
		try {
			
			orm.beginTransaction();
			
			IBankaccountRepository repo = orm.getBankaccountRepository();
			repo.update(DALBankaccountMapper.toDboPG(this));
			
			orm.commit();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	// -------------------------------------------------------------------------
	// GETTERS -----------------------------------------------------------------
	
	/**
	 * Get the ID of the bank account.
	 *
	 * @return ID of the bank account.
	 */
	public int getId() {
		
		return id;
	}
	
	/**
	 * Get the name given to the bank account by the client.
	 *
	 * @return Name of the bank account.
	 */
	public String getName() {
		
		return name;
	}
	
	/**
	 * Get the name of the bank.
	 *
	 * @return Name of the bank.
	 */
	public String getBankName() {
		
		return bankName;
	}
	
	/**
	 * Get the kind of bank account it is.
	 *
	 * @return Type of the bank account.
	 */
	public String getType() {
		
		return type;
	}
	
	/**
	 * Get the amount currently in the bank account.
	 *
	 * @return Amount in the bank account.
	 */
	public double getAmount() {
		
		return amount;
	}
	
	/**
	 * Get the value of the default flag.
	 *
	 * @return True if the bank account is set as default, false otherwise.
	 */
	public boolean isDefault() {
		
		return isDefault;
	}
	
	/**
	 * Get the value of the visible flag.
	 *
	 * @return True if the bank account is set to be visible, false otherwise.
	 */
	public boolean isVisible() {
		
		return isVisible;
	}
	
	/**
	 * Get the ID of the client owner.
	 *
	 * @return ID of the client owner.
	 */
	public int getClientId() {
		
		return clientID;
	}
	
	
	// -------------------------------------------------------------------------
	// SETTERS -----------------------------------------------------------------
	
	/**
	 * Change the id of the bank account by the given one.
	 *
	 * @param id New id to set.
	 */
	public void setId(int id) {
		
		this.id = id;
	}
	
	/**
	 * Change the name of the bank account by the given one.
	 *
	 * @param name New name to set.
	 */
	public void setName(String name) {
		
		this.name = name;
	}
	
	/**
	 * Change the name of the bank by the given one.
	 *
	 * @param bankName New bank's name to set.
	 */
	public void setBankName(String bankName) {
		
		this.bankName = bankName;
	}
	
	/**
	 * Change the type of the bank account by the given one.
	 *
	 * @param type New type to set.
	 */
	public void setType(String type) {
		
		this.type = type;
	}
	
	/**
	 * Change the amount in the bank account.
	 *
	 * @param amount New amount to set.
	 */
	public void setAmount(double amount) {
		
		this.amount = amount;
	}
	
	/**
	 * Toggle the isDefault flag.
	 *
	 * @param aDefault New flag to set.
	 */
	public void setDefault(boolean aDefault) {
		
		isDefault = aDefault;
	}
	
	/**
	 * Toggle the isVisible flag.
	 *
	 * @param visible New flag to set.
	 */
	public void setVisible(boolean visible) {
		
		isVisible = visible;
	}
	
	/**
	 * Change the id of the client owner by the given one.
	 *
	 * @param clientID New client ID to set.
	 */
	public void setClientId(int clientID) {
		
		this.clientID = clientID;
	}
}
