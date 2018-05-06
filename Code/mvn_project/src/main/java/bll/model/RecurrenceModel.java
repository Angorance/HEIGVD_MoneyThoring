package bll.model;

import java.sql.Date;

/**
 * TODO
 *
 * @author Daniel Gonzalez Lopez
 * @version 1.0
 */
public class RecurrenceModel {
	private int id;
	private int transactionID;
	private int gap;
	
	private Date nextDate;
	
	
	protected RecurrenceModel() {}
	
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
	public int getTransactionID() {
		
		return transactionID;
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
	public Date getNextDate() {
		
		return nextDate;
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
	 * @param transactionID
	 */
	public void setTransactionID(int transactionID) {
		
		this.transactionID = transactionID;
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
	 * @param nextDate
	 */
	public void setNextDate(Date nextDate) {
		
		this.nextDate = nextDate;
	}
}
