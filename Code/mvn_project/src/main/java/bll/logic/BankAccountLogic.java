package bll.logic;

import bll.mappers.DAL.DALIOTransactionMapper;
import bll.model.BankAccountModel;
import dal.dalexception.DALException;
import dal.ientites.IDALIotransactionEntity;
import dal.orm.IORM;
import dal.orm.MasterORM;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
public class BankAccountLogic extends BankAccountModel {
	
	private static BankAccountLogic defaultBankAccount;
	
	private ArrayList<IOTransactionLogic> transactions = new ArrayList<>();
	
	private HashMap<Integer, ArrayList<IOTransactionLogic>[]> transactionsByDate
			= new HashMap<>();
	
	
	public BankAccountLogic() {
		
		ClientLogic.getInstance().addBankAccount(this);
	}
	
	/**
	 * TODO
	 *
	 * @param name
	 * @param bankName
	 * @param type
	 * @param amount
	 * @param isDefault
	 */
	public BankAccountLogic(String name, String bankName, String type,
			double amount, boolean isDefault, int clientID) {
		
		super(name, bankName, type, amount, clientID);
		
		changeDefault(isDefault);
		
		ClientLogic.getInstance().addBankAccount(this);
		
		createBankAccount(MasterORM.getInstance().getORM());
	}
	
	private void addToHashMap(IOTransactionLogic tl) {
		
		Date date = tl.getDate();
		int year = date.toLocalDate().getYear();
		int month = date.toLocalDate().getMonthValue() - 1;
		
		if (transactionsByDate.containsKey(year)) {
			
			transactionsByDate.get(year)[month].add(tl);
		} else {
			
			ArrayList<IOTransactionLogic>[] tab = new ArrayList[12];
			
			for (int i = 0; i < 12; ++i) {
				tab[i] = new ArrayList<>();
			}
			
			transactionsByDate.put(year, tab);
			
			tab[month].add(tl);
		}
	}
	
	public String toString() {
		
		return getName();
	}
	
	public static BankAccountLogic getDefaultBankAccount() {
		
		return defaultBankAccount;
	}
	
	/**
	 * Increment the bank account by the value of the parameter.
	 *
	 * @param io Income to createItem to the amount.
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
	
	public void removeTransaction(IOTransactionLogic transaction) {
		
		LocalDate date = transaction.getDate().toLocalDate();
		int year = date.getYear();
		int month = date.getMonthValue();
		
		transactions.remove(transaction);
		transactionsByDate.get(year)[month - 1].remove(transaction);
		
		updateAmount(transaction.getAmount() * -1);
	}
	
	/**
	 * Add the transaction to the transaction list of the bank account.
	 *
	 * @param transaction New transaction to createItem to the list.
	 */
	public void addTransaction(IOTransactionLogic transaction) {
		
		transactions.add(transaction);
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
	
	public static BankAccountLogic getBankAccountByID(int bankAccountID) {
		
		for (BankAccountLogic ba : ClientLogic.getInstance()
				.getBankAccounts()) {
			
			if (ba.getId() == bankAccountID) {
				return ba;
			}
		}
		
		return null;
	}
	
	/**
	 * "Suppress" the bank account. It simply makes it invisible for the user.
	 */
	public void supp() {
		
		setVisible(false);
		setDefault(false);
		updateBankAccount(MasterORM.getInstance().getORM());
	}
	
	/**
	 * TODO
	 *
	 * @param isDefault
	 */
	public void changeDefault(boolean isDefault) {
		
		ClientLogic cl = ClientLogic.getInstance();
		
		if (isDefault) {
			List<BankAccountLogic> list = cl.getBankAccounts();
			
			for (BankAccountLogic ba : list) {
				if (ba.isDefault()) {
					ba.setDefault(false);
					ba.updateBankAccount(MasterORM.getInstance().getORM());
					break;
				}
			}
			
			saveDefault();
		}
		
		super.setDefault(isDefault);
	}
	
	public void saveDefault() {
		
		defaultBankAccount = this;
	}
	
	public IOTransactionLogic getMostRecentTransaction() {
		
		Integer[] years = (Integer[]) IOTransactionLogic.getYearsWithTransactions().toArray();
		
		int lastYear = years[years.length - 1];
		
		for (int i = 12; i > 0; --i) {
			
			if (transactionsByDate.get(lastYear)[i - 1] != null) {
				ArrayList<IOTransactionLogic> tmp = transactionsByDate.get(lastYear)[i - 1];
				Collections.sort(tmp);
				
				return tmp.get(tmp.size() - 1);
			}
		}
		
		return null;
	}
	
	/**
	 * TODO
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
		
		updateBankAccount(MasterORM.getInstance().getORM());
	}
	
	/**
	 * TODO
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
	
	public void wipe() {
		
		transactions.clear();
		transactionsByDate.clear();
	}
}
