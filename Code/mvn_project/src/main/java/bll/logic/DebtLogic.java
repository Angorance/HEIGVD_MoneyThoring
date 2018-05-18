package bll.logic;

import bll.mappers.DAL.DALClientMapper;
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
 * TODO
 *
 * @author Daniel Gonzalez Lopez
 * @version 1.0
 */
public class DebtLogic extends DebtModel {
	
	ClientModel contributor;
	ClientModel creator;
	
	public DebtLogic() {
		
		ClientLogic.getInstance().addDebt(this);
	}
	
	public DebtLogic(String name, String description, double amount,
			boolean isIncome, Date expirationDate, ClientModel contributor) {
		
		super(name, description, amount, isIncome, expirationDate);
		
		setContributor(contributor);
		setCreator(ClientLogic.getInstance());
		setCreatorID(ClientLogic.getInstance().getId());
		
		createDebt(MasterORM.getInstance().getORM());
		ClientLogic.getInstance().addDebt(this);
	}
	
	public DebtLogic(String name, String description, double amount,
			boolean isIncome, Date expirationDate) {
		
		super(name, description, amount, isIncome, expirationDate);
		
		setCreatorID(ClientLogic.getInstance().getId());
		
		createDebt(MasterORM.getInstance().getORM());
		ClientLogic.getInstance().addDebt(this);
	}
	
	public void setCreator(ClientModel creator) {
		
		this.creator = creator;
	}
	
	public void setContributor(ClientModel contributor) {
		
		this.contributor = contributor;
		
		if(contributor != null) {
			setContributorID(contributor.getId());
		} else {
			setContributorID(null);
		}
	}
	
	public void confirmPayment() {
		
		Date now = Date.valueOf(LocalDate.now());
		
		int facteur = isIncome() ? 1 : -1;
		String type = isIncome() ? "Créance: " : "Dette: ";
		
		// Create transaction for the creator
		new IOTransactionLogic(facteur * this.getAmount(), type + this.getName(),
				this.getDescription(), now, "CHF",
				CategoryLogic.getDefaultCategory(),
				BankAccountLogic.getDefaultBankAccount(), null);
		
		// Create transaction for the contributor
		if(contributor != null) {
			facteur = !isIncome() ? 1 : -1;
			type = !isIncome() ? "Créance: " : "Dette: ";
			
			IOTransactionModel tr = new IOTransactionModel(facteur * this.getAmount(), type + this.getName(), this.getDescription(), "CHF", !isIncome());
			
			tr.setDate(now);
			
			IORM orm = MasterORM.getInstance().getORM();
			
			try {
				orm.beginTransaction();
				
				ICategoryRepository repoC = orm.getCategoryRepository();
				IDALCategoryEntity cat = repoC.getDefaultCategoryByClientId(contributor.getId());
				tr.setCategoryID(cat.getId());
				
				IBankaccountRepository repoB = orm.getBankaccountRepository();
				IDALBankaccountEntity ba = repoB.getDefaultBankAccountByClient(contributor.getId());
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
	
	public void update(String name, String description, double amount,
			Date expirationDate) {
		
		setName(name);
		setDescription(description);
		setAmount(amount);
		setExpirationDate(expirationDate);
		
		updateDebt(MasterORM.getInstance().getORM());
	}
	
	public ClientModel getContributor() {
		return contributor;
	}
	
	public ClientModel getCreator() {
		return creator;
	}
	
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
