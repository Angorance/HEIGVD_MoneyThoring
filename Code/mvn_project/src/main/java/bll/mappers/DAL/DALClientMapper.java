package bll.mappers.DAL;

import bll.logic.ClientLogic;
import bll.model.ClientModel;
import dal.entities.derby.ClientDeEntity;
import dal.entities.pgsql.ClientPgEntity;
import dal.ientites.IDALClientEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used to map a ClientModel to an IDALClientEntity
 *
 * @author Héléna Line Reymond
 * @version 2.0
 */
public class DALClientMapper {
	
	/**
	 * ClientModel -> PostgreSQL entity
	 */
	private static IDALClientEntity toDboPG(ClientModel model) {
		
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
	private static IDALClientEntity toDboDe(ClientModel model) {
		
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
	 * TODO
	 *
	 * @param model
	 *
	 * @return
	 */
	public static IDALClientEntity toDbo(ClientModel model) {
		
		if (ClientLogic.getInstance().isOnline()) {
			return toDboPG(model);
		} else {
			return toDboDe(model);
		}
	}
	
	/**
	 * Entity -> ClientLogic
	 */
	public static void toBo(IDALClientEntity entity) {
		
		if (entity != null) {
			
			// Create the client
			ClientLogic object = ClientLogic.getInstance();
			
			object.setId(entity.getId());
			object.setUsername(entity.getUsername());
			object.setEmail(entity.getEmail());
			object.setPassword(entity.getPassword());
			object.setActivated(entity.getIsactivated());
			object.setKey(entity.getActivationkey());
			object.setSalt(entity.getSalt());
		}
	}
	
	/**
	 * Entity -> ClientModel
	 */
	public static ClientModel toClientModel(IDALClientEntity entity) {
		
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
		
		for (IDALClientEntity entity : entities) {
			models.add(toClientModel(entity));
		}
		
		return models;
	}
	
	/**
	 * TODO
	 *
	 * @param models
	 *
	 * @return
	 */
	public static List<IDALClientEntity> toDbos(List<ClientModel> models) {
		
		// Create the list of entities
		List<IDALClientEntity> entities = new ArrayList<>();
		
		for (ClientModel model : models) {
			entities.add(toDbo(model));
		}
		
		return entities;
	}
}