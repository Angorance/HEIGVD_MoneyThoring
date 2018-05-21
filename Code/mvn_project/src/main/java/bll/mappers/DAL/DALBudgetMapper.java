package bll.mappers.DAL;

import bll.logic.BudgetLogic;
import bll.logic.ClientLogic;
import bll.model.BudgetModel;
import dal.entities.derby.BudgetDeEntity;
import dal.entities.pgsql.BudgetPgEntity;
import dal.ientites.IDALBudgetEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used to map a BudgetModel to an IDALBudgetEntity
 *
 * @author Héléna Line Reymond
 * @version 1.0
 */
public class DALBudgetMapper {
	
	/**
	 * BudgetModel -> PostgreSQL entity
	 */
	public static IDALBudgetEntity toDboPG(BudgetModel model) {
		
		if (model == null) {
			return null;
		}
		
		// Create the PostgreSQL budget
		BudgetPgEntity pgEntity = new BudgetPgEntity();
		
		pgEntity.setId(model.getId());
		pgEntity.setName(model.getName());
		pgEntity.setAmount(model.getAmount());
		pgEntity.setIsshared(model.isShared());
		pgEntity.setIsrecurrent(model.isRecurrent());
		pgEntity.setStartingdate(model.getStartingDate());
		pgEntity.setEndingdate(model.getEndingDate());
		pgEntity.setGap(model.getGap());
		pgEntity.setClientId(model.getClientID());
		
		return pgEntity;
	}
	
	/**
	 * BudgetModel -> Derby entity
	 */
	public static IDALBudgetEntity toDboDe(BudgetModel model) {
		
		if (model == null) {
			return null;
		}
		
		// Create the Derby budget
		BudgetDeEntity derbyEntity = new BudgetDeEntity();
		
		derbyEntity.setId(model.getId());
		derbyEntity.setName(model.getName());
		derbyEntity.setAmount(model.getAmount());
		derbyEntity.setIsshared(model.isShared());
		derbyEntity.setIsrecurrent(model.isRecurrent());
		derbyEntity.setStartingdate(model.getStartingDate());
		derbyEntity.setEndingdate(model.getEndingDate());
		derbyEntity.setGap(model.getGap());
		derbyEntity.setClientId(model.getClientID());
		
		return derbyEntity;
	}
	
	/**
	 * TODO
	 *
	 * @param model
	 *
	 * @return
	 */
	public static IDALBudgetEntity toDbo(BudgetModel model) {
		
		if (ClientLogic.getInstance().isOnline()) {
			return toDboPG(model);
		} else {
			return toDboDe(model);
		}
	}
	
	/**
	 * Entity -> BudgetLogic
	 */
	public static BudgetLogic toBo(IDALBudgetEntity entity) {
		
		if (entity == null) {
			return null;
		}
		
		// Create the budget
		BudgetLogic object = new BudgetLogic();
		
		object.setId(entity.getId());
		object.setName(entity.getName());
		object.setAmount(entity.getAmount());
		object.setShared(entity.isIsshared());
		object.setRecurrent(entity.isIsrecurrent());
		object.setStartingDate(entity.getStartingdate());
		object.setEndingDate(entity.getEndingdate());
		object.setGap(entity.getGap());
		object.setClientID(entity.getClientId());
		
		return object;
	}
	
	/**
	 * Entities -> BudgetsLogic
	 */
	public static List<BudgetLogic> toBos(List<IDALBudgetEntity> entities) {
		
		// Create the list of budgets
		List<BudgetLogic> objects = new ArrayList<BudgetLogic>();
		
		for (IDALBudgetEntity entity : entities) {
			objects.add(toBo(entity));
		}
		
		return objects;
	}
	
	/**
	 * TODO
	 *
	 * @param models
	 *
	 * @return
	 */
	public static List<IDALBudgetEntity> toDbos(List<BudgetModel> models) {
		
		// Create the list of entities
		List<IDALBudgetEntity> entities = new ArrayList<>();
		
		for (BudgetModel model : models) {
			entities.add(toDbo(model));
		}
		
		return entities;
	}
}
