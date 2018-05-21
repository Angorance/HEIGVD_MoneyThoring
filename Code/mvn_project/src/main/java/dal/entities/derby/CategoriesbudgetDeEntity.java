package dal.entities.derby;

import dal.ientites.IDALCategoriesbudgetEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "CATEGORIESBUDGET", schema = "MONEYTHORING",
		catalog = "moneythoring")
@IdClass(CategoriesbudgetDeEntityPK.class)
public class CategoriesbudgetDeEntity
		implements IDALCategoriesbudgetEntity, Serializable {
	
	private int categoryId;
	private int budgetId;
	
	@Id
	@Column(name = "CATEGORY_ID", nullable = false)
	public int getCategoryId() {
		
		return categoryId;
	}
	
	public void setCategoryId(int categoryId) {
		
		this.categoryId = categoryId;
	}
	
	@Id
	@Column(name = "BUDGET_ID", nullable = false)
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
		CategoriesbudgetDeEntity that = (CategoriesbudgetDeEntity) o;
		return categoryId == that.categoryId && budgetId == that.budgetId;
	}
	
	@Override
	public int hashCode() {
		
		return Objects.hash(categoryId, budgetId);
	}
}
