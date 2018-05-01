package bll.mappers.DAL;

import bll.logic.CategoryLogic;
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
	 * Entity -> CategoryLogic
	 */
	public static CategoryLogic toBo(IDALCategoryEntity entity) {
		
		if (entity == null) {
			return null;
		}
		
		// Create the category
		CategoryLogic account = new CategoryLogic();
		
		account.setId(entity.getId());
		account.setName(entity.getName());
		account.setColor(entity.getColour());
		account.setDefault(entity.isIsdefault());
		account.setClientId(entity.getClientId());
		
		return account;
	}
	
	/**
	 * Entities -> CategoriesLogic
	 */
	public static List<CategoryLogic> toBos(List<IDALCategoryEntity> entities) {
		
		// Create the list of categories
		List<CategoryLogic> accounts = new ArrayList<CategoryLogic>();
		
		for(IDALCategoryEntity entity : entities){
			accounts.add(toBo(entity));
		}
		
		return accounts;
	}
	
	/**
	 * CategoriesModel -> PostgreSQL Entities
	 */
	public static List<IDALCategoryEntity> toDbosPG(List<CategoryModel> models) {
		
		// Create the list of entities
		List<IDALCategoryEntity> entities = new ArrayList<IDALCategoryEntity>();
		
		for(CategoryModel model : models){
			entities.add(toDboPG(model));
		}
		
		return entities;
	}
	
	/**
	 * CategoriesModel -> Derby Entities
	 */
	public static List<IDALCategoryEntity> toDbosDe(List<CategoryModel> models) {
		
		// Create the list of entities
		List<IDALCategoryEntity> entities = new ArrayList<IDALCategoryEntity>();
		
		for(CategoryModel model : models){
			entities.add(toDboDe(model));
		}
		
		return entities;
	}
}
