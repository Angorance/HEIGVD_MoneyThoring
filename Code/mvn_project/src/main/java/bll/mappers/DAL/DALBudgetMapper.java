package bll.mappers.DAL;

import dal.entities.derby.BudgetDeEntity;
import dal.entities.pgsql.BudgetPgEntity;
import dal.ientites.IDALBudgetEntity;

import java.util.*;

/**
 * Class used to map a BudgetModel to an IDALBudgetEntity
 *
 * @author Héléna Line Reymond
 * @version 1.0
 */
public class DALBudgetMapper {
	
	/**
	 * BudgetModel -> PostgreSQL entity
	 *//*
	public static IDALBudgetEntity toDboPG(BudgetModel model) {
		
		if (model == null) {
			return null;
		}
		
		// Create the PostgreSQL budget
		BudgetPgEntity pgEntity = new BudgetPgEntity();
		
		//TODO - complete
		*//*pgEntity.setId();
		pgEntity.setName();
		pgEntity.setAmount();
		pgEntity.setIsshared();
		pgEntity.setIsrecurrent();
		pgEntity.setStartingdate();
		pgEntity.setEndingdate();
		pgEntity.setGap();
		pgEntity.setClientId();*//*
		
		return pgEntity;
	}
	
	*//**
	 * BudgetModel -> Derby entity
	 *//*
	public static IDALBudgetEntity toDboDe(BudgetModel model) {
		
		if (model == null) {
			return null;
		}
		
		// Create the Derby budget
		BudgetDeEntity derbyEntity = new BudgetDeEntity();
		
		//TODO - complete
		*//*derbyEntity.setId();
		derbyEntity.setName();
		derbyEntity.setAmount();
		derbyEntity.setIsshared();
		derbyEntity.setIsrecurrent();
		derbyEntity.setStartingdate();
		derbyEntity.setEndingdate();
		derbyEntity.setGap();
		derbyEntity.setClientId();*//*
		
		return derbyEntity;
	}
	
	*//**
	 * Entity -> BudgetLogic
	 *//*
	public static BudgetLogic toBo(IDALBudgetEntity entity) {
		
		if (entity == null) {
			return null;
		}
		
		// Create the budget
		BudgetLogic object = new BudgetLogic();
		
		//TODO - complete
		*//*object.setId();
		object.setName();
		object.setAmount();
		object.setIsShared();
		object.setIsRecurrent();
		object.setStartingDate();
		object.setEndingDate();
		object.setGap();
		object.setClientId();*//*
		
		return object;
	}
	
	*//**
	 * Entities -> BudgetsLogic
	 *//*
	public static List<BudgetLogic> toBos(List<IDALBudgetEntity> entities) {
		
		// Create the list of budgets
		List<BudgetLogic> objects = new ArrayList<BudgetLogic>();
		
		for(IDALBudgetEntity entity : entities){
			objects.add(toBo(entity));
		}
		
		return objects;
	}
	
	*//**
	 * BudgetsModel -> PostgreSQL Entities
	 *//*
	public static List<IDALBudgetEntity> toDbosPG(List<BudgetModel> models) {
		
		// Create the list of entities
		List<IDALBudgetEntity> entities = new ArrayList<IDALBudgetEntity>();
		
		for(BudgetModel model : models){
			entities.add(toDboPG(model));
		}
		
		return entities;
	}
	
	*//**
	 * BudgetsModel -> Derby Entities
	 *//*
	public static List<IDALBudgetEntity> toDbosDe(List<BudgetModel> models) {
		
		// Create the list of entities
		List<IDALBudgetEntity> entities = new ArrayList<IDALBudgetEntity>();
		
		for(BudgetModel model : models){
			entities.add(toDboDe(model));
		}
		
		return entities;
	}*/
}
