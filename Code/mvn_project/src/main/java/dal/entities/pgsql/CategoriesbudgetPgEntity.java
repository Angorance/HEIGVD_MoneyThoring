package dal.entities.pgsql;

import dal.ientites.IDALCategoriesbudgetEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * TODO
 */
@Entity
@Table(name = "categoriesbudget", schema = "moneythoring",
		catalog = "moneythoring")
@IdClass(CategoriesbudgetPgEntityPK.class)
public class CategoriesbudgetPgEntity
		implements IDALCategoriesbudgetEntity, Serializable {
	
	private int categoryId;
	private int budgetId;
	
	@Id
	@Column(name = "category_id", nullable = false)
	public int getCategoryId() {
		
		return categoryId;
	}
	
	public void setCategoryId(int categoryId) {
		
		this.categoryId = categoryId;
	}
	
	@Id
	@Column(name = "budget_id", nullable = false)
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
		CategoriesbudgetPgEntity that = (CategoriesbudgetPgEntity) o;
		return categoryId == that.categoryId && budgetId == that.budgetId;
	}
	
	@Override
	public int hashCode() {
		
		return Objects.hash(categoryId, budgetId);
	}
}
