package bll.mappers.DAL;

import bll.model.BankAccountModel;
import bll.model.ClientModel;
import dal.entities.derby.ClientDeEntity;
import dal.entities.pgsql.BankaccountPgEntity;
import dal.entities.pgsql.ClientPgEntity;
import dal.ientites.IDALBankaccountEntity;
import dal.ientites.IDALClientEntity;
import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

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
        if (dbo == null) {
            return null;
        }
        BankAccountModel bo = new BankAccountModel();
        bo.setId(dbo.getId());
        return null;
    }

    public static Collection<BankAccountModel> toBos(Collection<IDALBankaccountEntity> dbos) {
        Collection<BankAccountModel> bos = new ArrayList<BankAccountModel>();
        Iterator<IDALBankaccountEntity> it = dbos.iterator();
        while (it.hasNext()) {
            dbos.add((IDALBankaccountEntity) toBo(it.next()));
        }
        return bos;
    }

    public static Collection<IDALBankaccountEntity> toDbosPG(Collection<BankAccountModel> bos) {
        Collection<IDALBankaccountEntity> dbos = new ArrayList<IDALBankaccountEntity>();
        Iterator<BankAccountModel> it = bos.iterator();
        while (it.hasNext()) {
            dbos.add((BankaccountPgEntity) toDboPG(it.next()));
        }
        return dbos;

    }

    public static Collection<IDALClientEntity> toDbosDe(Collection<ClientModel> bos) {
        // throw new Exception("TODO");
//        Collection<IDALClientEntity> dbos = new ArrayList<IDALClientEntity>();
//        Iterator<ClientModel> it = bos.iterator();
//        while (it.hasNext()) {
//            dbos.add((ClientPgEntity) toDboDe(it.next()));
//        }
        return null;
    }
}
