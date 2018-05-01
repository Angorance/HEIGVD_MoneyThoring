package bll.mappers.DAL;

import bll.logic.BankAccountLogic;
import bll.model.BankAccountModel;
import dal.entities.derby.BankaccountDeEntity;
import dal.entities.pgsql.BankaccountPgEntity;
import dal.ientites.IDALBankaccountEntity;

import java.util.*;

/**
 * Class used to map a BankAccountModel to an IDALBankAccountEntity
 *
 * @author Héléna Line Reymond
 * @version 2.0
 */
public class DALBankaccountMapper {
    
    /**
     * BankAccountModel -> PostgreSQL entity
     */
    public static IDALBankaccountEntity toDboPG(BankAccountModel model) {
        
        if (model == null) {
            return null;
        }
    
        // Create the PostgreSQL bank account
        BankaccountPgEntity pgEntity = new BankaccountPgEntity();
    
        pgEntity.setId(model.getId());
        pgEntity.setName(model.getName());
        pgEntity.setNamebank(model.getBankName());
        pgEntity.setTypeaccount(model.getType());
        pgEntity.setAmount(model.getAmount());
        pgEntity.setIsdefault(model.isDefault());
        pgEntity.setIsvisible(model.isVisible());
        pgEntity.setClientId(model.getClientId());
        
        return pgEntity;
    }
    
    /**
     * BankAccountModel -> Derby entity
     */
    public static IDALBankaccountEntity toDboDe(BankAccountModel model) {
        
        if (model == null) {
            return null;
        }
    
        // Create the Derby bank account
        BankaccountDeEntity derbyEntity = new BankaccountDeEntity();
    
        derbyEntity.setId(model.getId());
        derbyEntity.setName(model.getName());
        derbyEntity.setNamebank(model.getBankName());
        derbyEntity.setTypeaccount(model.getType());
        derbyEntity.setAmount(model.getAmount());
        derbyEntity.setIsdefault(model.isDefault());
        derbyEntity.setIsvisible(model.isVisible());
        derbyEntity.setClientId(model.getClientId());
    
        return derbyEntity;
    }
    
    /**
     * Entity -> BankAccountLogic
     */
    public static BankAccountLogic toBo(IDALBankaccountEntity entity) {
        
        if (entity == null) {
            return null;
        }
    
        // Create the bank account
        BankAccountLogic account = new BankAccountLogic();
    
        account.setId(entity.getId());
        account.setName(entity.getName());
        account.setBankName(entity.getNamebank());
        account.setType(entity.getTypeaccount());
        account.setAmount(entity.getAmount());
        account.setDefault(entity.isIsdefault());
        account.setVisible(entity.isIsvisible());
        account.setClientId(entity.getClientId());
    
        return account;
    }
    
    /**
     * Entities -> BankAccountsLogic
     */
    public static List<BankAccountLogic> toBos(List<IDALBankaccountEntity> entities) {
        
        // Create the list of bank accounts
        List<BankAccountLogic> accounts = new ArrayList<BankAccountLogic>();
    
        for(IDALBankaccountEntity entity : entities){
            accounts.add(toBo(entity));
        }
    
        return accounts;
    }
    
    /**
     * BankAccountsModel -> PostgreSQL Entities
     */
    public static List<IDALBankaccountEntity> toDbosPG(List<BankAccountModel> models) {
        
        // Create the list of entities
        List<IDALBankaccountEntity> entities = new ArrayList<IDALBankaccountEntity>();
    
        for(BankAccountModel model : models){
            entities.add(toDboPG(model));
        }
    
        return entities;
    }
    
    /**
     * BankAccountsModel -> Derby Entities
     */
    public static List<IDALBankaccountEntity> toDbosDe(List<BankAccountModel> models) {
    
        // Create the list of entities
        List<IDALBankaccountEntity> entities = new ArrayList<IDALBankaccountEntity>();
    
        for(BankAccountModel model : models){
            entities.add(toDboDe(model));
        }
    
        return entities;
    }
}
