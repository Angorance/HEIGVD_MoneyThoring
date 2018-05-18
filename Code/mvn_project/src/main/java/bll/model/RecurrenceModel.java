package bll.model;

import bll.mappers.DAL.DALRecurrenceMapper;
import dal.irepositories.IRecurrenceRepository;
import dal.orm.IORM;

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
