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
	 * Entity -> IOTransactionLogic
	 */
	public static IOTransactionLogic toBo(IDALIotransactionEntity entity) {
		
		if (entity == null) {
			return null;
		}
		
		// Create the transaction
		IOTransactionLogic account = new IOTransactionLogic();
		
		account.setId(entity.getId());
		account.setName(entity.getName());
		account.setDescription(entity.getDescription());
		account.setDate(entity.getDatetransaction());
		account.setAmount(entity.getAmount());
		account.setCurrency(entity.getCurrency());
		account.setIncome(entity.isIsincome());
		account.setCategoryID(entity.getCategoryId());
		account.setBankAccountID(entity.getBankaccountId());
		account.setBudgetID(entity.getBudgetId());
		
		return account;
	}
}
