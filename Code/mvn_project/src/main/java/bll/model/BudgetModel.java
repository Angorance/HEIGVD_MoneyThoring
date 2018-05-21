package bll.model;

import bll.mappers.DAL.DALBudgetMapper;
import dal.irepositories.IBudgetRepository;
import dal.orm.IORM;

import java.sql.Date;

/**
 * BudgetModel class.
 * Allows the mapping between the DAL entities and the Business Logic.
 * Only implements the constructors, the getters and the setters.
 *
 * @author Daniel Gonzalez Lopez
 * @version 1.0
 */
public class BudgetModel {
	
	private int id;
	private int clientID;
	private int gap;
	
	private String name;
	
	private double amount;
	
	private boolean isShared;
	private boolean isRecurrent;
	
	private Date startingDate;
	private Date endingDate;
	
	
	protected BudgetModel() {}
	
	/**
	 * Construct an instance with the given parameters.
	 *
	 * @param name
	 * @param amount
	 * @param isShared
	 * @param isRecurrent
	 * @param startingDate
	 * @param endingDate
	 * @param gap
	 */
	protected BudgetModel(String name, double amount, boolean isShared,
			boolean isRecurrent, Date startingDate, Date endingDate, int gap) {
		
		setName(name);
		setAmount(amount);
		setShared(isShared);
		setRecurrent(isRecurrent);
		setStartingDate(startingDate);
		setEndingDate(endingDate);
		setGap(gap);
		
	}
	
	/**
	 * Create a budget entry for the user into the database.
	 *
	 * @param orm ORM instance to use.
	 */
	protected void createBudget(IORM orm) {
		
		try {
			
			orm.beginTransaction();
			
			IBudgetRepository repo = orm.getBudgetRepository();
			setId(repo.addBudget(DALBudgetMapper.toDbo(this)));
			
			orm.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Update budget entry with new data.
	 *
	 * @param orm ORM instance to use.
	 */
	protected void updateBudget(IORM orm) {
		
		try {
			
			orm.beginTransaction();
			
			IBudgetRepository repo = orm.getBudgetRepository();
			repo.update(DALBudgetMapper.toDbo(this));
			
			orm.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Get ID of the budget.
	 *
	 * @return ID of the budget.
	 */
	public int getId() {
		
		return id;
	}
	
	/**
	 * Get client ID of the budget.
	 *
	 * @return Client ID.
	 */
	public int getClientID() {
		
		return clientID;
	}
	
	/**
	 * Get the gap of the budget.
	 * (For reccurent budgets. Not implemented)
	 *
	 * @return Gap of the budget.
	 */
	public int getGap() {
		
		return gap;
	}
	
	/**
	 * Get the amount of the budget.
	 *
	 * @return Amount of the budget.
	 */
	public double getAmount() {
		
		return amount;
	}
	
	/**
	 * Get the name of the budget.
	 *
	 * @return Name of the budget.
	 */
	public String getName() {
		
		return name;
	}
	
	/**
	 * Get if the budget is shared or not.
	 *
	 * @return True if shared budget, false otherwise.
	 */
	public boolean isShared() {
		
		return isShared;
	}
	
	/**
	 * Get if the budget is recurrent or not.
	 * (Not implemented)
	 *
	 * @return True if it's recurrent, false otherwise.
	 */
	public boolean isRecurrent() {
		
		return isRecurrent;
	}
	
	/**
	 * Get the starting date of the budget.
	 *
	 * @return Starting date.
	 */
	public Date getStartingDate() {
		
		return startingDate;
	}
	
	/**
	 * Get the ending date of the budget.
	 *
	 * @return Ending date.
	 */
	public Date getEndingDate() {
		
		return endingDate;
	}
	
	
	/**
	 * Set the ID of the budget.
	 *
	 * @param id
	 */
	public void setId(int id) {
		
		this.id = id;
	}
	
	/**
	 * The the client ID of the budget.
	 *
	 * @param clientID
	 */
	public void setClientID(int clientID) {
		
		this.clientID = clientID;
	}
	
	/**
	 * Srt the gap of the budget.
	 *
	 * @param gap
	 */
	public void setGap(int gap) {
		
		this.gap = gap;
	}
	
	/**
	 * Set the amount of the budget.
	 *
	 * @param amount
	 */
	public void setAmount(double amount) {
		
		this.amount = amount;
	}
	
	/**
	 * Set the name of the budget.
	 *
	 * @param name
	 */
	public void setName(String name) {
		
		this.name = name;
	}
	
	/**
	 * Set whether the budget is shared or not.
	 *
	 * @param shared
	 */
	public void setShared(boolean shared) {
		
		isShared = shared;
	}
	
	/**
	 * Set whether it's a recurrent budget or not.
	 *
	 * @param recurrent
	 */
	public void setRecurrent(boolean recurrent) {
		
		isRecurrent = recurrent;
	}
	
	/**
	 * Set the starting date.
	 *
	 * @param startingDate
	 */
	public void setStartingDate(Date startingDate) {
		
		this.startingDate = startingDate;
	}
	
	/**
	 * Set the ending date.
	 *
	 * @param endingDate
	 */
	public void setEndingDate(Date endingDate) {
		
		this.endingDate = endingDate;
	}
}
