package bll.logic;

import bll.model.CategoryModel;
import dal.dalexception.DALException;
import dal.ientites.IDALCategoriesbudgetEntity;
import dal.orm.IORM;
import dal.orm.MasterORM;

import java.util.ArrayList;
import java.util.List;

/**
 * CategoryLogic class.
 * Implements the business logic of the CategoryModel.
 * Implements methods to create, change and delete categories.
 * Before changing these attributes, the methods check their integrity
 * to avoid data problems.
 *
 * @author Daniel Gonzalez Lopez
 * @author Héléna Line Reymond
 * @version 2.0
 */
public class CategoryLogic extends CategoryModel {
	
	/**
	 * Construct an instance and link it to the user instance.
	 */
	public CategoryLogic() {
		
		ClientLogic.getInstance().addCategory(this);
	}
	
	/**
	 * Construct an instance, link it to the user instance and create the entry
	 * in the database.
	 *
	 * @param name
	 * @param color
	 * @param isDefault (Only used for database created categories)
	 */
	public CategoryLogic(String name, String color, boolean isDefault) {
		
		super(name, color, isDefault);
		
		ClientLogic.getInstance().addCategory(this);
		
		createCategory(MasterORM.getInstance().getORM());
	}
	
	/**
	 * Update the category and its entry in the database.
	 */
	public void update(String name, String color) {
		
		setName(name);
		setColor(color);
		
		updateCategory(MasterORM.getInstance().getORM());
	}
	
	/**
	 * Delete a category from the database and kill all links it has in the
	 * application (user, budgets, ...)
	 */
	public void supp() {
		
		try {
			IORM orm = MasterORM.getInstance().getORM();
			
			orm.beginTransaction();
			
			// If the category to delete is used in transactions
			// change their category by the default one.
			if (!ClientLogic.getInstance().isOnline()) {
				
				// Get the transactions using this category
				ArrayList<IOTransactionLogic> transactions = IOTransactionLogic
						.getTransactionsByCategory().get(this);
				
				if (transactions != null) {
					
					// Change the category by the default one
					for (IOTransactionLogic transaction : transactions) {
						transaction.setCategory(getDefaultCategory());
						
						// Update the transactions
						transaction.updateIOTransaction(orm);
					}
				}
			}
			
			// Get the budgets using this category
			List<IDALCategoriesbudgetEntity> budgetLinks = orm
					.getCategoriesBudgetRepository()
					.getCategoriesBudgetByCategory(getId());
			
			if (budgetLinks != null) {
				
				// Change the category by the default one
				for (IDALCategoriesbudgetEntity budgetLink : budgetLinks) {
					
					// Delete the link with the budget
					orm.getCategoriesBudgetRepository().delete(budgetLink);
					
					// Update the budget
					BudgetLogic budget = ClientLogic.getInstance()
							.getBudget(budgetLink.getBudgetId());
					
					budget.removeCategory(this);
				}
			}
			
			orm.getCategoryRepository().delete(getId());
			
			orm.commit();
			
			IOTransactionLogic.updateTransactionsOnCategoryDeletion(this);
			ClientLogic.getInstance().removeCategory(this);
			
		} catch (DALException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Get the category of the given ID.
	 *
	 * @param categoryID
	 *
	 * @return CategoryLogic instance of the corresponding ID.
	 */
	public static CategoryLogic getByID(int categoryID) {
		
		for (CategoryLogic cl : ClientLogic.getInstance().getCategories()) {
			if (cl.getId() == categoryID) {
				return cl;
			}
		}
		
		return null;
	}
	
	/**
	 * Get the default category.
	 *
	 * @return CategoryLogic instance of the default category.
	 */
	public static CategoryLogic getDefaultCategory() {
		
		for (CategoryLogic cl : ClientLogic.getInstance().getCategories()) {
			if (cl.isDefault()) {
				return cl;
			}
		}
		
		return null;
	}
	
	/**
	 * Get the representation of the category (its name)
	 *
	 * @return String representing the name.
	 */
	@Override
	public String toString() {
		
		return getName();
	}
}
