package bll.logic;

import bll.mappers.DAL.DALBudgetMapper;
import bll.mappers.DAL.DALCategoryBudgetMapper;
import bll.model.BudgetModel;
import bll.model.CategoryBudgetModel;
import dal.dalexception.DALException;
import dal.ientites.IDALCategoriesbudgetEntity;
import dal.irepositories.ICategoriesBudgetRepository;
import dal.orm.IORM;
import dal.orm.MasterORM;

import java.sql.Date;
import java.util.*;

/**
 * TODO
 *
 * @author Daniel Gonzalez Lopez
 * @version 1.0
 */
public class BudgetLogic extends BudgetModel {
	
	private ArrayList<CategoryLogic> categories = new ArrayList<>();
	
	public BudgetLogic() {
		
		ClientLogic.getInstance().addBudget(this);
	}
	
	public BudgetLogic(String name, double amount, boolean isShared, boolean isRecurrent, Date startingDate,
			Date endingDate, int gap, ArrayList<CategoryLogic> categories) {
		
		super(name, amount, isShared, isRecurrent, startingDate, endingDate, gap);
		setCategoriesBudget(categories);
		ClientLogic.getInstance().addBudget(this);
		
		createBudget(MasterORM.getInstance().getPgORM());
		updateCategoriesBudget(MasterORM.getInstance().getPgORM());
	}
	
	/**
	 * TODO
	 */
	public void update(String name, double amount, boolean isShared, boolean isRecurrent,
			Date startingDate, Date endingDate, int gap, ArrayList<CategoryLogic> categories) {
		
		setName(name);
		setAmount(amount);
		setShared(isShared);
		setRecurrent(isRecurrent);
		setStartingDate(startingDate);
		setEndingDate(endingDate);
		setGap(gap);
		setCategoriesBudget(categories);
		
		updateBudget(MasterORM.getInstance().getPgORM());
		updateCategoriesBudget(MasterORM.getInstance().getPgORM());
	}
	
	/**
	 * TODO
	 */
	public void supp() {
		
		try {
			MasterORM.getInstance().getPgORM().getBudgetRepository()
					.delete(getId());
		} catch (DALException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Get the categories of the budget.
	 *
	 * @return categories of the budget.
	 */
	public ArrayList<CategoryLogic> getCategoriesBudget() {
		
		return categories;
	}
	
	/**
	 * Set the categories of the budget.
	 *
	 * @param categories Categories of the budget.
	 */
	private void setCategoriesBudget(ArrayList<CategoryLogic> categories) {
		
		categories.addAll(categories);
	}
	
	/**
	 * Update the categories of the budget.
	 */
	private void updateCategoriesBudget(IORM orm) {
		
		try {
			orm.beginTransaction();
			
			ICategoriesBudgetRepository repo = orm.getCategoriesBudgetRepository();
			
			// Delete CategoriesBudget for the id of the budget
			repo.delete(getId());
			
			// Add the new CategoriesBudget list
			for(CategoryLogic category : categories) {
				
				CategoryBudgetModel cat = new CategoryBudgetModel(category.getId(), getId());
				repo.addCategoriesBudget(DALCategoryBudgetMapper.toDboPG(cat));
			}
			
			orm.commit();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Update the categories of the budget from the DB.
	 */
	protected void setDataFromDB(IORM orm) {
		
		try {
			
			List<IDALCategoriesbudgetEntity> cb = orm.getCategoriesBudgetRepository()
					.getCategoriesBudgetByBudget(getId());
			
			for (CategoryBudgetModel b : DALCategoryBudgetMapper.toBos(cb)) {
				categories.add(CategoryLogic.getByID(b.getCategoryID()));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
