package bll.logic;

import bll.model.CategoryModel;
import dal.dalexception.DALException;
import dal.orm.IORM;
import dal.orm.MasterORM;

import java.util.ArrayList;

/**
 * TODO
 *
 * @author Daniel Gonzalez Lopez
 * @version 1.0
 */
public class CategoryLogic extends CategoryModel {
	
	public CategoryLogic() {
		
		ClientLogic.getInstance().addCategory(this);
	}
	
	public CategoryLogic(String name, String color, boolean isDefault) {
		
		super(name, color, isDefault);
		
		ClientLogic.getInstance().addCategory(this);
		
		createCategory(MasterORM.getInstance().getORM());
	}
	
	/**
	 * TODO
	 */
	public void update(String name, String color) {
		
		setName(name);
		setColor(color);
		
		updateCategory(MasterORM.getInstance().getORM());
	}
	
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
					// Change the category be the default one
					for (IOTransactionLogic transaction : transactions) {
						transaction.setCategory(getDefaultCategory());
						
						// Update the transactions
						transaction.updateIOTransaction(orm);
					}
				}
				
				// TODO - Do the same for budgets !!!
			}
			
			orm.getCategoryRepository().delete(getId());
			
			orm.commit();
			
			IOTransactionLogic.updateTransactionsOnCategoryDeletion(this);
			ClientLogic.getInstance().removeCategory(this);
			
		} catch (DALException e) {
			e.printStackTrace();
		}
	}
	
	public static CategoryLogic getByID(int categoryID) {
		
		for (CategoryLogic cl : ClientLogic.getInstance().getCategories()) {
			if (cl.getId() == categoryID) {
				return cl;
			}
		}
		
		return null;
	}
	
	public static CategoryLogic getDefaultCategory() {
		
		for (CategoryLogic cl : ClientLogic.getInstance().getCategories()) {
			if (cl.isDefault()) {
				return cl;
			}
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		
		return getName();
	}
}
