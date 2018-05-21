package bll.logic;

import bll.mappers.DAL.DALIOTransactionMapper;
import bll.model.BankAccountModel;
import dal.dalexception.DALException;
import dal.ientites.IDALIotransactionEntity;
import dal.orm.IORM;
import dal.orm.MasterORM;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * BankAccountLogic class.
 * Implements the business logic of the BankAccountModel.
 * Implements methods to create, change and delete bank accounts and some
 * methods concerning their transactions.
 * Before changing these attributes, the methods check their integrity
 * to avoid data problems.
 *
 * @author Daniel Gonzalez Lopez
 * @version 2.0
 */
public class BankAccountLogic extends BankAccountModel {
	
	private static BankAccountLogic defaultBankAccount;
	
	private ArrayList<IOTransactionLogic> transactions = new ArrayList<>();
	
	private HashMap<Integer, ArrayList<IOTransactionLogic>[]> transactionsByDate
			= new HashMap<>();
	
	
	/**
	 * Construct the instance adding the bank account to the client instance.
	 * Only used when recovering data from databases.
	 */
	public BankAccountLogic() {
		
		ClientLogic.getInstance().addBankAccount(this);
	}
	
	/**
	 * Construct the instance adding the bank account to the client instance and
	 * creating the input on the database.
	 *
	 * @param name
	 * @param bankName
	 * @param type
	 * @param amount
	 * @param isDefault If it has to be default account or not.
	 */
	public BankAccountLogic(String name, String bankName, String type,
			double amount, boolean isDefault, int clientID) {
		
		super(name, bankName, type, amount, clientID);
		
		changeDefault(isDefault);
		
		ClientLogic.getInstance().addBankAccount(this);
		
		// Create entry on the database.
		createBankAccount(MasterORM.getInstance().getORM());
	}
	
	/**
	 * Add a transaction to the hashmap of transactionsByDate.
	 *
	 * @param tl Transaction to be added.
	 */
	private void addToHashMap(IOTransactionLogic tl) {
		
		// We recover date info
		LocalDate date = tl.getDate().toLocalDate();
		int year = date.getYear();
		int month = date.getMonthValue() - 1;
		
		// If the key is already contained
		if (transactionsByDate.containsKey(year)) {
			
			// We simply add it to the right month tab.
			transactionsByDate.get(year)[month].add(tl);
		} else {
			
			// Otherwise we declare a new ArrayList tab.
			ArrayList<IOTransactionLogic>[] tab = new ArrayList[12];
			
			// We initialise it
			for (int i = 0; i < 12; ++i) {
				tab[i] = new ArrayList<>();
			}
			
			// Then we put it in the hashmap
			transactionsByDate.put(year, tab);
			
			// And add the transaction in it.
			tab[month].add(tl);
		}
	}
	
	/**
	 * Gets a representation of the bank account (it's name) as a String.
	 *
	 * @return String of the representation.
	 */
	@Override
	public String toString() {
		
		return getName();
	}
	
	/**
	 * Get the default bank account for the user.
	 *
	 * @return BankAccountLogic instance of the default bank account.
	 */
	public static BankAccountLogic getDefaultBankAccount() {
		
		return defaultBankAccount;
	}
	
	/**
	 * Increment the bank account by the value of the parameter.
	 * If the value is negative, it decrements.
	 *
	 * @param io Input to add to the amount.
	 */
	private void updateAmount(double io) {
		
		setAmount(getAmount() + io);
		updateBankAccount(MasterORM.getInstance().getORM());
	}
	
	/**
	 * Add the transaction to the transaction list of the bank account.
	 *
	 * @param transaction New transaction to createItem to the list.
	 */
	protected void addNewTransaction(IOTransactionLogic transaction) {
		
		addTransaction(transaction);
		transaction.setBankAccountID(getId());
		updateAmount(transaction.getAmount());
	}
	
	/**
	 * Remove a transaction from all lists and hashmaps and update the amount of
	 * the bank account.
	 *
	 * @param transaction Transaction to be removed.
	 */
	public void removeTransaction(IOTransactionLogic transaction) {
		
		LocalDate date = transaction.getDate().toLocalDate();
		int year = date.getYear();
		int month = date.getMonthValue() - 1;
		
		transactions.remove(transaction);
		transactionsByDate.get(year)[month].remove(transaction);
		
		updateAmount(transaction.getAmount() * -1);
	}
	
	/**
	 * Add the transaction to the transaction list of the bank account.
	 *
	 * @param transaction New transaction to add to the list.
	 */
	public void addTransaction(IOTransactionLogic transaction) {
		
		transactions.add(transaction);
		addToHashMap(transaction);
	}
	
	/**
	 * Update the date and thus the place in the hashmap of the transaction.
	 *
	 * @param transaction Transaction to be moved.
	 * @param previous Previous date of the transaction.
	 */
	public void updateTransaction(IOTransactionLogic transaction,
			LocalDate previous) {
		
		int year = previous.getYear();
		int month = previous.getMonthValue() - 1;
		
		transactionsByDate.get(year)[month].remove(transaction);
		
		addToHashMap(transaction);
	}
	
	/**
	 * Get the list of transactions for this bank account.
	 *
	 * @return List of transactions.
	 */
	public HashMap<Integer, ArrayList<IOTransactionLogic>[]> getTransactions() {
		
		return transactionsByDate;
	}
	
	/**
	 * Recover the bank account using its ID.
	 *
	 * @param bankAccountID ID of the bank account wanted.
	 *
	 * @return BankAccountLogic instance for the corresponding ID.
	 */
	public static BankAccountLogic getBankAccountByID(int bankAccountID) {
		
		// For all bank accounts
		for (BankAccountLogic ba : ClientLogic.getInstance()
				.getBankAccounts()) {
			
			// If we found the given ID, we return the instance
			if (ba.getId() == bankAccountID) {
				return ba;
			}
		}
		
		// Otherwise we return null.
		return null;
	}
	
	/**
	 * "Suppress" the bank account. It simply makes it invisible for the user.
	 * Update it on the database.
	 */
	public void supp() {
		
		setVisible(false);
		setDefault(false);
		updateBankAccount(MasterORM.getInstance().getORM());
	}
	
	/**
	 * Change the default bank account.
	 * If the user wants to set the bank account as default, it founds, if it
	 * exists, the current default, set it to false and set the new default.
	 * Otherwise, it simply set false for the default flag.
	 *
	 * @param isDefault
	 */
	public void changeDefault(boolean isDefault) {
		
		ClientLogic cl = ClientLogic.getInstance();
		
		// If it's true
		if (isDefault) {
			List<BankAccountLogic> list = cl.getBankAccounts();
			
			// We look for the current default account
			for (BankAccountLogic ba : list) {
				if (ba.isDefault()) {
					
					// We remove it from default account
					ba.setDefault(false);
					
					// We update the database.
					ba.updateBankAccount(MasterORM.getInstance().getORM());
					break;
				}
			}
			
			// We save the new default account.
			saveDefault();
		}
		
		// And we set it as default.
		super.setDefault(isDefault);
	}
	
	/**
	 * Save the default account to recover it more quickly.
	 */
	public void saveDefault() {
		
		defaultBankAccount = this;
	}
	
	/**
	 * Get the most recent transaction in time (not the last one registered).
	 *
	 * @return IOTransactionLogic instance of the most recent.
	 */
	public IOTransactionLogic getMostRecentTransaction() {
		
		if (transactionsByDate != null && !transactionsByDate.isEmpty()) {
			Object[] years = IOTransactionLogic.getYearsWithTransactions()
					.toArray();
			
			// We recover the last year used.
			int lastYear = (Integer) years[years.length - 1];
			
			// We start on the last month
			for (int i = 12; i > 0; --i) {
				
				ArrayList<IOTransactionLogic> tmp = transactionsByDate
						.get(lastYear)[i - 1];
				
				// and stop at the first tab not empty
				if (tmp != null && tmp.size() > 0) {
					
					// We sort it
					Collections.sort(tmp);
					
					// And return the last entry of the tab.
					return tmp.get(tmp.size() - 1);
				}
			}
		}
		
		// If there is not transactions, we return null.
		return null;
	}
	
	/**
	 * Update the bank account information.
	 * Update the database once done.
	 *
	 * @param name
	 * @param bankName
	 * @param type
	 * @param amount
	 * @param isDefault
	 */
	public void update(String name, String bankName, String type, double amount,
			boolean isDefault) {
		
		setName(name);
		setType(type);
		setBankName(bankName);
		setAmount(amount);
		changeDefault(isDefault);
		
		// Update entry on database.
		updateBankAccount(MasterORM.getInstance().getORM());
	}
	
	/**
	 * Recover the data  from the database for this bank account.
	 * It mainly will recover the transactions of the bank account.
	 */
	public void setDataFromDB(IORM orm) {
		
		try {
			
			List<IDALIotransactionEntity> ba = orm.getIotransactionRepository()
					.getIotransactionsByBankaccount(getId());
			
			DALIOTransactionMapper.toBos(ba);
			
		} catch (DALException e) {
			e.printStackTrace();
		}
	}
	
	// Wipe all the data of the instance for a new user connection.
	public void wipe() {
		
		transactions.clear();
		transactionsByDate.clear();
	}
}
