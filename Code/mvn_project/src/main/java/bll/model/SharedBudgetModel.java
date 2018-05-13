package bll.model;

import bll.mappers.DAL.DALSharedBudgetMapper;
import dal.irepositories.ISharedBudgetRepository;
import dal.orm.IORM;

/**
 * @author Héléna Line Reymond
 * @version 1.0
 */
public class SharedBudgetModel {
	
	private int client_id;
	private int budget_id;
	
	// Protected default constructor. Avoid instances creation outside package.
	protected SharedBudgetModel() {}
	
	/**
	 * Construct an instance of SharedBudgetModel with the given parameters.
	 *
	 * @param client_id ID of the client.
	 * @param budget_id ID of the budget.
	 */
	protected SharedBudgetModel(int client_id, int budget_id) {
		
		this.client_id = client_id;
		this.budget_id = budget_id;
	}
	
	/**
	 * Create an entry for the user into the database.
	 *
	 * @param orm ORM instance to use.
	 */
	protected void createSharedBudget(IORM orm) {
		
		try {
			
			orm.beginTransaction();
			
			ISharedBudgetRepository repo = orm.getSharedBudgetRepository();
			//repo.addSharedbudget(DALSharedBudgetMapper.toDboPG(this));
			
			orm.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Get the client ID.
	 *
	 * @return client ID.
	 */
	public int getClientID() {
		
		return client_id;
	}
	
	/**
	 * Get the budget ID.
	 *
	 * @return budget ID.
	 */
	public int getBudgetID() {
		
		return budget_id;
	}
	
	/**
	 * Set the client ID.
	 *
	 * @return client ID.
	 */
	public void setClientID(int ID) {
		
		client_id = ID;
	}
	
	/**
	 * Set the budget ID.
	 *
	 * @return budget ID.
	 */
	public void setBudgetID(int ID) {
		
		budget_id = ID;
	}
}
