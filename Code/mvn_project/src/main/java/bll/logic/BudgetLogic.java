package bll.logic;

import bll.model.BudgetModel;
import dal.dalexception.DALException;
import dal.orm.PgORM;

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
		
		createBudget(new PgORM());
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
		
		updateBudget(new PgORM());
	}
	
	/**
	 * TODO
	 */
	public void supp() {
		
		try {
			new PgORM().getBudgetRepository().delete(getId());
		} catch (DALException e) {
			e.printStackTrace();
		}
	}
}
