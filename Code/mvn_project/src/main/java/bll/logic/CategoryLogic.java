package bll.logic;

import bll.model.CategoryModel;
import dal.ientites.IDALCategoryEntity;

/**
 * TODO
 *
 * @authors Daniel Gonzalez Lopez
 * @version 1.0
 */
public class CategoryLogic extends CategoryModel {

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
    }
}
