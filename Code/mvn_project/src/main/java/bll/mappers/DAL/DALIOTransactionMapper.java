package bll.mappers.DAL;

import bll.logic.IOTransactionLogic;
import bll.model.IOTransactionModel;
import dal.entities.derby.IotransactionDeEntity;
import dal.entities.pgsql.IotransactionPgEntity;
import dal.ientites.IDALIotransactionEntity;

import java.util.*;

/**
 * Class used to map a IOTransactionModel to an IDALIOTransactionEntity
 *
 * @author Héléna Line Reymond
 * @version 1.0
 */
public class DALIOTransactionMapper {
	
	/**
	 * IOTransactionModel -> PostgreSQL entity
	 */
	public static IDALIotransactionEntity toDboPG(IOTransactionModel model) {
		
		if (model == null) {
			return null;
		}
		
		// Create the PostgreSQL transaction
		IotransactionPgEntity pgEntity = new IotransactionPgEntity();
		
		//TODO - fix
		pgEntity.setId(model.getId());
		pgEntity.setName(model.getName());
		pgEntity.setDescription(model.getDescription());
		//pgEntity.setDatetransaction(model.getDate());
		pgEntity.setAmount(model.getAmount());
		pgEntity.setCurrency(model.getCurrency());
		pgEntity.setIsincome(model.isIncome());
		pgEntity.setCategoryId(model.getCategoryID());
		pgEntity.setBankaccountId(model.getBankAccountID());
		pgEntity.setBudgetId(model.getBudgetID());
		
		return pgEntity;
	}
	
	/**
	 * IOTransactionModel -> Derby entity
	 */
	public static IDALIotransactionEntity toDboDe(IOTransactionModel model) {
		
		if (model == null) {
			return null;
		}
		
		// Create the Derby transaction
		IotransactionDeEntity derbyEntity = new IotransactionDeEntity();
		
		//TODO - fix
		derbyEntity.setId(model.getId());
		derbyEntity.setName(model.getName());
		derbyEntity.setDescription(model.getDescription());
		//derbyEntity.setDatetransaction(model.getDate());
		derbyEntity.setAmount(model.getAmount());
		derbyEntity.setCurrency(model.getCurrency());
		derbyEntity.setIsincome(model.isIncome());
		derbyEntity.setCategoryId(model.getCategoryID());
		derbyEntity.setBankaccountId(model.getBankAccountID());
		derbyEntity.setBudgetId(model.getBudgetID());
		
		return derbyEntity;
	}
	
	/**
	 * Entity -> IOTransactionLogic
	 */
	public static IOTransactionLogic toBo(IDALIotransactionEntity entity) {
		
		if (entity == null) {
			return null;
		}
		
		// Create the transaction
		IOTransactionLogic object = new IOTransactionLogic();
		
		//TODO - fix
		object.setId(entity.getId());
		object.setName(entity.getName());
		object.setDescription(entity.getDescription());
		//object.setDate(entity.getDatetransaction());
		object.setAmount(entity.getAmount());
		object.setCurrency(entity.getCurrency());
		object.setIncome(entity.isIsincome());
		object.setCategoryID(entity.getCategoryId());
		object.setBankAccountID(entity.getBankaccountId());
		object.setBudgetID(entity.getBudgetId());
		
		return object;
	}
	
	/**
	 * Entities -> IOTransactionsLogic
	 */
	public static List<IOTransactionLogic> toBos(List<IDALIotransactionEntity> entities) {
		
		// Create the list of transactions
		List<IOTransactionLogic> objects = new ArrayList<IOTransactionLogic>();
		
		for(IDALIotransactionEntity entity : entities){
			objects.add(toBo(entity));
		}
		
		return objects;
	}
	
	/**
	 * IOTransactionsModel -> PostgreSQL Entities
	 */
	public static List<IDALIotransactionEntity> toDbosPG(List<IOTransactionModel> models) {
		
		// Create the list of entities
		List<IDALIotransactionEntity> entities = new ArrayList<IDALIotransactionEntity>();
		
		for(IOTransactionModel model : models){
			entities.add(toDboPG(model));
		}
		
		return entities;
	}
	
	/**
	 * IOTransactionsModel -> Derby Entities
	 */
	public static List<IDALIotransactionEntity> toDbosDe(List<IOTransactionModel> models) {
		
		// Create the list of entities
		List<IDALIotransactionEntity> entities = new ArrayList<IDALIotransactionEntity>();
		
		for(IOTransactionModel model : models){
			entities.add(toDboDe(model));
		}
		
		return entities;
	}
}
