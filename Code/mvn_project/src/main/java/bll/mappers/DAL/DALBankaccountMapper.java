package bll.mappers.DAL;

import bll.model.BankAccountModel;
import bll.model.ClientModel;
import dal.entities.pgsql.BankaccountPgEntity;
import dal.ientites.IDALBankaccountEntity;
import dal.ientites.IDALClientEntity;

import java.util.*;

/**
 * Class used to map a BankAccountModel to an IDALClientEntity
 */
public class DALBankaccountMapper {

    public static IDALBankaccountEntity toDboPG(BankAccountModel bo) {
        if (bo == null) {
            return null;
        }
        BankaccountPgEntity dboPg = new BankaccountPgEntity();
        dboPg.setId(bo.getId());

        return null;
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
