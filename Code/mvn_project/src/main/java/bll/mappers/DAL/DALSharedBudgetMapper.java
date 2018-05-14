package bll.mappers.DAL;

import bll.model.SharedBudgetModel;
import dal.entities.pgsql.SharedbudgetPgEntity;
import dal.ientites.IDALSharedbudgetEntity;

import java.util.*;

/**
 * Class used to map a SharedBudgetModel to an IDALSharedbudgetEntity
 *
 * @author Héléna Line Reymond
 * @version 1.0
 */
public class DALSharedBudgetMapper {
	
	/**
	 * SharedBudgetModel -> PostgreSQL entity
	 */
	public static IDALSharedbudgetEntity toDboPG(SharedBudgetModel model) {
		
		if (model == null) {
			return null;
		}
		
		// Create the PostgreSQL entry
		SharedbudgetPgEntity pgEntity = new SharedbudgetPgEntity();
		
		pgEntity.setClientId(model.getClientID());
		pgEntity.setBudgetId(model.getBudgetID());
		
		return pgEntity;
	}
	
	/**
	 * Entity -> SharedBudgetModel
	 */
	public static SharedBudgetModel toBo(IDALSharedbudgetEntity entity) {
		
		if (entity == null) {
			return null;
		}
		
		// Create the entry
		SharedBudgetModel object = new SharedBudgetModel();
		
		object.setClientID(entity.getClientId());
		object.setBudgetID(entity.getBudgetId());
		
		return object;
	}
	
	/**
	 * Entities -> SharedBudgetsModel
	 */
	public static List<SharedBudgetModel> toBos(List<IDALSharedbudgetEntity> entities) {
		
		// Create the list of entries
		List<SharedBudgetModel> objects = new ArrayList<SharedBudgetModel>();
		
		for(IDALSharedbudgetEntity entity : entities){
			objects.add(toBo(entity));
		}
		
		return objects;
	}
	
	/**
	 * SharedBudgetsModel -> PostgreSQL Entities
	 */
	public static List<IDALSharedbudgetEntity> toDbosPG(List<SharedBudgetModel> models) {
		
		// Create the list of entities
		List<IDALSharedbudgetEntity> entities = new ArrayList<IDALSharedbudgetEntity>();
		
		for(SharedBudgetModel model : models){
			entities.add(toDboPG(model));
		}
		
		return entities;
	}
}
