package bll.logic;

import bll.model.BudgetModel;
import dal.dalexception.DALException;
import dal.orm.MasterORM;

import java.sql.Date;

/**
 * TODO
 *
 * @author Daniel Gonzalez Lopez
 * @version 1.0
 */
public class BudgetLogic extends BudgetModel {
	
	public BudgetLogic() {
		
		ClientLogic.getInstance().addBudget(this);
	}
	
	public BudgetLogic(String name, double amount, Date startingDate,
			Date endingDate) {
		
		super(name, amount, startingDate, endingDate);
		
		createBudget(MasterORM.getInstance().getORM());
	}
	
	/**
	 * TODO
	 */
	public void update(String name, double amount, Date startingDate,
			Date endingDate) {
		
		setName(name);
		setAmount(amount);
		setStartingDate(startingDate);
		setEndingDate(endingDate);
		
		updateBudget(MasterORM.getInstance().getORM());
	}
	
	/**
	 * TODO
	 */
	public void supp() {
		
		try {
			MasterORM.getInstance().getORM().getBudgetRepository()
					.delete(getId());
		} catch (DALException e) {
			e.printStackTrace();
		}
	}
}
