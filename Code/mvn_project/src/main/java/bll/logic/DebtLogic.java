package bll.logic;

import bll.model.ClientModel;
import bll.model.DebtModel;
import bll.model.IOTransactionModel;
import dal.dalexception.DALException;
import dal.ientites.IDALBankaccountEntity;
import dal.ientites.IDALCategoryEntity;
import dal.irepositories.IBankaccountRepository;
import dal.irepositories.ICategoryRepository;
import dal.irepositories.IDebtRepository;
import dal.orm.IORM;
import dal.orm.MasterORM;

import java.sql.Date;
import java.time.LocalDate;


/**
 * DebtLogic class.
 * Implements the business logic of the DebtModel.
 * Implements methods to create, change and delete debts and methods to
 * manage the links of the creator and the contributor, plus methods to
 * create entries of transactions once the debt is confirmed.
 * Before changing these attributes, the methods check their integrity
 * to avoid data problems.
 *
 * @author Daniel Gonzalez Lopez
 * @author Héléna Line Reymond
 * @version 2.0
 */
public class DebtLogic extends DebtModel {
	
	ClientModel contributor;
	ClientModel creator;
	
	/**
	 * Construct an instance and link it to the user instance.
	 */
	public DebtLogic() {
		
		ClientLogic.getInstance().addDebt(this);
	}
	
	/**
	 * Construct an instance, link it to the user instance and create an entry
	 * in the database
	 *
	 * @param name
	 * @param description
	 * @param amount
	 * @param isIncome
	 * @param expirationDate
	 * @param contributor (Used if online)
	 */
	public DebtLogic(String name, String description, double amount,
			boolean isIncome, Date expirationDate, ClientModel contributor) {
		
		super(name, description, amount, isIncome, expirationDate);
		
		setContributor(contributor);
		setCreator(ClientLogic.getInstance());
		setCreatorID(ClientLogic.getInstance().getId());
		
		createDebt(MasterORM.getInstance().getORM());
		ClientLogic.getInstance().addDebt(this);
	}
	
	/**
	 * Set the creator of the debt.
	 *
	 * @param creator
	 */
	public void setCreator(ClientModel creator) {
		
		this.creator = creator;
	}
	
	/**
	 * Set the contributor chosen for the debt by the creator.
	 *
	 * @param contributor
	 */
	public void setContributor(ClientModel contributor) {
		
		this.contributor = contributor;
		
		if (contributor != null) {
			setContributorID(contributor.getId());
		} else {
			setContributorID(null);
		}
	}
	
	/**
	 * Confirm the payment of a debt.
	 * Create the transaction implied from the debt on the creator account and,
	 * if there is one, on the contributor account too (but with reverse info
	 * for the amount)
	 */
	public void confirmPayment() {
		
		Date now = Date.valueOf(LocalDate.now());
		
		int facteur = isIncome() ? 1 : -1;
		String type = isIncome() ? "Créance: " : "Dette: ";
		
		// Create transaction for the creator
		new IOTransactionLogic(facteur * this.getAmount(),
				type + this.getName(), this.getDescription(), now, "CHF",
				CategoryLogic.getDefaultCategory(),
				BankAccountLogic.getDefaultBankAccount(), null);
		
		// Create transaction for the contributor
		if (contributor != null) {
			facteur = !isIncome() ? 1 : -1;
			type = !isIncome() ? "Créance: " : "Dette: ";
			
			IOTransactionModel tr = new IOTransactionModel(
					facteur * this.getAmount(), type + this.getName(),
					this.getDescription(), "CHF", !isIncome());
			
			tr.setDate(now);
			
			IORM orm = MasterORM.getInstance().getORM();
			
			try {
				orm.beginTransaction();
				
				ICategoryRepository repoC = orm.getCategoryRepository();
				IDALCategoryEntity cat = repoC
						.getDefaultCategoryByClientId(contributor.getId());
				tr.setCategoryID(cat.getId());
				
				IBankaccountRepository repoB = orm.getBankaccountRepository();
				IDALBankaccountEntity ba = repoB
						.getDefaultBankAccountByClient(contributor.getId());
				tr.setBankAccountID(ba.getId());
				
				orm.commit();
				
			} catch (DALException e) {
				e.printStackTrace();
			}
			
			tr.setBudgetID(null);
			tr.createIOTransaction(orm);
		}
		
		supp();
	}
	
	/**
	 * Update the debt in the app and in the database.
	 *
	 * @param name
	 * @param description
	 * @param amount
	 * @param expirationDate
	 */
	public void update(String name, String description, double amount,
			Date expirationDate) {
		
		setName(name);
		setDescription(description);
		setAmount(amount);
		setExpirationDate(expirationDate);
		
		// Update the entry in the database.
		updateDebt(MasterORM.getInstance().getORM());
	}
	
	/**
	 * Get the contributor of the debt.
	 *
	 * @return ClientModel instance representing the contributor.
	 */
	public ClientModel getContributor() {
		
		return contributor;
	}
	
	/**
	 * Get the creator of the debt.
	 *
	 * @return ClientModel instance representing the creator.
	 */
	public ClientModel getCreator() {
		
		return creator;
	}
	
	/**
	 * Delete the debt from the database and all the links from the user.
	 */
	public void supp() {
		
		IORM orm = MasterORM.getInstance().getORM();
		
		try {
			orm.beginTransaction();
			
			IDebtRepository repo = orm.getDebtRepository();
			repo.delete(this.getId());
			
			ClientLogic.getInstance().removeDebt(this);
			
			orm.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
