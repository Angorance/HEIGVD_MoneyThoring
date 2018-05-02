package bll.mappers.DAL;

import bll.logic.RecurrenceLogic;
import bll.model.RecurrenceModel;
import dal.entities.derby.RecurrenceDeEntity;
import dal.entities.pgsql.RecurrencePgEntity;
import dal.ientites.IDALRecurrenceEntity;

import java.util.*;

/**
 * Class used to map a RecurrenceModel to an IDALRecurrenceEntity
 *
 * @author Héléna Line Reymond
 * @version 1.0
 */
public class DALRecurrenceMapper {
	
	/**
	 * RecurrenceModel -> PostgreSQL entity
	 */
	public static IDALRecurrenceEntity toDboPG(RecurrenceModel model) {
		
		if (model == null) {
			return null;
		}
		
		// Create the PostgreSQL recurrence
		RecurrencePgEntity pgEntity = new RecurrencePgEntity();
		
		//TODO - fix
		pgEntity.setId(model.getId());
		pgEntity.setGap(model.getGap());
		//pgEntity.setNextdate(model.getNextDate());
		pgEntity.setIotransactionId(model.getTransactionID());
		
		return pgEntity;
	}
	
	/**
	 * RecurrenceModel -> Derby entity
	 */
	public static IDALRecurrenceEntity toDboDe(RecurrenceModel model) {
		
		if (model == null) {
			return null;
		}
		
		// Create the Derby recurrence
		RecurrenceDeEntity derbyEntity = new RecurrenceDeEntity();
		
		//TODO - fix
		derbyEntity.setId(model.getId());
		derbyEntity.setGap(model.getGap());
		//derbyEntity.setNextdate(model.getNextDate());
		derbyEntity.setIotransactionId(model.getTransactionID());
		
		return derbyEntity;
	}
	
	/**
	 * Entity -> RecurrenceLogic
	 */
	public static RecurrenceLogic toBo(IDALRecurrenceEntity entity) {
		
		if (entity == null) {
			return null;
		}
		
		// Create the recurrence
		RecurrenceLogic object = new RecurrenceLogic();
		
		//TODO - fix
		object.setId(entity.getId());
		object.setGap(entity.getGap());
		//object.setNextDate(entity.getNextdate());
		object.setTransactionID(entity.getIotransactionId());
		
		return object;
	}
	
	/**
	 * Entities -> RecurrencesLogic
	 */
	public static List<RecurrenceLogic> toBos(List<IDALRecurrenceEntity> entities) {
		
		// Create the list of recurrences
		List<RecurrenceLogic> objects = new ArrayList<RecurrenceLogic>();
		
		for(IDALRecurrenceEntity entity : entities){
			objects.add(toBo(entity));
		}
		
		return objects;
	}
	
	/**
	 * RecurrencesLogic -> PostgreSQL Entities
	 */
	public static List<IDALRecurrenceEntity> toDbosPG(List<RecurrenceModel> models) {
		
		// Create the list of entities
		List<IDALRecurrenceEntity> entities = new ArrayList<IDALRecurrenceEntity>();
		
		for(RecurrenceModel model : models){
			entities.add(toDboPG(model));
		}
		
		return entities;
	}
	
	/**
	 * RecurrencesLogic -> Derby Entities
	 */
	public static List<IDALRecurrenceEntity> toDbosDe(List<RecurrenceModel> models) {
		
		// Create the list of entities
		List<IDALRecurrenceEntity> entities = new ArrayList<IDALRecurrenceEntity>();
		
		for(RecurrenceModel model : models){
			entities.add(toDboDe(model));
		}
		
		return entities;
	}
}
