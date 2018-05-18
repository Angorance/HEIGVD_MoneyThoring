package dal.orm;

import bll.logic.ClientLogic;

/**
 * MasterORM Class
 * Allows to recover ORM instances depending on if we are connected of not.
 * Implemented as a Singleton as we recover always the same ORMs and we should
 * have multiple instances of this class.
 *
 * @author Daniel Gonzalez Lopez
 * @version 1.0
 */
public class MasterORM {
	
	private IORM derby;
	private IORM postgre;
	
	/**
	 * Construct the instance.
	 * Create the two ORM (Derby and PostGre).
	 */
	private MasterORM() {
		
		derby = new DerbyORM();
		postgre = new PgORM();
	}
	
	/**
	 * Static inner class to guarantee the Singleton concept.
	 */
	private static class Instance {
		
		static final MasterORM instance = new MasterORM();
	}
	
	/**
	 * Static function to recover the instance of the class.
	 *
	 * @return MasterORM single instance.
	 */
	public static MasterORM getInstance() {
		
		return Instance.instance;
	}
	
	/**
	 * Allows to get the ORM wanted depending on connection.
	 * PostGre for online connection.
	 * Derby for offline connection.
	 *
	 * @return Wanted ORM as IORM instance.
	 */
	public IORM getORM() {
		
		// If we are online, we use PostGre, otherwise we use Derby.
		if (ClientLogic.getInstance().isOnline()) {
			return postgre;
		} else {
			return derby;
		}
	}
}
