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

    public static IDALBankaccountEntity toDboDe(BankAccountModel bo) {
//        if (bo == null) {
//            return null;
//        }
//        BankaccountDeEntity dboDe = new BankaccountDeEntity();
//        dboDe.setId(bo.getId());
        return null;
        //return dboDe;
    }

    public static BankAccountModel toBo(IDALBankaccountEntity dbo) {
      return null;
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
