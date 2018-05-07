package bll.logic;

import bll.model.BankAccountModel;
import dal.orm.PgORM;

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
 * @version 1.1
 */
public class BankAccountLogic extends BankAccountModel {
	
	private ArrayList<IOTransactionLogic> transactions = new ArrayList<>();
	
	
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
		
		createBankAccount(new PgORM());
	}
	
	public String toString() {
		
		return super.getName();
	}
	
	/**
	 * Increment the bank account by the value of the parameter.
	 *
	 * @param io Income to createItem to the amount.
	 */
	private void updateAmount(double io) {
		
		setAmount(getAmount() + io);
	}
	
	/**
	 * Add the transaction to the transaction list of the bank account.
	 *
	 * @param transaction New transaction to createItem to the list.
	 */
	public void addTransaction(IOTransactionLogic transaction) {
		
		transactions.add(transaction);
		transaction.setBankAccountID(getId());
		
		updateAmount(transaction.getAmount());
	}
	
	/**
	 * Get the list of transactions for this bank account.
	 *
	 * @return List of transactions.
	 */
	public ArrayList<IOTransactionLogic> getTransactions() {
		
		return transactions;
	}
	
	/**
	 * "Suppress" the bank account. It simply makes it invisible for the user.
	 */
	public void supp() {
		
		setVisible(false);
		setDefault(false);
		updateBankAccount(new PgORM());
	}
	
	/**
	 * TODO
	 *
	 * @param isDefault
	 */
	private void changeDefault(boolean isDefault) {
		
		ClientLogic cl = ClientLogic.getInstance();
		
		if (isDefault) {
			List<BankAccountLogic> list = cl.getBankAccounts();
			
			for (BankAccountLogic ba : list) {
				if (ba.isDefault()) {
					ba.setDefault(false);
					ba.updateBankAccount(new PgORM());
					break;
				}
			}
		}
		
		setDefault(isDefault);
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
		
		updateBankAccount(new PgORM());
	}
}
