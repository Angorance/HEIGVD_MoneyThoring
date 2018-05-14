package dal.ientites;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * TODO
 */
public interface IDALCategoriesbudgetEntity {
	
	@Id
	@Column(name = "category_id", nullable = false)
	int getCategoryId();
	
	void setCategoryId(int categoryId);
	
	@Id
	@Column(name = "budget_id", nullable = false)
	int getBudgetId();
	
	void setBudgetId(int budgetId);
}
