package bll.mappers.DAL;

import bll.logic.BankAccountLogic;
import bll.logic.CategoryLogic;
import bll.logic.ClientLogic;
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
		
		pgEntity.setId(model.getId());
		pgEntity.setName(model.getName());
		pgEntity.setDescription(model.getDescription());
		pgEntity.setDatetransaction(model.getDate());
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
		
		derbyEntity.setId(model.getId());
		derbyEntity.setName(model.getName());
		derbyEntity.setDescription(model.getDescription());
		derbyEntity.setDatetransaction(model.getDate());
		derbyEntity.setAmount(model.getAmount());
		derbyEntity.setCurrency(model.getCurrency());
		derbyEntity.setIsincome(model.isIncome());
		derbyEntity.setCategoryId(model.getCategoryID());
		derbyEntity.setBankaccountId(model.getBankAccountID());
		derbyEntity.setBudgetId(model.getBudgetID());
		
		return derbyEntity;
	}
	
	
	/**
	 * TODO
	 *
	 * @param model
	 *
	 * @return
	 */
	public static IDALIotransactionEntity toDbo(IOTransactionModel model) {
		
		if (ClientLogic.getInstance().isOnline()) {
			return toDboPG(model);
		} else {
			return toDboDe(model);
		}
	}
	
	/**
	 * CategoriesModel -> Derby Entities
	 */
	public static List<IDALIotransactionEntity> toDbos(
			List<IOTransactionModel> models) {
		
		// Create the list of entities
		List<IDALIotransactionEntity> entities = new ArrayList<>();
		
		for (IOTransactionModel model : models) {
			entities.add(toDbo(model));
		}
		
		return entities;
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
		
		object.setId(entity.getId());
		object.setName(entity.getName());
		object.setDescription(entity.getDescription());
		object.setDate(entity.getDatetransaction());
		object.setAmount(entity.getAmount());
		object.setCurrency(entity.getCurrency());
		object.setIncome(entity.isIsincome());
		object.setCategory(CategoryLogic.getByID(entity.getCategoryId()));
		object.setBankAccount(
				BankAccountLogic.getBankAccountByID(entity.getBankaccountId()));
		object.setBudgetID(entity.getBudgetId());
		
		return object;
	}
	
	/**
	 * Entities -> IOTransactionsLogic
	 */
	public static List<IOTransactionLogic> toBos(
			List<IDALIotransactionEntity> entities) {
		
		// Create the list of transactions
		List<IOTransactionLogic> objects = new ArrayList<>();
		
		for (IDALIotransactionEntity entity : entities) {
			objects.add(toBo(entity));
		}
		
		return objects;
	}
}
