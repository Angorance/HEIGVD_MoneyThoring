package dal.entities.pgsql;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Guillaume zaretti
 * @version 1.2
 * @see IDALCategoriesbudgetEntityPK
 */
public class CategoriesbudgetPgEntityPK implements Serializable, IDALCategoriesbudgetEntityPK {
	
	private int categoryId;
	private int budgetId;
	
	@Column(name = "category_id", nullable = false)
	@Id
	public int getCategoryId() {
		
		return categoryId;
	}
	
	public void setCategoryId(int categoryId) {
		
		this.categoryId = categoryId;
	}
	
	@Column(name = "budget_id", nullable = false)
	@Id
	public int getBudgetId() {
		
		return budgetId;
	}
	
	public void setBudgetId(int budgetId) {
		
		this.budgetId = budgetId;
	}
	
	@Override
	public boolean equals(Object o) {
		
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		CategoriesbudgetPgEntityPK that = (CategoriesbudgetPgEntityPK) o;
		return categoryId == that.categoryId && budgetId == that.budgetId;
	}
	
	@Override
	public int hashCode() {
		
		return Objects.hash(categoryId, budgetId);
	}
}
