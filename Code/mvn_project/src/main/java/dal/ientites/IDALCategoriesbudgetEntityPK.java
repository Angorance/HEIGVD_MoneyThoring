package dal.ientites;

import javax.persistence.Column;
import javax.persistence.Id;

public interface IDALCategoriesbudgetEntityPK {
    @Column(name = "category_id", nullable = false)
    @Id
    int getCategoryId();

    void setCategoryId(int categoryId);

    @Column(name = "budget_id", nullable = false)
    @Id
    int getBudgetId();

    void setBudgetId(int budgetId);
}
