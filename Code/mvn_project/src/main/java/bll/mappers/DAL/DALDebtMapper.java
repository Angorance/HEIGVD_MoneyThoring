package bll.mappers.DAL;

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
	 *//*
	public static IDALDebtEntity toDboPG(DebtModel model) {
		
		if (model == null) {
			return null;
		}
		
		// Create the PostgreSQL debt
		DebtPgEntity pgEntity = new DebtPgEntity();
		
		//TODO - complete
		*//*pgEntity.setId();
		pgEntity.setName();
		pgEntity.setDescription();
		pgEntity.setAmount();
		pgEntity.setIsincome();
		pgEntity.setExpirationdate();
		pgEntity.setClientId();             //TODO - INT?
		pgEntity.setClientId1();*//*          //TODO - INTEGER??
		
		return pgEntity;
	}
	
	*//**
	 * DebtModel -> Derby entity
	 *//*
	public static IDALDebtEntity toDboDe(DebtModel model) {
		
		if (model == null) {
			return null;
		}
		
		// Create the Derby debt
		DebtDeEntity derbyEntity = new DebtDeEntity();
		
		//TODO - complete
		*//*derbyEntity.setId();
		derbyEntity.setName();
		derbyEntity.setDescription();
		derbyEntity.setAmount();
		derbyEntity.setIsincome();
		derbyEntity.setExpirationdate();
		derbyEntity.setClientId();             //TODO - INT?
		derbyEntity.setClientId1();*//*          //TODO - INTEGER??
		
		return derbyEntity;
	}
	
	*//**
	 * Entity -> DebtLogic
	 *//*
	public static DebtLogic toBo(IDALDebtEntity entity) {
		
		if (entity == null) {
			return null;
		}
		
		// Create the debt
		DebtLogic object = new DebtLogic();
		
		//TODO - complete
		*//*object.setId();
		object.setName();
		object.setDescription();
		object.setAmount();
		object.setIsincome();
		object.setExpirationDate();
		object.setCreator();
		object.setContributor();*//*
		
		return object;
	}
	
	*//**
	 * Entities -> DebtsLogic
	 *//*
	public static List<DebtLogic> toBos(List<IDALDebtEntity> entities) {
		
		// Create the list of debts
		List<DebtLogic> objects = new ArrayList<DebtLogic>();
		
		for(IDALDebtEntity entity : entities){
			objects.add(toBo(entity));
		}
		
		return objects;
	}
	
	*//**
	 * DebtsModel -> PostgreSQL Entities
	 *//*
	public static List<IDALDebtEntity> toDbosPG(List<DebtModel> models) {
		
		// Create the list of entities
		List<IDALDebtEntity> entities = new ArrayList<IDALDebtEntity>();
		
		for(DebtModel model : models){
			entities.add(toDboPG(model));
		}
		
		return entities;
	}
	
	*//**
	 * DebtsModel -> Derby Entities
	 *//*
	public static List<IDALDebtEntity> toDbosDe(List<DebtModel> models) {
		
		// Create the list of entities
		List<IDALDebtEntity> entities = new ArrayList<IDALDebtEntity>();
		
		for(DebtModel model : models){
			entities.add(toDboDe(model));
		}
		
		return entities;
	}*/
}
