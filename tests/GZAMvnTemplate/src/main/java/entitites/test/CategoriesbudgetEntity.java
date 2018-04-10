package entitites.test;

import javax.persistence.*;

@Entity
@Table(name = "categoriesbudget", schema = "public", catalog = "moneythoring")
@IdClass(CategoriesbudgetEntityPK.class)
public class CategoriesbudgetEntity {
    private int categoryId;
    private int budgetId;
    private BudgetEntity budgetByBudgetId;

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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoriesbudgetEntity that = (CategoriesbudgetEntity) o;

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

    @ManyToOne
    @JoinColumn(name = "budget_id", referencedColumnName = "id", nullable = false)
    public BudgetEntity getBudgetByBudgetId() {
        return budgetByBudgetId;
    }

    public void setBudgetByBudgetId(BudgetEntity budgetByBudgetId) {
        this.budgetByBudgetId = budgetByBudgetId;
    }
}
