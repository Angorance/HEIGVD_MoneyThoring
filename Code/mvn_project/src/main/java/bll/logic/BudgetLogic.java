package bll.logic;

import bll.model.BudgetModel;
import dal.dalexception.DALException;
import dal.orm.MasterORM;
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
	
	public BudgetLogic(String name, double amount, boolean isShared, boolean isRecurrent, Date startingDate,
			Date endingDate, int gap) {
		
		super(name, amount, isShared, isRecurrent, startingDate, endingDate, gap);
		ClientLogic.getInstance().addBudget(this);
		
		createBudget(MasterORM.getInstance().getPgORM());
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
		
		updateBudget(MasterORM.getInstance().getPgORM());
	}
	
	/**
	 * TODO
	 */
	public void supp() {
		
		try {
			MasterORM.getInstance().getPgORM().getBudgetRepository()
					.delete(getId());
		} catch (DALException e) {
			e.printStackTrace();
		}
	}
}
