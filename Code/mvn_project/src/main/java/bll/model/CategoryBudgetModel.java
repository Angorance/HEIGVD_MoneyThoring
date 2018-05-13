package bll.model;

import bll.mappers.DAL.DALCategoryBudgetMapper;
import dal.irepositories.ICategoriesBudgetRepository;
import dal.orm.IORM;

/**
 * @author Héléna Line Reymond
 * @version 1.0
 */
public class CategoryBudgetModel {
	
	private int category_id;
	private int budget_id;
	
	// Protected default constructor. Avoid instances creation outside package.
	public CategoryBudgetModel() {}
	
	/**
	 * Construct an instance of CategoryBudgetModel with the given parameters.
	 *
	 * @param category_id ID of the category.
	 * @param budget_id ID of the budget.
	 */
	protected CategoryBudgetModel(int category_id, int budget_id) {
		
		this.category_id = category_id;
		this.budget_id = budget_id;
	}
	
	/**
	 * Create an entry for the user into the database.
	 *
	 * @param orm ORM instance to use.
	 */
	protected void createCategoryBudget(IORM orm) {
		
		try {
			
			orm.beginTransaction();
			
			ICategoriesBudgetRepository repo = orm.getCategoriesBudgetRepository();
			repo.addCategoriesBudget(DALCategoryBudgetMapper.toDboPG(this));
			
			orm.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Get the category ID.
	 *
	 * @return category ID.
	 */
	public int getCategoryID() {
		
		return category_id;
	}
	
	/**
	 * Get the budget ID.
	 *
	 * @return budget ID.
	 */
	public int getBudgetID() {
		
		return budget_id;
	}
	
	/**
	 * Set the category ID.
	 *
	 * @return category ID.
	 */
	public void setCategoryID(int ID) {
		
		category_id = ID;
	}
	
	/**
	 * Set the budget ID.
	 *
	 * @return budget ID.
	 */
	public void setBudgetID(int ID) {
		
		budget_id = ID;
	}
}
