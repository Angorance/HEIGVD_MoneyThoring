package dal.irepositories;

import dal.dalexception.DALException;
import dal.ientites.IDALBudgetEntity;

import java.util.List;

public interface IBudgetRepository {
    public IDALBudgetEntity getBudget(int id) throws DALException;

    public List<IDALBudgetEntity> getBudgets() throws DALException;

    public void update(IDALBudgetEntity Budget) throws DALException;

    public void addBudget(IDALBudgetEntity Budget) throws DALException;

    public void delete(int id) throws DALException;
}
