package dal.ientites;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * IDALCategoriesbudgetEntity interface.
 * interface who represent a category entity
 *
 * @author Guillaume Zaretti
 * @version 1.2
 */
public interface IDALCategoriesbudgetEntity {
	
	/**
	 * get the category id of the categories budget entity
	 *
	 * @return category id of the categories budget entity
	 */
	@Id
	@Column(name = "category_id", nullable = false)
	int getCategoryId();
	
	/**
	 * set the  category id of the categories budget entity
	 *
	 * @param budget id of the category budget entity
	 */
	void setCategoryId(int categoryId);
	
	/**
	 * get the budget id of the categories budget entity
	 *
	 * @return budget id of the categories budget entity
	 */
	@Id
	@Column(name = "budget_id", nullable = false)
	int getBudgetId();
	
	/**
	 * set the  budget id of the categories budget entity
	 *
	 * @param budget id of the categories budget entity
	 */
	void setBudgetId(int budgetId);
}
