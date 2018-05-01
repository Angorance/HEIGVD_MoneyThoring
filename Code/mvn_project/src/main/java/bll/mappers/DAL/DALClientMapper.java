package bll.mappers.DAL;

import bll.logic.ClientLogic;
import bll.model.ClientModel;
import dal.entities.derby.ClientDeEntity;
import dal.entities.pgsql.ClientPgEntity;
import dal.ientites.IDALClientEntity;

import java.util.*;

/**
 * Class used to map a ClientModel to an IDALClientEntity
 */
public class DALClientMapper {
	
	/**
	 * ClientModel -> PostgreSQL entity
	 */
    public static IDALClientEntity toDboPG(ClientModel model) {
    	
        if (model == null) {
            return null;
        }
        
        // Create the PostgreSQL client
        ClientPgEntity pgEntity = new ClientPgEntity();
        
        pgEntity.setId(model.getId());
        pgEntity.setUsername(model.getUsername());
        pgEntity.setEmail(model.getEmail());
        pgEntity.setPassword(model.getPassword());
        pgEntity.setIsactivated(model.getIsActivated());
        pgEntity.setActivationkey(model.getKey());
        pgEntity.setSalt(model.getSalt());
 
        return pgEntity;
    }
	
	/**
	 * ClientModel -> Derby entity
	 */
    public static IDALClientEntity toDboDe(ClientModel model) {
     
    	if (model == null) {
            return null;
        }
	
	    // Create the Derby client
        ClientDeEntity derbyEntity = new ClientDeEntity();
    	
        derbyEntity.setId(model.getId());
        derbyEntity.setUsername(model.getUsername());
        derbyEntity.setEmail(model.getEmail());
        derbyEntity.setPassword(model.getPassword());
        derbyEntity.setIsactivated(model.getIsActivated());
        derbyEntity.setActivationkey(model.getKey());
        derbyEntity.setSalt(model.getSalt());
        
        return derbyEntity;
    }
	
	/**
	 * Entity -> ClientLogic
	 */
	public static void toBo(IDALClientEntity entity) {
		
		if (entity != null) {
			
			// Create the client
			ClientLogic client = ClientLogic.getInstance();
			
			client.setId(entity.getId());
			client.setUsername(entity.getUsername());
			client.setEmail(entity.getEmail());
			client.setPassword(entity.getPassword());
			client.setActivated(entity.getIsactivated());
			client.setKey(entity.getActivationkey());
			client.setSalt(entity.getSalt());
		}
	}
	
	/**
	 * Entity -> ClientModel
	 */
    private static ClientModel toClientModel(IDALClientEntity entity) {
    	
        if (entity == null) {
            return null;
        }
        
        // Create the client model
        ClientModel model = new ClientModel();
        
        model.setId(entity.getId());
        model.setUsername(entity.getUsername());
        model.setEmail(entity.getEmail());
        model.setPassword(entity.getPassword());
        model.setActivated(entity.getIsactivated());
        model.setKey(entity.getActivationkey());
        model.setSalt(entity.getSalt());
        
        return model;
    }
	
	/**
	 * Entities -> ClientsModel
	 */
    public static List<ClientModel> toBos(List<IDALClientEntity> entities) {
	
	    // Create the list of clients model
        List<ClientModel> models = new ArrayList<ClientModel>();
	
	    for(IDALClientEntity entity : entities){
		    models.add(toClientModel(entity));
	    }
        
        return models;
    }
	
	/**
	 * ClientsModel -> PostgreSQL Entities
	 */
    public static List<IDALClientEntity> toDbosPG(List<ClientModel> models) {
	
	    // Create the list of entities
	    List<IDALClientEntity> entities = new ArrayList<IDALClientEntity>();
	
	    for(ClientModel model : models){
		    entities.add(toDboPG(model));
	    }
	
	    return entities;
    }
	
	/**
	 * ClientsModel -> Derby Entities
	 */
    public static List<IDALClientEntity> toDbosDe(List<ClientModel> models) {
	
	    // Create the list of entities
	    List<IDALClientEntity> entities = new ArrayList<IDALClientEntity>();
	
	    for(ClientModel model : models){
		    entities.add(toDboDe(model));
	    }
	
	    return entities;
    }
}