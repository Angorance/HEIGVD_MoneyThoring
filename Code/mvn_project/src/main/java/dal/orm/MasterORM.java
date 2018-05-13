package dal.orm;

/**
 * TODO
 *
 * @author Daniel Gonzalez Lopez
 * @version 1.0
 */
public class MasterORM {
	
	private IORM derby;
	private IORM postgre;
	
	private MasterORM() {
		derby = new DerbyORM();
		postgre = new PgORM();
	}
	
	private static class Instance {
		
		static final MasterORM instance = new MasterORM();
	}
	
	public static MasterORM getInstance() {
		
		return Instance.instance;
	}
	
	public IORM getPgORM() {
		return postgre;
	}
	
	public IORM getDeORM() {
		return derby;
	}
}
