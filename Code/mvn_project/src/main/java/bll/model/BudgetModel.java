package bll.model;

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
