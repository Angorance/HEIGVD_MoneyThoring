package dal.orm;

import dal.dalexception.DALException;
import dal.irepositories.*;

/**
 * TODO
 */
public interface IORM {
	
	/**
	 * Construct an single instance of clientrepositoriy and return it
	 *
	 * @return an instance of IClientRepository
	 */
	IClientRepository getClientRepository();
	
	/**
	 * Construct an single instance of clientrepositoriy and return it
	 *
	 * @return an instance of IBankaccountRepository
	 */
	IBankaccountRepository getBankaccountRepository();
	
	/**
	 * Construct an single instance of categoryrepostiory and return it
	 *
	 * @return an instance of ICategoryRepository
	 */
	ICategoryRepository getCategoryRepository();
	
	/**
	 * Construct an single instance of budgetrepostiory and return it
	 *
	 * @return an instance of IBudgetRepository
	 */
	IBudgetRepository getBudgetRepository();
	
	/**
	 * Construct an single instance of dbbtRepository and return it
	 *
	 * @return an instance of IDebtRepository
	 */
	IDebtRepository getDebtRepository();
	
	/**
	 * Construct an single instance of clientrepositoriy and return it
	 *
	 * @return an instance of IClientRepository
	 */
	IRecurrenceRepository getRecurrenceRepository();
	
	/**
	 * begin a transaction shared bitween all IIotransactionRepository
	 *
	 * @throws DALException
	 */
	IIotransactionRepository getIotransactionRepository();
	
	/**
	 * begin a transaction shared bitween all repositories
	 *
	 * @throws DALException
	 */
	void beginTransaction() throws DALException;
	
	
	/**
	 * rollback transaction shared bitween all repositories
	 *
	 * @throws DALException
	 */
	void rollback() throws DALException;
	
	/**
	 * commit transaction shared bitween all repositories
	 *
	 * @throws DALException
	 */
	void commit() throws DALException;
	
}
