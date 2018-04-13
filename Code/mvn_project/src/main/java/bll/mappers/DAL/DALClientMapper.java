package bll.mappers.DAL;

import bll.logic.ClientLogic;
import bll.model.ClientModel;
import dal.entities.pgsql.ClientPGEntity;
import dal.ientites.IDALClientEntity;

public class DALClientMapper {
    public  static IDALClientEntity toDboPG(ClientLogic bo){
        ClientPGEntity dboPg = new ClientPGEntity()
        dboPg.setId(bo.getId());
        dboPg.setUsername(bo.getUsername());
        dboPg.setEmail(bo.getEmail());
        dboPg.setPassword("");
        dboPg.setIsactivated(true);
        dboPg.setActivationkey("");
        dboPg.setSalt("");

    }
    public  static IDALClientEntity toDboDe(){return null;}
    public  static ClientModel toBo(IDALClientEntity client){
       // ClientModel bo = new ClientModel();
        return null;}
}


