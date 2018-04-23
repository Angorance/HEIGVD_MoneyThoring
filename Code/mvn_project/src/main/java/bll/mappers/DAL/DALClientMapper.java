package bll.mappers.DAL;

import bll.model.ClientModel;
import dal.entities.derby.ClientDeEntity;
import dal.entities.pgsql.ClientPgEntity;
import dal.ientites.IDALClientEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class DALClientMapper {
    public static IDALClientEntity toDboPG(ClientModel bo) {
        if (bo == null) {
            return null;
        }
        ClientPgEntity dboPg = new ClientPgEntity();
        dboPg.setId(bo.getId());
        dboPg.setUsername(bo.getUsername());
        dboPg.setEmail(bo.getEmail());
        dboPg.setPassword(bo.getPassword());
        dboPg.setIsactivated(bo.getIsActivated());
        dboPg.setActivationkey(bo.getKey());
        dboPg.setSalt(bo.getSalt());
        return dboPg;
    }

    public static IDALClientEntity toDboDe(ClientModel bo) {
        if (bo == null) {
            return null;
        }
        ClientDeEntity dboDe = new ClientDeEntity();
        dboDe.setId(bo.getId());
        dboDe.setUsername(bo.getUsername());
        dboDe.setEmail(bo.getEmail());
        dboDe.setPassword(bo.getPassword());
        dboDe.setIsactivated(bo.getIsActivated());
        dboDe.setActivationkey(bo.getKey());
        dboDe.setSalt(bo.getSalt());
        return dboDe;
    }

    public static ClientModel toBo(IDALClientEntity dbo) {
        if (dbo == null) {
            return null;
        }
        ClientModel bo = new ClientModel();
        bo.setId(dbo.getId());
        bo.setUsername(dbo.getUsername());
        bo.setEmail(dbo.getEmail());
        bo.setPassword(dbo.getPassword());
        bo.setActivated(dbo.getIsactivated());
        bo.setKey(dbo.getActivationkey());
        bo.setSalt(dbo.getSalt());
        return bo;
    }

    public static Collection<ClientModel> toBos(Collection<IDALClientEntity> dbos) {
        Collection<ClientModel> bos = new ArrayList<ClientModel>();
        Iterator<IDALClientEntity> it = dbos.iterator();
        while (it.hasNext()) {
            dbos.add((IDALClientEntity) toBo(it.next()));
        }
        return bos;
    }

    public static Collection<IDALClientEntity> toDbosPG(Collection<ClientModel> bos) {

        return null;
    }

    public static Collection<IDALClientEntity> toDbosDe(Collection<ClientModel> bos) {
        return null;
    }


}


