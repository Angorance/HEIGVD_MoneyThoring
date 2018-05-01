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
        BankAccountLogic object = new BankAccountLogic();
    
        object.setId(entity.getId());
        object.setName(entity.getName());
        object.setBankName(entity.getNamebank());
        object.setType(entity.getTypeaccount());
        object.setAmount(entity.getAmount());
        object.setDefault(entity.isIsdefault());
        object.setVisible(entity.isIsvisible());
        object.setClientId(entity.getClientId());
    
        return object;
    }
    
    /**
     * Entities -> BankAccountsLogic
     */
    public static List<BankAccountLogic> toBos(List<IDALBankaccountEntity> entities) {
        
        // Create the list of bank accounts
        List<BankAccountLogic> objects = new ArrayList<BankAccountLogic>();
    
        for(IDALBankaccountEntity entity : entities){
            objects.add(toBo(entity));
        }
    
        return objects;
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
