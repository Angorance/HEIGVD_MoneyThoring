package entitites.test;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class CategoriesbudgetEntityPK implements Serializable {
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoriesbudgetEntityPK that = (CategoriesbudgetEntityPK) o;

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
