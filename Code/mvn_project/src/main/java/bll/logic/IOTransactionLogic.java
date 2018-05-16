package bll.logic;

import bll.model.IOTransactionModel;
import dal.dalexception.DALException;
import dal.orm.IORM;
import dal.orm.MasterORM;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

/**
 * TODO
 *
 * @author Daniel Gonzalez Lopez
 * @author Héléna Line Reymond
 * @version 1.2
 */
public class IOTransactionLogic extends IOTransactionModel implements Comparable<IOTransactionLogic> {
	
	private static ArrayList<IOTransactionLogic> transactions
			= new ArrayList<>();
	
	private static HashMap<CategoryLogic, ArrayList<IOTransactionLogic>>
			transactionsByCategory = new HashMap<>();
	
	private static Set<Integer> yearsWithTransactions = new TreeSet<>();
	
	private CategoryLogic category;
	private BankAccountLogic bank;
	private RecurrenceLogic recurrence;
	
	public IOTransactionLogic() {
		
		transactions.add(this);
	}
	
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
		
		createIOTransaction(MasterORM.getInstance().getORM());
		transactions.add(this);
	}
	
	public static IOTransactionLogic getIOTransactionByID(int iotransactionId) {
		
		for (IOTransactionLogic tl : transactions) {
			
			if (tl.getId() == iotransactionId) {
				return tl;
			}
		}
		
		return null;
	}
	
	public static HashMap<CategoryLogic, ArrayList<IOTransactionLogic>> getTransactionsByCategory() {
		
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
	
	private void setBank(BankAccountLogic bankAccount) {
		
		this.bank = bankAccount;
		bankAccount.addNewTransaction(this);
	}
	
	public void setBankAccount(BankAccountLogic bankAccount) {
		
		this.bank = bankAccount;
		bankAccount.addTransaction(this);
	}
	
	private void updateBank(BankAccountLogic newBank) {
		
		bank.removeTransaction(this);
		setBank(newBank);
	}
	
	public void setRecurrence(RecurrenceLogic recurrence) {
		
		this.recurrence = recurrence;
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
		
		updateIOTransaction(MasterORM.getInstance().getORM());
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
			IORM orm = MasterORM.getInstance().getORM();
			
			orm.beginTransaction();
			
			orm.getIotransactionRepository().delete(getId());
			orm.commit();
			
			transactionsByCategory.get(this.category).remove(this);
			
			if (transactionsByCategory.get(this.category).isEmpty()) {
				transactionsByCategory.remove(this.category);
			}
			
			bank.removeTransaction(this);
			recurrence.supp();
			
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
