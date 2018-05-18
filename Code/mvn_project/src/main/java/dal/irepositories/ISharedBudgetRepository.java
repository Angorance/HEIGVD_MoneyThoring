package dal.irepositories;

import dal.dalexception.DALException;
import dal.ientites.IDALSharedbudgetEntity;

import java.util.List;

/**
 * ISharedbudgetRepository give the access methodes for handle the Sharedbudget
 * into persistence
 *
 * @author Guillaume Zaretti
 * @author Héléna Line Reymond
 * @version 1.2
 */
public interface ISharedBudgetRepository {
	
	/**
	 * Retreave a Sharedbudget by client id and budget id
	 *
	 * @param client_id id of the client
	 * @param budget_id id of the budget
	 *
	 * @return IDALSharedbudgetEntity the Sharedbudget
	 *
	 * @throws DALException
	 */
	public IDALSharedbudgetEntity getSharedbudget(int client_id, int budget_id)
			throws DALException;
	
	/**
	 * Retreave all shared budget
	 *
	 * @return List<IDALSharedbudgetEntity> the list of shared budget
	 *
	 * @throws DALException
	 */
	public List<IDALSharedbudgetEntity> getSharedbudgets() throws DALException;
	
	/**
	 * Update otransaction
	 *
	 * @param sharedBudget the debt would'you update
	 *
	 * @throws DALException
	 */
	public void update(IDALSharedbudgetEntity sharedBudget) throws DALException;
	
	/**
	 * add a shared budget
	 *
	 * @param sharedBudget the shared budget would'you add
	 *
	 * @throws DALException
	 */
	public void addSharedbudget(IDALSharedbudgetEntity sharedBudget)
			throws DALException;
	
	/**
	 * delete a sharedbudget
	 *
	 * @param sharedBudget the shared budget would'you delete
	 *
	 * @throws DALException
	 */
	public void delete(IDALSharedbudgetEntity sharedBudget) throws DALException;
	
	
	/**
	 * Retreave the Sharedbudget by client id
	 *
	 * @param client_id id of the client
	 *
	 * @return IDALSharedbudgetEntity the Sharedbudget
	 *
	 * @throws DALException
	 */
	public List<IDALSharedbudgetEntity> getSharedbudgetByClient(int client_id)
			throws DALException;
	
	/**
	 * Retreave a Sharedbudget by budget id
	 *
	 * @param budget_id id of the budget
	 *
	 * @return IDALSharedbudgetEntity the Sharedbudget
	 *
	 * @throws DALException
	 */
	public List<IDALSharedbudgetEntity> getSharedbudgetByBudget(int budget_id)
			throws DALException;
	
    /**
     * delete all SharedBudget
     * @param budget_id  ID of the budget.
     * @throws DALException
     */
    public void delete(int budget_id) throws DALException;
}
