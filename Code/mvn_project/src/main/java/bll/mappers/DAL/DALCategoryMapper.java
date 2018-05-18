package bll.mappers.DAL;

import bll.logic.CategoryLogic;
import bll.logic.ClientLogic;
import bll.model.CategoryModel;
import dal.entities.derby.CategoryDeEntity;
import dal.entities.pgsql.CategoryPgEntity;
import dal.ientites.IDALCategoryEntity;

import java.util.*;

/**
 * Class used to map a CategoryModel to an IDALCategoryEntity
 *
 * @author Héléna Line Reymond
 * @version 1.0
 */
public class DALCategoryMapper {
	
	/**
	 * CategoryModel -> PostgreSQL entity
	 */
	public static IDALCategoryEntity toDboPG(CategoryModel model) {
		
		if (model == null) {
			return null;
		}
		
		// Create the PostgreSQL category
		CategoryPgEntity pgEntity = new CategoryPgEntity();
		
		pgEntity.setId(model.getId());
		pgEntity.setName(model.getName());
		pgEntity.setColour(model.getColor());
		pgEntity.setIsdefault(model.isDefault());
		pgEntity.setClientId(model.getClientID());
		
		return pgEntity;
	}
	
	/**
	 * CategoryModel -> Derby entity
	 */
	public static IDALCategoryEntity toDboDe(CategoryModel model) {
		
		if (model == null) {
			return null;
		}
		
		// Create the Derby category
		CategoryDeEntity derbyEntity = new CategoryDeEntity();
		
		derbyEntity.setId(model.getId());
		derbyEntity.setName(model.getName());
		derbyEntity.setColour(model.getColor());
		derbyEntity.setIsdefault(model.isDefault());
		derbyEntity.setClientId(model.getClientID());
		
		return derbyEntity;
	}
	
	/**
	 * TODO
	 *
	 * @param model
	 * @return
	 */
	public static IDALCategoryEntity toDbo(CategoryModel model) {
		
		if (ClientLogic.getInstance().isOnline()) {
			return toDboPG(model);
		} else {
			return toDboDe(model);
		}
	}
	
	/**
	 * CategoriesModel -> Derby Entities
	 */
	public static List<IDALCategoryEntity> toDbos(List<CategoryModel> models) {
		
		// Create the list of entities
		List<IDALCategoryEntity> entities = new ArrayList<IDALCategoryEntity>();
		
		for(CategoryModel model : models){
			entities.add(toDbo(model));
		}
		
		return entities;
	}
	
	/**
	 * Entity -> CategoryLogic
	 */
	public static CategoryLogic toBo(IDALCategoryEntity entity) {
		
		if (entity == null) {
			return null;
		}
		
		// Create the category
		CategoryLogic object = new CategoryLogic();
		
		object.setId(entity.getId());
		object.setName(entity.getName());
		object.setColor(entity.getColour());
		object.setDefault(entity.isIsdefault());
		object.setClientId(entity.getClientId());
		
		return object;
	}
	
	/**
	 * Entities -> CategoriesLogic
	 */
	public static List<CategoryLogic> toBos(List<IDALCategoryEntity> entities) {
		
		// Create the list of categories
		List<CategoryLogic> objects = new ArrayList<CategoryLogic>();
		
		for(IDALCategoryEntity entity : entities){
			objects.add(toBo(entity));
		}
		
		return objects;
	}
}
