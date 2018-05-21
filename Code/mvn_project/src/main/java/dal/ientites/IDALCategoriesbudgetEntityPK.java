package dal.ientites;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * IDALCategoriesbudgetEntityPK interface.
 * interface who represent a category budget pk entity
 *
 * @author Guillaume Zaretti
 * @version 1.2
 */
public interface IDALCategoriesbudgetEntityPK {
	
	/**
	 * get the category id of the categories budget entity pk
	 *
	 * @return category id of the categories budget entity pk
	 */
	@Column(name = "category_id", nullable = false)
	@Id
	int getCategoryId();
	
	/**
	 * set the  category id of the categories budget entity pk
	 *
	 * @param budget id of the category budget entity pk
	 */
	void setCategoryId(int categoryId);
	
	/**
	 * get the budget id of the categories budget entity pk
	 *
	 * @return budget id of the categories budget entity pk
	 */
	@Column(name = "budget_id", nullable = false)
	@Id
	int getBudgetId();
	
	/**
	 * set the  budget id of the categories budget entity pk
	 *
	 * @param budget id of the categories budget entity pk
	 */
	void setBudgetId(int budgetId);
}
