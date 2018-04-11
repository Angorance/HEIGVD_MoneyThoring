package dal.entities.derby;

import javax.persistence.*;

@Entity
@Table(name = "CATEGORIESBUDGET", schema = "MONEYTHORING", catalog = "")
@IdClass(CategoriesbudgetDeEntityPK.class)
public class CategoriesbudgetDeEntity {
    private int categoryId;
    private int budgetId;
    private CategoryDeEntity categoryByCategoryId;
    private BudgetDeEntity budgetByBudgetId;

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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoriesbudgetDeEntity that = (CategoriesbudgetDeEntity) o;

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
    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID", nullable = false)
    public CategoryDeEntity getCategoryByCategoryId() {
        return categoryByCategoryId;
    }

    public void setCategoryByCategoryId(CategoryDeEntity categoryByCategoryId) {
        this.categoryByCategoryId = categoryByCategoryId;
    }

    @ManyToOne
    @JoinColumn(name = "BUDGET_ID", referencedColumnName = "ID", nullable = false)
    public BudgetDeEntity getBudgetByBudgetId() {
        return budgetByBudgetId;
    }

    public void setBudgetByBudgetId(BudgetDeEntity budgetByBudgetId) {
        this.budgetByBudgetId = budgetByBudgetId;
    }
}
