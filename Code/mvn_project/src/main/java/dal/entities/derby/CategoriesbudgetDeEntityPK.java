package dal.entities.derby;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class CategoriesbudgetDeEntityPK implements Serializable {
    private int categoryId;
    private int budgetId;

    @Column(name = "CATEGORY_ID", nullable = false)
    @Id
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Column(name = "BUDGET_ID", nullable = false)
    @Id
    public int getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(int budgetId) {
        this.budgetId = budgetId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoriesbudgetDeEntityPK that = (CategoriesbudgetDeEntityPK) o;

        if (categoryId != that.categoryId) return false;
        if (budgetId != that.budgetId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = categoryId;
        result = 31 * result + budgetId;
        return result;
    }
}
