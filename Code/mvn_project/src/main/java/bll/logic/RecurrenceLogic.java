package bll.logic;

import bll.model.RecurrenceModel;
import dal.orm.PgORM;

/**
 * TODO
 *
 * @author Daniel Gonzalez Lopez
 * @version 1.0
 */
public class RecurrenceLogic extends RecurrenceModel {
	
	private IOTransactionLogic transaction;
	
	public RecurrenceLogic() {}
	
	public RecurrenceLogic(IOTransactionLogic transaction, int gap) {
	
		setTransaction(transaction);
		setGap(gap);
		
		createRecurrence(new PgORM());
	}
	
	public void setTransaction(IOTransactionLogic transaction) {
		
		this.transaction = transaction;
		setTransactionID(transaction.getId());
	}
}
