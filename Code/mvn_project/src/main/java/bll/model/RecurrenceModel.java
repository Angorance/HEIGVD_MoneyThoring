package bll.model;

import bll.mappers.DAL.DALRecurrenceMapper;
import dal.irepositories.IRecurrenceRepository;
import dal.orm.IORM;

import java.sql.Date;

/**
 * RecurrenceModel class.
 * Allows the mapping between the DAL entities and the Business Logic.
 * Only implements the constructors, the getters and the setters.
 *
 * Functionality not implemented.
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
	 * Create a transaction entry for the user into the database.
	 *
	 * @param orm ORM instance to use.
	 */
	protected void createRecurrence(IORM orm) {
		
		try {
			
			orm.beginTransaction();
			
			IRecurrenceRepository repo = orm.getRecurrenceRepository();
			setId(repo.addRecurrence(DALRecurrenceMapper.toDbo(this)));
			
			orm.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Update transaction entry with new data.
	 *
	 * @param orm ORM instance to use.
	 */
	protected void updateRecurrence(IORM orm) {
		
		try {
			
			orm.beginTransaction();
			
			IRecurrenceRepository repo = orm.getRecurrenceRepository();
			repo.update(DALRecurrenceMapper.toDbo(this));
			
			orm.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Get the ID.
	 *
	 * @return
	 */
	public int getId() {
		
		return id;
	}
	
	/**
	 * Get the transaction ID.
	 *
	 * @return
	 */
	public int getTransactionID() {
		
		return transactionID;
	}
	
	/**
	 * Get the gap.
	 *
	 * @return
	 */
	public int getGap() {
		
		return gap;
	}
	
	/**
	 * Get the next date.
	 *
	 * @return
	 */
	public Date getNextDate() {
		
		return nextDate;
	}
	
	
	/**
	 * Set the ID.
	 *
	 * @param id
	 */
	public void setId(int id) {
		
		this.id = id;
	}
	
	/**
	 * Set the transaction ID.
	 *
	 * @param transactionID
	 */
	public void setTransactionID(int transactionID) {
		
		this.transactionID = transactionID;
	}
	
	/**
	 * Set the gap.
	 *
	 * @param gap
	 */
	public void setGap(int gap) {
		
		this.gap = gap;
	}
	
	/**
	 * Set the next date.
	 *
	 * @param nextDate
	 */
	public void setNextDate(Date nextDate) {
		
		this.nextDate = nextDate;
	}
}
