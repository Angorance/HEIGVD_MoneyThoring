package dal.irepositories;

import dal.dalexception.DALException;
import dal.ientites.IDALBudgetEntity;

import java.util.List;

/**
 * IBudgetRepository give the access methodes for handle the budget into persistence
 */
public interface IBudgetRepository {
    /**
     * Retreave a budget by this id
     *
     * @param id of the budget
     * @return IDALBudgetEntity the budget
     * @throws DALException
     */
    public IDALBudgetEntity getBudget(int id) throws DALException;

    /**
     * Retreave all budgets
     *
     * @return List<IDALBudgetEntity> the list of budgets
     * @throws DALException
     */
    public List<IDALBudgetEntity> getBudgets() throws DALException;

    /**
     * Retreave the budgets by client id
     *
     * @param id of the client
     * @return List<IDALBankaccountEntity> the list of budgets by client id
     * @throws DALException
     */
    public List<IDALBudgetEntity> getBudgetsByClient(int id) throws DALException;

    /**
     * Update a budget
     *
     * @param Budget would'you update
     * @throws DALException
     */
    public void update(IDALBudgetEntity budget) throws DALException;

    /**
     * add a budget
     *
     * @param Budget would'you add
     * @throws DALException
     */
    public void addBudget(IDALBudgetEntity budget) throws DALException;

    /**
     * delete a budget by id
     *
     * @param id the budget id would'you delete
     * @throws DALException
     */
    public void delete(int id) throws DALException;
}
