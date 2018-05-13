package bll.logic;

import bll.model.RecurrenceModel;
import dal.orm.IORM;
import dal.orm.MasterORM;
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
		
		createRecurrence(MasterORM.getInstance().getPgORM());
	}
	
	public void setTransaction(IOTransactionLogic transaction) {
		
		this.transaction = transaction;
		setTransactionID(transaction.getId());
		transaction.setRecurrence(this);
	}
	
	public void supp() {
		
		try {
			IORM orm = MasterORM.getInstance().getPgORM();
			
			orm.beginTransaction();
			
			orm.getRecurrenceRepository().delete(getId());
			orm.commit();
			
		} catch (dal.dalexception.DALException e) {
			e.printStackTrace();
		}
	}
}
