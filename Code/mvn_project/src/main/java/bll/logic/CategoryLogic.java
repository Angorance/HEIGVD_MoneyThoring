package bll.logic;

import bll.model.CategoryModel;
import dal.dalexception.DALException;
import dal.ientites.IDALCategoryEntity;
import dal.orm.IORM;
import dal.orm.PgORM;

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
        
        createCategory(new PgORM());
    }
    
    /**
     * TODO
     */
    public void update(String name, String color) {
    	
    	setName(name);
    	setColor(color);
    
    	updateCategory(new PgORM());
    }
    
    public void supp() {
	
	    try {
	    	IORM orm = new PgORM();
	    	
	    	orm.beginTransaction();
	    	
	    	orm.getCategoryRepository().delete(getId());
	    	orm.commit();
	    	
	    } catch (DALException e) {
		    e.printStackTrace();
	    }
    }
	
	@Override
	public String toString() {
		
		return getName();
	}
}
