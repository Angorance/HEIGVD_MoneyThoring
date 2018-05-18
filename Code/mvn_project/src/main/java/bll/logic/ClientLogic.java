package bll.logic;

import bll.mappers.DAL.*;
import bll.model.ClientModel;
import bll.model.SharedBudgetModel;
import dal.dalexception.DALException;
import dal.ientites.*;
import dal.irepositories.IClientRepository;
import dal.orm.IORM;
import dal.orm.MasterORM;
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
 * @version 2.0
 */
public class ClientLogic extends ClientModel {
	
	private ArrayList<BankAccountLogic> bankAccounts = new ArrayList<>();
	private ArrayList<CategoryLogic> categories = new ArrayList<>();
	private ArrayList<BudgetLogic> budgets = new ArrayList<>();
	private ArrayList<DebtLogic> debts = new ArrayList<>();
	
	private boolean online;
	
	private ClientLogic() {}
	
	public void setOnline(boolean online) {
		
		this.online = online;
	}
	
	public boolean isOnline() {
		
		return online;
	}
	
	public BudgetLogic getBudget(int budgetId) {
		
		for(BudgetLogic budget : budgets) {
			
			if(budget.getId() == budgetId) {
				return budget;
			}
		}
		
		return null;
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
	
	public List<ClientModel> getAllUsers() {
		
		IORM orm = MasterORM.getInstance().getORM();
		
		try {
			orm.beginTransaction();
			
			IClientRepository repo = orm.getClientRepository();
			
			return DALClientMapper.toBos(repo.getClients());
			
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ArrayList<DebtLogic> getDebts() {
		
		return debts;
	}
	
	// SETTERS
	
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
	 * Add a budget to the client.
	 *
	 * @param bu budget to add.
	 */
	public void addBudget(BudgetLogic bu) {
		
		budgets.add(bu);
		bu.setClientID(getId());
	}
	
	/**
	 * Remove a budget from the client.
	 *
	 * @param bu budget to remove.
	 */
	public void removeBudget(BudgetLogic bu) {
		
		budgets.remove(bu);
	}
	
	/**
	 * Add a debt to the client.
	 *
	 * @param de Debt to add.
	 */
	public void addDebt(DebtLogic de) {
		
		debts.add(de);
	}
	
	/**
	 * Remove the debt from the client.
	 *
	 * @param de Debt to remove.
	 */
	public void removeDebt(DebtLogic de) {
		
		debts.remove(de);
	}
	
	// SUPPRESSORS
	
	/**
	 * Suppress the client's account and his data.
	 */
	public void supp() {
		
		IORM orm = MasterORM.getInstance().getORM();
		
		try {
			orm.beginTransaction();
			
			IClientRepository repo = orm.getClientRepository();
			repo.delete(getId());
			
			orm.commit();
			
		} catch (DALException e) {
			e.printStackTrace();
		}
	}
	
	
	protected void setDataFromDB() {
		
		try {
			IORM orm = MasterORM.getInstance().getORM();
			
			orm.beginTransaction();
			
			// Bank accounts
			List<IDALBankaccountEntity> ba = orm.getBankaccountRepository()
					.getBankAccoutsByClient(getId());
			
			// Get the bank accounts
			DALBankaccountMapper.toBos(ba);
			
			
			// Categories
			List<IDALCategoryEntity> cat = orm.getCategoryRepository()
					.getCategoriesByClientId(getId());
			
			// Get the categories
			DALCategoryMapper.toBos(cat);
			
			
			// Budgets
			List<IDALBudgetEntity> bu = orm.getBudgetRepository()
					.getBudgetsByClient(getId());
			
			// Get the categories of the budgets
			for (BudgetLogic b : DALBudgetMapper.toBos(bu)) {
				b.setDataFromDB(orm);
			}
			
			
			if (isOnline()) {
				// Shared budgets
				List<IDALSharedbudgetEntity> sb = orm
						.getSharedBudgetRepository()
						.getSharedbudgetByClient(getId());
				
				// Get the shared budgets
				for(SharedBudgetModel sbm : DALSharedBudgetMapper.toBos(sb)) {
				IDALBudgetEntity budget = orm.getBudgetRepository().getBudget(sbm.getBudgetID());
				BudgetLogic b = DALBudgetMapper.toBo(budget);
				b.setDataFromDB(orm);
			}
			}
			
			// Debts
			List<IDALDebtEntity> de = orm.getDebtRepository()
					.getDebtsByClient(getId());
			
			// Get the debts
			DALDebtMapper.toBos(de);
			
			
			// Update the transactions
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
	public void update(boolean isActivated) {
		
		setActivated(isActivated);
		
		updateUser(MasterORM.getInstance().getORM());
	}
	
	public void wipe() {
		
		for (BankAccountLogic ba : bankAccounts) {
			ba.wipe();
		}
		
		bankAccounts.clear();
		categories.clear();
		budgets.clear();
		debts.clear();
		
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
