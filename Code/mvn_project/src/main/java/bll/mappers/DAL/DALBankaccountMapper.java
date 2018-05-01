package bll.mappers.DAL;

import bll.model.BankAccountModel;
import bll.model.ClientModel;
import dal.entities.derby.BankaccountDeEntity;
import dal.entities.pgsql.BankaccountPgEntity;
import dal.ientites.IDALBankaccountEntity;
import dal.ientites.IDALClientEntity;

import java.util.*;

/**
 * Class used to map a BankAccountModel to an IDALBankAccountEntity
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
     * Entity -> BankAccountModel
     */
    public static BankAccountModel toBo(IDALBankaccountEntity entity) {
        
        if (entity == null) {
            return null;
        }
    
        // Create the bank account model
        BankAccountModel model = new BankAccountModel(null, null, null, 0, false, 0);
    
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setBankName(entity.getNamebank());
        model.setType(entity.getTypeaccount());
        model.setAmount(entity.getAmount());
        model.setDefault(entity.isIsdefault());
        model.setVisible(entity.isIsvisible());
        model.setClientId(entity.getClientId());
    
        return model;
    }

    public static Collection<BankAccountModel> toBos(Collection<IDALBankaccountEntity> dbos) {
       return null;
    }

    public static Collection<IDALBankaccountEntity> toDbosPG(Collection<BankAccountModel> bos) {
       return null;

    }

    public static Collection<IDALClientEntity> toDbosDe(Collection<ClientModel> bos) {
        // throw new Exception("TODO");
//        Collection<IDALClientEntity> dbos = new ArrayList<IDALClientEntity>();
//        Iterator<ClientModel> it = bos.iterator();
//        while (it.hasNext()) {
//            dbos.formReturn((ClientPgEntity) toDboDe(it.next()));
//        }
        return null;
    }
}
