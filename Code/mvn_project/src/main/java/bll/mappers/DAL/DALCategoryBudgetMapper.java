package bll.mappers.DAL;

import bll.logic.ClientLogic;
import bll.model.CategoryBudgetModel;
import dal.entities.derby.CategoriesbudgetDeEntity;
import dal.entities.pgsql.CategoriesbudgetPgEntity;
import dal.ientites.IDALCategoriesbudgetEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used to map a CategoryBudgetModel to an IDALCategoriesbudgetEntity
 *
 * @author Héléna Line Reymond
 * @version 1.0
 */
public class DALCategoryBudgetMapper {
	
	/**
	 * CategoryBudgetModel -> PostgreSQL entity
	 */
	public static IDALCategoriesbudgetEntity toDboPG(
			CategoryBudgetModel model) {
		
		if (model == null) {
			return null;
		}
		
		// Create the PostgreSQL entry
		CategoriesbudgetPgEntity pgEntity = new CategoriesbudgetPgEntity();
		
		pgEntity.setCategoryId(model.getCategoryID());
		pgEntity.setBudgetId(model.getBudgetID());
		
		return pgEntity;
	}
	
	/**
	 * CategoryBudgetModel -> Derby entity
	 */
	public static IDALCategoriesbudgetEntity toDboDe(
			CategoryBudgetModel model) {
		
		if (model == null) {
			return null;
		}
		
		// Create the Derby entry
		CategoriesbudgetDeEntity deEntity = new CategoriesbudgetDeEntity();
		
		deEntity.setCategoryId(model.getCategoryID());
		deEntity.setBudgetId(model.getBudgetID());
		
		return deEntity;
	}
	
	/**
	 * CategoryBudgetModel -> Derby / PostgreSQL entity
	 * Depending on if we are online or offline
	 */
	public static IDALCategoriesbudgetEntity toDbo(CategoryBudgetModel model) {
		
		if (ClientLogic.getInstance().isOnline()) {
			return toDboPG(model);
		} else {
			return toDboDe(model);
		}
	}
	
	/**
	 * Entity -> CategoryBudgetModel
	 */
	public static CategoryBudgetModel toBo(IDALCategoriesbudgetEntity entity) {
		
		if (entity == null) {
			return null;
		}
		
		// Create the entry
		CategoryBudgetModel object = new CategoryBudgetModel();
		
		object.setCategoryID(entity.getCategoryId());
		object.setBudgetID(entity.getBudgetId());
		
		return object;
	}
	
	/**
	 * Entities -> CategoriesBudgetModel
	 */
	public static List<CategoryBudgetModel> toBos(
			List<IDALCategoriesbudgetEntity> entities) {
		
		// Create the list of entries
		List<CategoryBudgetModel> objects
				= new ArrayList<CategoryBudgetModel>();
		
		for (IDALCategoriesbudgetEntity entity : entities) {
			objects.add(toBo(entity));
		}
		
		return objects;
	}
	
	/**
	 * CategoriesBudgetModel -> PostgreSQL Entities
	 */
	public static List<IDALCategoriesbudgetEntity> toDbosPG(
			List<CategoryBudgetModel> models) {
		
		// Create the list of entities
		List<IDALCategoriesbudgetEntity> entities
				= new ArrayList<IDALCategoriesbudgetEntity>();
		
		for (CategoryBudgetModel model : models) {
			entities.add(toDboPG(model));
		}
		
		return entities;
	}
}
