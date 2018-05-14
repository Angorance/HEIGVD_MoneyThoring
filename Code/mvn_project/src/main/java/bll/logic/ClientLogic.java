package bll.logic;

import bll.mappers.DAL.DALBankaccountMapper;
import bll.mappers.DAL.DALBudgetMapper;
import bll.mappers.DAL.DALCategoryMapper;
import bll.model.ClientModel;
import dal.ientites.IDALBankaccountEntity;
import dal.ientites.IDALBudgetEntity;
import dal.ientites.IDALCategoryEntity;
import dal.orm.IORM;
import dal.orm.MasterORM;
import dal.orm.PgORM;
import smtp.Mail;

import java.util.ArrayList;
import java.util.List;


/**
 * ClientLogic class.
 * Implements the business logic of the ClientModel. The methods allow to
 * change the attributes of the client like his email, his username or his
 * password. Before changing these attributes, the methods check their integrity
 * to avoid data problems.
 *
 * @author Daniel Gonzalez Lopez
 * @version 1.5
 */
public class ClientLogic extends ClientModel {
	
	private ArrayList<BankAccountLogic> bankAccounts = new ArrayList<>();
	private ArrayList<CategoryLogic> categories = new ArrayList<>();
	private ArrayList<BudgetLogic> budgets = new ArrayList<>();
	
	private boolean online;
	
	private ClientLogic() {}
	
	public void setOnline(boolean online) {
		
		this.online = online;
	}
	
	public boolean isOnline() {
		
		return online;
	}
	
	/**
	 * TODO
	 */
	public static class Instance {
		
		static ClientLogic instance = new ClientLogic();
	}
	
	/**
	 * TODO
	 *
	 * @return
	 */
	public static ClientLogic getInstance() {
		
		return Instance.instance;
	}
	
	
	/**
	 * TODO
	 *
	 * @param email
	 * @param username
	 * @param password
	 */
	public void setClient(String email, String username, String password) {
		
		setEmail(email);
		setUsername(username);
		
		try {
			setPassword(Authentication.hash(password));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (isOnline()) {
			setKey(KeyGenerator.generateKey(12));
			
			/*Send the key*/
			Mail.sendMail(username, email, getKey());
		} else {
			
			setKey(null);
			setActivated(true);
		}
		
		createUser(MasterORM.getInstance().getORM());
	}
	
	// GETTERS
	
	/**
	 * Get the list of bank accounts of the client.
	 *
	 * @return ArrayList of bank accounts.
	 */
	public List<BankAccountLogic> getBankAccounts() {
		
		ArrayList<BankAccountLogic> visibles = new ArrayList<>();
		
		for (BankAccountLogic ba : bankAccounts) {
			if (ba.isVisible()) {
				visibles.add(ba);
			}
		}
		
		return visibles;
	}
	
	public List<CategoryLogic> getCategories() {
		return categories;
	}
	
	public List<BudgetLogic> getBudgets() {
		return budgets;
	}
	
	
	// SETTERS
	
	/**
	 * Change the email of the client by the new one given in parameter.
	 * Before setting the new email, setEmail() verifies its format and sends
	 * a validation key to verify the email. For this purpose, it changes the
	 * flag isValidated.
	 *
	 * @param email New email to set.
	 *
	 * @see ClientModel#setEmail(String)
	 * @see ClientModel#setActivated(boolean)
	 */
	@Override
	public void setEmail(String email) {
		// TODO - check email format
		// TODO - send validation key
		
		super.setEmail(email);
	}
	
	/**
	 * Change the username of the client by the new one given in parameter.
	 * Before setting the new username, setUsername() verifies it is not
	 * already
	 * being used by another client.
	 *
	 * TODO - Logic works if online. If not, when synchronising, createItem
	 * random number ?
	 *
	 * @param username New username to set.
	 *
	 * @see ClientModel#setUsername(String)
	 */
	@Override
	public void setUsername(String username) {
		// TODO - Check if not already used.
		
		super.setUsername(username);
	}
	
	/**
	 * Change the password of the client by the new one given in parameter.
	 * Before setting, it hashes the password with the client salt.
	 *
	 * @param password New password to set.
	 *
	 * @see ClientModel#setPassword(String)
	 */
	@Override
	public void setPassword(String password) {
		
		super.setPassword(password);
	}
	
	/**
	 * Link a bank account to its client.
	 *
	 * @param ba Bank account to createItem to the list.
	 */
	public void addBankAccount(BankAccountLogic ba) {
		
		ba.setClientId(getId());
		bankAccounts.add(ba);
	}
	
	/**
	 * TODO
	 *
	 * @param ca
	 */
	public void addCategory(CategoryLogic ca) {
		
		categories.add(ca);
		ca.setClientId(getId());
	}
	
	public void removeCategory(CategoryLogic ca) {
		
		categories.remove(ca);
	}
	
	/**
	 * TODO
	 *
	 * @param bu
	 */
	public void addBudget(BudgetLogic bu) {
		
		budgets.add(bu);
		bu.setClientID(getId());
	}
	
	
	// SUPPRESSORS
	
	/**
	 * Suppress the client's account and his data.
	 */
	public void supp() {
		// TODO - Suppress the account !
	}
	
	
	protected void setDataFromDB() {
		
		try {
			IORM orm = MasterORM.getInstance().getORM();
			
			orm.beginTransaction();
			
			List<IDALBankaccountEntity> ba = orm.getBankaccountRepository()
					.getBankAccoutsByClient(getId());
			
			List<IDALCategoryEntity> cat = orm.getCategoryRepository()
					.getCategoriesByClientId(getId());
			
			List<IDALBudgetEntity> bu = orm.getBudgetRepository()
					.getBudgetsByClient(getId());
			
			DALBankaccountMapper.toBos(ba);
			DALCategoryMapper.toBos(cat);
			DALBudgetMapper.toBos(bu);
			
			for (BankAccountLogic b : getBankAccounts()) {
				b.setDataFromDB(orm);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		
		String client = "";
		
		client += "Username: " + getUsername() + "\n";
		client += "Email: " + getEmail() + "\n";
		client += "Hash: " + getPassword() + "\n";
		client += "Salt: " + getSalt();
		
		return client;
	}
	
	/**
	 * Update the client into the database.
	 */
	public void updateClientToDB() {
		updateUser(new PgORM());
	}
	
	public void wipe() {
		
		for (BankAccountLogic ba : bankAccounts) {
			ba.wipe();
		}
		
		bankAccounts.clear();
		categories.clear();
		budgets.clear();
		
		setUsername(null);
		setEmail(null);
		setPassword(null);
		setSalt(null);
		setKey(null);
		setActivated(false);
		setId(0);
		
		System.gc();
	}
}
