package bll.model;

import bll.mappers.DAL.DALBudgetMapper;
import dal.irepositories.IBudgetRepository;
import dal.orm.IORM;

import java.sql.Date;

/**
 * TODO
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
	
	protected BudgetModel(String name, double amount, boolean isShared, boolean isRecurrent, Date startingDate, Date endingDate, int gap) {
		
		setName(name);
		setAmount(amount);
		setShared(isShared);
		setRecurrent(isRecurrent);
		setStartingDate(startingDate);
		setEndingDate(endingDate);
		setGap(gap);
		
	}
	
	/**
	 * Create a category entry for the user into the database.
	 *
	 * @param orm ORM instance to use.
	 */
	protected void createBudget(IORM orm) {
		
		try {
			
			orm.beginTransaction();
			
			IBudgetRepository repo = orm.getBudgetRepository();
			setId(repo.addBudget(DALBudgetMapper.toDboPG(this)));
			
			orm.commit();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Update category entry with new data.
	 *
	 * @param orm ORM instance to use.
	 */
	protected void updateBudget(IORM orm) {
		
		try {
			
			orm.beginTransaction();
			
			IBudgetRepository repo = orm.getBudgetRepository();
			repo.update(DALBudgetMapper.toDboPG(this));
			
			orm.commit();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * TODO
	 *
	 * @return
	 */
	public int getId() {
		
		return id;
	}
	
	/**
	 * TODO
	 *
	 * @return
	 */
	public int getClientID() {
		
		return clientID;
	}
	
	/**
	 * TODO
	 *
	 * @return
	 */
	public int getGap() {
		
		return gap;
	}
	
	/**
	 * TODO
	 *
	 * @return
	 */
	public double getAmount() {
		
		return amount;
	}
	
	/**
	 * TODO
	 *
	 * @return
	 */
	public String getName() {
		
		return name;
	}
	
	/**
	 * TODO
	 *
	 * @return
	 */
	public boolean isShared() {
		
		return isShared;
	}
	
	/**
	 * TODO
	 *
	 * @return
	 */
	public boolean isRecurrent() {
		
		return isRecurrent;
	}
	
	/**
	 * TODO
	 *
	 * @return
	 */
	public Date getStartingDate() {
		
		return startingDate;
	}
	
	/**
	 * TODO
	 *
	 * @return
	 */
	public Date getEndingDate() {
		
		return endingDate;
	}
	
	
	/**
	 * TODO
	 *
	 * @param id
	 */
	public void setId(int id) {
		
		this.id = id;
	}
	
	/**
	 * TODO
	 *
	 * @param clientID
	 */
	public void setClientID(int clientID) {
		
		this.clientID = clientID;
	}
	
	/**
	 * TODO
	 *
	 * @param gap
	 */
	public void setGap(int gap) {
		
		this.gap = gap;
	}
	
	/**
	 * TODO
	 *
	 * @param amount
	 */
	public void setAmount(double amount) {
		
		this.amount = amount;
	}
	
	/**
	 * TODO
	 *
	 * @param name
	 */
	public void setName(String name) {
		
		this.name = name;
	}
	
	/**
	 * TODO
	 *
	 * @param shared
	 */
	public void setShared(boolean shared) {
		
		isShared = shared;
	}
	
	/**
	 * TODO
	 *
	 * @param recurrent
	 */
	public void setRecurrent(boolean recurrent) {
		
		isRecurrent = recurrent;
	}
	
	/**
	 * TODO
	 *
	 * @param startingDate
	 */
	public void setStartingDate(Date startingDate) {
		
		this.startingDate = startingDate;
	}
	
	/**
	 * TODO
	 *
	 * @param endingDate
	 */
	public void setEndingDate(Date endingDate) {
		
		this.endingDate = endingDate;
	}
}
