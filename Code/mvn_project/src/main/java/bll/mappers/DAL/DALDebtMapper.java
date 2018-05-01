package bll.mappers.DAL;

import bll.logic.DebtLogic;
import bll.model.DebtModel;
import dal.entities.derby.DebtDeEntity;
import dal.entities.pgsql.DebtPgEntity;
import dal.ientites.IDALDebtEntity;

import java.util.*;

/**
 * Class used to map a DebtModel to an IDALDebtEntity
 *
 * @author Héléna Line Reymond
 * @version 1.0
 */
public class DALDebtMapper {
	
	/**
	 * DebtModel -> PostgreSQL entity
	 */
	public static IDALDebtEntity toDboPG(DebtModel model) {
		
		if (model == null) {
			return null;
		}
		
		// Create the PostgreSQL debt
		DebtPgEntity pgEntity = new DebtPgEntity();
		
		//TODO - fix
		pgEntity.setId(model.getId());
		pgEntity.setName(model.getName());
		pgEntity.setDescription(model.getDescription());
		pgEntity.setAmount(model.getAmount());
		pgEntity.setIsincome(model.isIncome());
		//pgEntity.setExpirationdate(model.getExpirationDate());
		pgEntity.setClientId(model.getCreatorID());             //TODO - INT?
		pgEntity.setClientId1(model.getContributorID());        //TODO - INTEGER??
		
		return pgEntity;
	}
	
	/**
	 * DebtModel -> Derby entity
	 */
	public static IDALDebtEntity toDboDe(DebtModel model) {
		
		if (model == null) {
			return null;
		}
		
		// Create the Derby debt
		DebtDeEntity derbyEntity = new DebtDeEntity();
		
		//TODO - fix
		derbyEntity.setId(model.getId());
		derbyEntity.setName(model.getName());
		derbyEntity.setDescription(model.getDescription());
		derbyEntity.setAmount(model.getAmount());
		derbyEntity.setIsincome(model.isIncome());
		//derbyEntity.setExpirationdate(model.getExpirationDate());
		derbyEntity.setClientId(model.getCreatorID());             //TODO - INT?
		derbyEntity.setClientId1(model.getContributorID());        //TODO - INTEGER??
		
		return derbyEntity;
	}
	
	/**
	 * Entity -> DebtLogic
	 */
	public static DebtLogic toBo(IDALDebtEntity entity) {
		
		if (entity == null) {
			return null;
		}
		
		// Create the debt
		DebtLogic object = new DebtLogic();
		
		object.setId(entity.getId());
		object.setName(entity.getName());
		object.setDescription(entity.getDescription());
		object.setAmount(entity.getAmount());
		object.setIncome(entity.isIsincome());
		object.setExpirationDate(entity.getExpirationdate());
		object.setCreatorID(entity.getClientId());
		object.setContributorID(entity.getClientId1());
		
		return object;
	}
	
	/**
	 * Entities -> DebtsLogic
	 */
	public static List<DebtLogic> toBos(List<IDALDebtEntity> entities) {
		
		// Create the list of debts
		List<DebtLogic> objects = new ArrayList<DebtLogic>();
		
		for(IDALDebtEntity entity : entities){
			objects.add(toBo(entity));
		}
		
		return objects;
	}
	
	/**
	 * DebtsModel -> PostgreSQL Entities
	 */
	public static List<IDALDebtEntity> toDbosPG(List<DebtModel> models) {
		
		// Create the list of entities
		List<IDALDebtEntity> entities = new ArrayList<IDALDebtEntity>();
		
		for(DebtModel model : models){
			entities.add(toDboPG(model));
		}
		
		return entities;
	}
	
	/**
	 * DebtsModel -> Derby Entities
	 */
	public static List<IDALDebtEntity> toDbosDe(List<DebtModel> models) {
		
		// Create the list of entities
		List<IDALDebtEntity> entities = new ArrayList<IDALDebtEntity>();
		
		for(DebtModel model : models){
			entities.add(toDboDe(model));
		}
		
		return entities;
	}
}
