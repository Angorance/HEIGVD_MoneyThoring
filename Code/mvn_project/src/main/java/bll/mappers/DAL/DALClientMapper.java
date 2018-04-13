package bll.mappers.DAL;

import bll.model.ClientModel;
import dal.entities.pgsql.ClientPgEntity;
import dal.ientites.IDALClientEntity;

public class DALClientMapper {
    public static IDALClientEntity toDboPG(ClientModel bo) {
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

//    public static IDALClientEntity toDboDe(ClientModel bo) {
//        ClientDeEntity dboDe = new ClientDeEntity();
//        dboDe.setId(bo.getId());
//        dboDe.setUsername(bo.getUsername());
//        dboDe.setEmail(bo.getEmail());
//        dboDe.setPassword(bo.getPassword());
//        dboDe.setIsactivated(bo.getIsActivated());
//        dboDe.setActivationkey(bo.getKey());
//        dboDe.setSalt(bo.getSalt());
//        return dboDe;
//    }

    public static ClientModel toBo(IDALClientEntity dbo) {
        // ClientModel bo = new ClientModel();
        ClientModel bo = new ClientModel();
        bo.setId(dbo.getId());
        bo.setUsername(dbo.getUsername());
        bo.setEmail(dbo.getEmail());
        bo.setPassword(dbo.getPassword());
        bo.setIsActivated(dbo.isIsactivated());
        bo.setKey(dbo.getActivationkey());
        bo.setSalt(dbo.getSalt());
        return bo;
    }
}


