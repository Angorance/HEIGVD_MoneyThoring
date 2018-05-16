package bll.logic;

import bll.model.ClientModel;
import bll.model.DebtModel;
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
	
	public DebtLogic() {
		
		ClientLogic.getInstance().addDebt(this);
	}
	
	public DebtLogic(String name, String description, double amount,
			boolean isIncome, Date expirationDate, ClientModel contributor) {
		
		super(name, description, amount, isIncome, expirationDate);
		
		setContributor(contributor);
		setCreatorID(ClientLogic.getInstance().getId());
		
		createDebt(MasterORM.getInstance().getPgORM());
		ClientLogic.getInstance().addDebt(this);
	}
	
	public DebtLogic(String name, String description, double amount,
			boolean isIncome, Date expirationDate) {
		
		super(name, description, amount, isIncome, expirationDate);
		
		setCreatorID(ClientLogic.getInstance().getId());
		
		createDebt(MasterORM.getInstance().getPgORM());
		ClientLogic.getInstance().addDebt(this);
	}
	
	public void setContributor(ClientModel contributor) {
		
		this.contributor = contributor;
		
		if(contributor != null) {
			setContributorID(contributor.getId());
		}
	}
	
	public void confirmPayment() {
		
		Date now = Date.valueOf(LocalDate.now());
		
		new IOTransactionLogic(this.getAmount(), this.getName(),
				this.getDescription(), now, "CHF",
				CategoryLogic.getDefaultCategory(),
				BankAccountLogic.getDefaultBankAccount());
		
		supp();
	}
	
	public void update(String name, String description, double amount,
			Date expirationDate) {
		
		setName(name);
		setDescription(description);
		setAmount(amount);
		setExpirationDate(expirationDate);
		
		updateDebt(MasterORM.getInstance().getPgORM());
	}
	
	public ClientModel getContributor() {
		return contributor;
	}
	
	public void supp() {
		
		IORM orm = MasterORM.getInstance().getPgORM();
		
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
