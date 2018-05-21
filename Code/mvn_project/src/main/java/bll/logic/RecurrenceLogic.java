package bll.logic;

import bll.model.RecurrenceModel;
import dal.orm.IORM;
import dal.orm.MasterORM;

/**
 * RecurrenceLogic class.
 * Allows to manage recurrent transactions. Not implemented.
 *
 * @author Daniel Gonzalez Lopez
 * @version 1.0
 */
public class RecurrenceLogic extends RecurrenceModel {
	
	private IOTransactionLogic transaction;
	
	public RecurrenceLogic() {}
	
	/**
	 * Construct an instance and create the entry in the database.
	 *
	 * @param transaction
	 * @param gap
	 */
	public RecurrenceLogic(IOTransactionLogic transaction, int gap) {
		
		setTransaction(transaction);
		setGap(gap);
		
		createRecurrence(MasterORM.getInstance().getORM());
	}
	
	/**
	 * Set the transaction concerned by the recurrence.
	 *
	 * @param transaction
	 */
	public void setTransaction(IOTransactionLogic transaction) {
		
		this.transaction = transaction;
		setTransactionID(transaction.getId());
		transaction.setRecurrence(this);
	}
	
	/**
	 * Suppress the reccurence from the application and the database.
	 */
	public void supp() {
		
		try {
			IORM orm = MasterORM.getInstance().getORM();
			
			orm.beginTransaction();
			
			orm.getRecurrenceRepository().delete(getId());
			orm.commit();
			
		} catch (dal.dalexception.DALException e) {
			e.printStackTrace();
		}
	}
}
