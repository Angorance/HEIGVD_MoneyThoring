package bll.logic;

import bll.model.CategoryModel;
import dal.dalexception.DALException;
import dal.ientites.IDALCategoryEntity;
import dal.orm.IORM;
import dal.orm.MasterORM;
import dal.orm.PgORM;

import java.util.HashMap;

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
        
        createCategory(MasterORM.getInstance().getPgORM());
    }
    
    /**
     * TODO
     */
    public void update(String name, String color) {
    	
    	setName(name);
    	setColor(color);
    
    	updateCategory(MasterORM.getInstance().getPgORM());
    }
    
    public void supp() {
	
	    try {
	    	IORM orm = MasterORM.getInstance().getPgORM();
	    	
	    	orm.beginTransaction();
	    	
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
