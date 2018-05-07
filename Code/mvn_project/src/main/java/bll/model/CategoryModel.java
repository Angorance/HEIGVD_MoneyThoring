package bll.model;

import bll.mappers.DAL.DALCategoryMapper;
import dal.irepositories.ICategoryRepository;
import dal.orm.IORM;
import dal.orm.PgORM;

/**
 * CategoryModel class.
 * Allows the mapping between the DAL entities and the Business Logic.
 * Only implements the constructors, the getters and the setters.
 *
 * @author Daniel Gonzalez Lopez
 * @version 1.2
 */
public class CategoryModel {
	
	private int id;
	private int clientID;
	
	private String name;
	private String color;
	
	private boolean isDefault;
	
	
	// Protected default constructor. Avoid instances creation outside package.
	protected CategoryModel() {}
	
	/**
	 * Construct an instance of ClientModel with the given parameters.
	 *
	 * @param name Name of the category.
	 * @param color Color of the category.
	 * @param isDefault Flag for the default category.
	 */
	protected CategoryModel(String name, String color, boolean isDefault) {
		
		this.name = name;
		this.color = color;
		
		setDefault(isDefault);
	}
	
	/**
	 * Create a category entry for the user into the database.
	 *
	 * @param orm ORM instance to use.
	 */
	protected void createCategory(IORM orm) {
		
		try {
			
			orm.beginTransaction();
			
			ICategoryRepository repo = orm.getCategoryRepository();
			repo.addCategory(DALCategoryMapper.toDboPG(this));
			
			orm.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Update category entry with new data.
	 *
	 * @param orm ORM instance to use.
	 */
	protected void updateCategory(IORM orm) {
		
		try {
			//PgORM or = (PgORM) orm;
			orm.beginTransaction();
			
			ICategoryRepository repo = orm.getCategoryRepository();
			repo.update(DALCategoryMapper.toDboPG(this));
			
			orm.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// -------------------------------------------------------------------------
	// GETTERS -----------------------------------------------------------------
	
	/**
	 * Get the ID of the category.
	 *
	 * @return ID of category.
	 */
	public int getId() {
		
		return id;
	}
	
	/**
	 * Get the name of the category.
	 *
	 * @return Name of the category.
	 */
	public String getName() {
		
		return name;
	}
	
	/**
	 * Get the color of the category.
	 *
	 * @return Color of category.
	 */
	public String getColor() {
		
		return color;
	}
	
	/**
	 * Get the isDefault flag of the category.
	 *
	 * @return Default flag.
	 */
	public boolean isDefault() {
		
		return isDefault;
	}
	
	/**
	 * Get the client ID for the category.
	 *
	 * @return Client ID.
	 */
	public int getClientID() {
		
		return clientID;
	}
	
	
	// -------------------------------------------------------------------------
	// SETTERS -----------------------------------------------------------------
	
	/**
	 * Set the ID of the category.
	 *
	 * @param id New ID to set.
	 */
	public void setId(int id) {
		
		this.id = id;
	}
	
	/**
	 * Set the name of the category.
	 *
	 * @param name New name to set.
	 */
	public void setName(String name) {
		
		this.name = name;
	}
	
	/**
	 * Set the color of the category.
	 *
	 * @param color New color to set.
	 */
	public void setColor(String color) {
		
		this.color = color;
	}
	
	/**
	 * Set the default flag of the category.
	 * Look for the previous Default Category and change its flag.
	 *
	 * @param aDefault New default flag to set.
	 */
	public void setDefault(boolean aDefault) {
		
		isDefault = aDefault;
	}
	
	/**
	 * Change the id of the client owner by the given one.
	 *
	 * @param clientID New client ID to set.
	 */
	public void setClientId(int clientID) {
		
		this.clientID = clientID;
	}
}
