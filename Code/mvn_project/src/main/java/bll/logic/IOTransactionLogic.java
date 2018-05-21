package bll.logic;

import bll.model.IOTransactionModel;
import dal.dalexception.DALException;
import dal.ientites.IDALIotransactionEntity;
import dal.irepositories.IIotransactionRepository;
import dal.orm.IORM;
import dal.orm.MasterORM;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

/**
 * IOTransactionLogic class.
 * Implements the business logic of the IOTransactionModel.
 * Implements methods to manage the links between transactions and other
 * objects, as categories or the bank account.
 * Before changing these attributes, the methods check their integrity
 * to avoid data problems.
 *
 * @author Daniel Gonzalez Lopez
 * @author Héléna Line Reymond
 * @version 2.0
 */
public class IOTransactionLogic extends IOTransactionModel
		implements Comparable<IOTransactionLogic> {
	
	private static ArrayList<IOTransactionLogic> transactions
			= new ArrayList<>();
	
	private static HashMap<CategoryLogic, ArrayList<IOTransactionLogic>>
			transactionsByCategory = new HashMap<>();
	
	private static Set<Integer> yearsWithTransactions = new TreeSet<>();
	
	private CategoryLogic category;
	private BankAccountLogic bank;
	private RecurrenceLogic recurrence;
	private BudgetLogic sharedBudget;
	
	/**
	 * Construct an instance and link it with the good bank account.
	 */
	public IOTransactionLogic() {
		
		transactions.add(this);
	}
	
	/**
	 * Construct an instance and link it with the good bank account, plus it
	 * creates the entry in the database.
	 *
	 * @param amount
	 * @param name
	 * @param description
	 * @param date
	 * @param currency (Not implemented, always CHF)
	 * @param category
	 * @param bankAccount
	 */
	public IOTransactionLogic(double amount, String name, String description,
			Date date, String currency, CategoryLogic category,
			BankAccountLogic bankAccount, BudgetLogic budget) {
		
		super(amount, name, description, currency, (amount >= 0));
		
		setDate(date);
		setCategory(category);
		setBank(bankAccount);
		setBudget(budget);
		
		createIOTransaction(MasterORM.getInstance().getORM());
		transactions.add(this);
	}
	
	/**
	 * Get the transaction corresponding to the given ID.
	 *
	 * @param iotransactionId
	 *
	 * @return IOTransactionLogic instance or null if nothing found.
	 */
	public static IOTransactionLogic getIOTransactionByID(int iotransactionId) {
		
		for (IOTransactionLogic tl : transactions) {
			
			if (tl.getId() == iotransactionId) {
				return tl;
			}
		}
		
		return null;
	}
	
	/**
	 * Get the transactions for a given budget ID.
	 *
	 * @param budgetId
	 *
	 * @return ArrayList of transactions.
	 */
	public static ArrayList<IOTransactionModel> getIOTransactionByBudget(
			int budgetId) {
		
		ArrayList<IOTransactionModel> list = new ArrayList<>();
		
		IORM orm = MasterORM.getInstance().getORM();
		
		try {
			orm.beginTransaction();
			
			IIotransactionRepository repo = orm.getIotransactionRepository();
			
			for (IDALIotransactionEntity te : repo
					.getIotransactionsByBudget(budgetId)) {
				
				IOTransactionModel tm = new IOTransactionModel(te.getAmount(),
						te.getName(), te.getDescription(), te.getCurrency(),
						false);
				
				tm.setDate(te.getDatetransaction());
				
				list.add(tm);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * Get the transactions by categories (hashmap).
	 *
	 * @return HashMap of the transactions by categories.
	 */
	public static HashMap<CategoryLogic, ArrayList<IOTransactionLogic>> getTransactionsByCategory() {
		
		return transactionsByCategory;
	}
	
	/**
	 * Add a transaction to the hashmap.
	 */
	private void addToHashMap() {
		
		if (transactionsByCategory.containsKey(this.category)) {
			
			transactionsByCategory.get(this.category).add(this);
		} else {
			
			ArrayList<IOTransactionLogic> tr = new ArrayList<>();
			
			transactionsByCategory.put(this.category, tr);
			
			tr.add(this);
		}
	}
	
	/**
	 * Set the date of the transaction.
	 * Update the TreeSet containing all the transaction years.
	 *
	 * @param date New date of the transaction.
	 */
	@Override
	public void setDate(Date date) {
		
		super.setDate(date);
		
		yearsWithTransactions.add(date.toLocalDate().getYear());
	}
	
	/**
	 * Get the TreeSet of the years with transactions.
	 *
	 * @return Set of years with transactions.
	 */
	public static Set<Integer> getYearsWithTransactions() {
		
		return yearsWithTransactions;
	}
	
	/**
	 * Set the category for the transaction.
	 *
	 * @param cl
	 */
	public void setCategory(CategoryLogic cl) {
		
		this.category = cl;
		
		setCategoryID(cl.getId());
		addToHashMap();
	}
	
	/**
	 * Get the category of the transaction.
	 *
	 * @return CategoryLogic instance.
	 */
	public CategoryLogic getCategory() {
		
		return category;
	}
	
	/**
	 * Update the category of the transaction with the new one.
	 *
	 * @param newCat
	 */
	private void updateCategory(CategoryLogic newCat) {
		
		transactionsByCategory.get(this.category).remove(this);
		setCategory(newCat);
	}
	
	/**
	 * Set the bank account of the transaction.
	 * Add the transaction as new transaction, updating the amount of the bank
	 * account.
	 *
	 * @param bankAccount
	 */
	private void setBank(BankAccountLogic bankAccount) {
		
		this.bank = bankAccount;
		bankAccount.addNewTransaction(this);
	}
	
	/**
	 * Set the bank account of the transaction.
	 * Add the transaction as already existing transaction, so it don't update
	 * the amount of the bank account.
	 *
	 * @param bankAccount
	 */
	public void setBankAccount(BankAccountLogic bankAccount) {
		
		this.bank = bankAccount;
		bankAccount.addTransaction(this);
	}
	
	/**
	 * Update the bank account of the transaction.
	 * It removes it from the previous bank (updating its amount) and add it to
	 * the new bank (updating its amount too).
	 *
	 * @param newBank
	 */
	private void updateBank(BankAccountLogic newBank) {
		
		bank.removeTransaction(this);
		setBank(newBank);
	}
	
	/**
	 * Set the recurrence for the transaction (not implemented).
	 *
	 * @param recurrence
	 */
	public void setRecurrence(RecurrenceLogic recurrence) {
		
		this.recurrence = recurrence;
	}
	
	/**
	 * Set the shared budget of the transaction.
	 *
	 * @param budget
	 */
	private void setBudget(BudgetLogic budget) {
		
		this.sharedBudget = budget;
		
		if (budget != null) {
			
			setBudgetID(budget.getId());
		} else {
			
			setBudgetID(null);
		}
		
	}
	
	/**
	 * Get the shared budget concerned by the transaction.
	 *
	 * @return BudgetLogic instance.
	 */
	public BudgetLogic getBudget() {
		
		if (sharedBudget == null && getBudgetID() != null) {
			
			for (BudgetLogic bu : ClientLogic.getInstance().getBudgets()) {
				
				if (bu.getId() == getBudgetID()) {
					sharedBudget = bu;
					break;
				}
			}
		}
		
		return sharedBudget;
	}
	
	/**
	 * Update the transaction, updating the entry in the database.
	 */
	public void update(double amount, String name, String description,
			Date date, String currency, CategoryLogic category,
			BankAccountLogic bankAccount, BudgetLogic budget) {
		
		setAmount(amount);
		setName(name);
		setDescription(description);
		setCurrency(currency);
		setBudget(budget);
		
		
		if (this.category != category) {
			updateCategory(category);
		}
		
		if (this.bank != bankAccount) {
			updateBank(bankAccount);
		}
		
		if (getDate() != date) {
			LocalDate previous = getDate().toLocalDate();
			
			setDate(date);
			bank.updateTransaction(this, previous);
		}
		
		IORM orm = MasterORM.getInstance().getORM();
		updateIOTransaction(orm);
		
		try {
			orm.commit();
		} catch (DALException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Update all transactions concerned by the deletion of a category.
	 * The new category set is the default one.
	 *
	 * This method is used only by Derby (offline) because it was impossible to
	 * make such a complexe trigger with this embedded database.
	 *
	 * @param deleted
	 */
	public static void updateTransactionsOnCategoryDeletion(
			CategoryLogic deleted) {
		
		if (transactionsByCategory.containsKey(deleted)) {
			for (IOTransactionLogic old : transactionsByCategory.get(deleted)) {
				
				old.setCategory(CategoryLogic.getDefaultCategory());
			}
		}
		
		transactionsByCategory.remove(deleted);
	}
	
	/**
	 * Suppress the transaction from the app and the database.
	 */
	public void supp() {
		
		try {
			IORM orm = MasterORM.getInstance().getORM();
			
			orm.beginTransaction();
			
			orm.getIotransactionRepository().delete(getId());
			orm.commit();
			
			transactionsByCategory.get(this.category).remove(this);
			
			if (transactionsByCategory.get(this.category).isEmpty()) {
				transactionsByCategory.remove(this.category);
			}
			
			bank.removeTransaction(this);
			
			if (recurrence != null) {
				recurrence.supp();
			}
			
		} catch (DALException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Compares this object with the specified object for order.  Returns a
	 * negative integer, zero, or a positive integer as this object is less
	 * than, equal to, or greater than the specified object.
	 *
	 * <p>The implementor must ensure
	 * {@code sgn(x.compareTo(y)) == -sgn(y.compareTo(x))}
	 * for all {@code x} and {@code y}.  (This
	 * implies that {@code x.compareTo(y)} must throw an exception iff
	 * {@code y.compareTo(x)} throws an exception.)
	 *
	 * <p>The implementor must also ensure that the relation is transitive:
	 * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
	 * {@code x.compareTo(z) > 0}.
	 *
	 * <p>Finally, the implementor must ensure that {@code x.compareTo(y)==0}
	 * implies that {@code sgn(x.compareTo(z)) == sgn(y.compareTo(z))}, for
	 * all {@code z}.
	 *
	 * <p>It is strongly recommended, but <i>not</i> strictly required that
	 * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
	 * class that implements the {@code Comparable} interface and violates
	 * this condition should clearly indicate this fact.  The recommended
	 * language is "Note: this class has a natural ordering that is
	 * inconsistent with equals."
	 *
	 * <p>In the foregoing description, the notation
	 * {@code sgn(}<i>expression</i>{@code )} designates the mathematical
	 * <i>signum</i> function, which is defined to return one of {@code -1},
	 * {@code 0}, or {@code 1} according to whether the value of
	 * <i>expression</i> is negative, zero, or positive, respectively.
	 *
	 * @param o the object to be compared.
	 *
	 * @return a negative integer, zero, or a positive integer as this object
	 * 		is less than, equal to, or greater than the specified object.
	 *
	 * @throws NullPointerException if the specified object is null
	 * @throws ClassCastException if the specified object's type prevents it
	 * 		from being compared to this object.
	 */
	@Override
	public int compareTo(IOTransactionLogic o) {
		
		return this.getDate().compareTo(o.getDate());
	}
}
