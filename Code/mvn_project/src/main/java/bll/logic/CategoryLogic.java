package bll.logic;

import bll.model.CategoryModel;
import dal.dalexception.DALException;
import dal.ientites.IDALCategoryEntity;
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
    }
    
    public CategoryLogic(IDALCategoryEntity cat) {
        setId(cat.getId());
        setName(cat.getName());
        setColor(cat.getColour());
        setDefault(cat.isIsdefault());
        setClientId(cat.getClientId());
	
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
		    new PgORM().getCategoryRepository().delete(getId());
	    } catch (DALException e) {
		    e.printStackTrace();
	    }
    }
}
