package bll.logic;

import bll.model.IOTransactionModel;
import dal.dalexception.DALException;
import dal.orm.IORM;
import dal.orm.PgORM;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

/**
 * TODO
 *
 * @author Daniel Gonzalez Lopez, Héléna Line Reymond
 * @version 1.2
 */
public class IOTransactionLogic extends IOTransactionModel {
	
	private static HashMap<CategoryLogic, ArrayList<IOTransactionLogic>>
			transactionsByCategory = new HashMap<>();
	
	private static Set<Integer> yearsWithTransactions = new TreeSet<>();
	
	private CategoryLogic category;
	private BankAccountLogic bank;
	
	public IOTransactionLogic() {}
	
	/**
	 * TODO
	 *
	 * @param amount
	 * @param name
	 * @param description
	 * @param date
	 * @param currency
	 * @param category
	 * @param bankAccount
	 */
	public IOTransactionLogic(double amount, String name, String description,
			Date date, String currency, CategoryLogic category,
			BankAccountLogic bankAccount) {
		
		super(amount, name, description, currency, (amount >= 0));
		
		setDate(date);
		setCategory(category);
		setBank(bankAccount);
		
		setBudgetID(null);
		
		createIOTransaction(new PgORM());
	}
	
	public HashMap<CategoryLogic, ArrayList<IOTransactionLogic>> getTransactionsByCategory() {
		
		return transactionsByCategory;
	}
	
	private void addToHashMap() {
		
		if (transactionsByCategory.containsKey(this.category)) {
			
			transactionsByCategory.get(this.category).add(this);
		} else {
			
			ArrayList<IOTransactionLogic> tr = new ArrayList<>();
			
			transactionsByCategory.put(this.category, tr);
			
			tr.add(this);
		}
	}
	
	@Override
	public void setDate(Date date) {
		
		super.setDate(date);
		
		yearsWithTransactions.add(date.toLocalDate().getYear());
	}
	
	public static Set getYearsWithTransactions() {
		
		return yearsWithTransactions;
	}
	
	public void setCategory(CategoryLogic cl) {
		
		this.category = cl;
		
		setCategoryID(cl.getId());
		addToHashMap();
	}
	
	private void updateCategory(CategoryLogic newCat) {
		
		transactionsByCategory.get(this.category).remove(this);
		setCategory(newCat);
	}
	
	public void setBank(BankAccountLogic bankAccount) {
		
		this.bank = bankAccount;
		bankAccount.addNewTransaction(this);
	}
	
	private void updateBank(BankAccountLogic newBank) {
		
		bank.removeTransaction(this);
		setBank(newBank);
	}
	
	/**
	 * TODO
	 */
	public void update(double amount, String name, String description,
			Date date, String currency, CategoryLogic category,
			BankAccountLogic bankAccount) {
		
		setAmount(amount);
		setName(name);
		setDescription(description);
		setDate(date);
		setCurrency(currency);
		
		setBudgetID(null);
		
		if (this.category != category) {
			updateCategory(category);
		}
		
		if (this.bank != bankAccount) {
			updateBank(bankAccount);
		}
		
		updateIOTransaction(new PgORM());
	}
	
	public static void updateTransactionsOnCategoryDeletion(
			CategoryLogic deleted) {
		
		for (IOTransactionLogic old : transactionsByCategory.get(deleted)) {
			
			old.setCategory(CategoryLogic.getDefaultCategory());
		}
		
		transactionsByCategory.remove(deleted);
	}
	
	public void supp() {
		
		try {
			IORM orm = new PgORM();
			
			orm.beginTransaction();
			
			orm.getIotransactionRepository().delete(getId());
			orm.commit();
			
			transactionsByCategory.get(this.category).remove(this);
			bank.removeTransaction(this);
			
		} catch (DALException e) {
			e.printStackTrace();
		}
	}
	
	@Deprecated(since = "1.2")
	@Override
	public String toString() {
		
		String s = getName() + " " + getAmount() + " " + getBankAccountID()
				+ " " + getDate();
		
		return s;
	}
}
