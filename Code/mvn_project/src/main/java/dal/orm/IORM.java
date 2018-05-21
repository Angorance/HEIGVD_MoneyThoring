package dal.orm;

import dal.dalexception.DALException;
import dal.irepositories.*;

/**
 * IORM interface.
 * the interface who use all repositories, handle the session andtransaction
 * into each repositories
 *
 * @author Guillaume Zaretti
 * @version 1.2
 */
public interface IORM {
	
	/**
	 * Construct an single instance of IClientRepository and return it
	 *
	 * @return an instance of IClientRepository
	 */
	IClientRepository getClientRepository();
	
	/**
	 * Construct an single instance of IBankaccountRepository and return it
	 *
	 * @return an instance of IBankaccountRepository
	 */
	IBankaccountRepository getBankaccountRepository();
	
	/**
	 * Construct an single instance of ICategoryRepository and return it
	 *
	 * @return an instance of ICategoryRepository
	 */
	ICategoryRepository getCategoryRepository();
	
	/**
	 * Construct an single instance of IBudgetRepository and return it
	 *
	 * @return an instance of IBudgetRepository
	 */
	IBudgetRepository getBudgetRepository();
	
	/**
	 * Construct an single instance of IDebtRepository and return it
	 *
	 * @return an instance of IDebtRepository
	 */
	IDebtRepository getDebtRepository();
	
	/**
	 * Construct an single instance of IRecurrenceRepository and return it
	 *
	 * @return an instance of IClientRepository
	 */
	IRecurrenceRepository getRecurrenceRepository();
	
	/**
	 * Construct an single instance of IIotransactionRepository
	 *
	 * @throws DALException
	 */
	IIotransactionRepository getIotransactionRepository();
	
	/**
	 * Construct an single instance of ICategoriesBudgetRepository
	 *
	 * @throws DALException
	 */
	ICategoriesBudgetRepository getCategoriesBudgetRepository();
	
	/**
	 * Construct an single instance of ISharedBudgetRepository
	 *
	 * @throws DALException
	 */
	ISharedBudgetRepository getSharedBudgetRepository();
	
	/**
	 * begin a transaction shared between all repositories
	 *
	 * @throws DALException
	 */
	void beginTransaction() throws DALException;
	
	
	/**
	 * rollback transaction shared between all repositories
	 *
	 * @throws DALException
	 */
	void rollback() throws DALException;
	
	/**
	 * commit transaction shared between all repositories
	 *
	 * @throws DALException
	 */
	void commit() throws DALException;
	
}
