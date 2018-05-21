package bll.model;

import bll.mappers.DAL.DALDebtMapper;
import dal.irepositories.IDebtRepository;
import dal.orm.IORM;

import java.sql.Date;

/**
 * DebtModel class.
 * Allows the mapping between the DAL entities and the Business Logic.
 * Only implements the constructors, the getters and the setters.
 *
 * @author Daniel Gonzalez Lopez
 * @version 1.2
 */
public class DebtModel {
	
	private int id;
	private int creatorID;
	private Integer contributorID = null;
	
	private String name;
	private String description;
	
	private double amount;
	
	private boolean isIncome;
	
	private Date expirationDate;
	
	
	protected DebtModel() {}
	
	/**
	 * Construct an instance.
	 *
	 * @param name
	 * @param description
	 * @param amount
	 * @param isIncome
	 * @param expirationDate
	 */
	protected DebtModel(String name, String description, double amount,
			boolean isIncome, Date expirationDate) {
		
		this.name = name;
		this.description = description;
		this.amount = amount;
		this.isIncome = isIncome;
		this.expirationDate = expirationDate;
	}
	
	
	/**
	 * Create a debt entry for the user into the database.
	 *
	 * @param orm ORM instance to use.
	 */
	protected void createDebt(IORM orm) {
		
		try {
			
			orm.beginTransaction();
			
			IDebtRepository repo = orm.getDebtRepository();
			setId(repo.addDebt(DALDebtMapper.toDbo(this)));
			
			orm.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Update debt entry with new data.
	 *
	 * @param orm ORM instance to use.
	 */
	protected void updateDebt(IORM orm) {
		
		try {
			
			orm.beginTransaction();
			
			IDebtRepository repo = orm.getDebtRepository();
			repo.update(DALDebtMapper.toDbo(this));
			
			orm.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Get ID of the debt.
	 *
	 * @return
	 */
	public int getId() {
		
		return id;
	}
	
	/**
	 * Get creator ID of the debt.
	 *
	 * @return
	 */
	public int getCreatorID() {
		
		return creatorID;
	}
	
	/**
	 * Get contributor ID of the debt.
	 *
	 * @return
	 */
	public Integer getContributorID() {
		
		return contributorID;
	}
	
	/**
	 * Get the name of the debt.
	 *
	 * @return
	 */
	public String getName() {
		
		return name;
	}
	
	/**
	 * Get the description of the debt.
	 *
	 * @return
	 */
	public String getDescription() {
		
		return description;
	}
	
	/**
	 * Get the amount of the debt.
	 *
	 * @return
	 */
	public double getAmount() {
		
		return amount;
	}
	
	/**
	 * Get whether it's an income or not.
	 *
	 * @return True if it's an income, false otherwise.
	 */
	public boolean isIncome() {
		
		return isIncome;
	}
	
	/**
	 * Get the expiration date.
	 *
	 * @return
	 */
	public Date getExpirationDate() {
		
		return expirationDate;
	}
	
	
	/**
	 * Set the ID.
	 *
	 * @param id
	 */
	public void setId(int id) {
		
		this.id = id;
	}
	
	/**
	 * Set the creator ID.
	 *
	 * @param creatorID
	 */
	public void setCreatorID(int creatorID) {
		
		this.creatorID = creatorID;
	}
	
	/**
	 * Set the contributor ID.
	 *
	 * @param contributorID
	 */
	public void setContributorID(Integer contributorID) {
		
		this.contributorID = contributorID;
	}
	
	/**
	 * Set the name.
	 *
	 * @param name
	 */
	public void setName(String name) {
		
		this.name = name;
	}
	
	/**
	 * Set the description.
	 *
	 * @param description
	 */
	public void setDescription(String description) {
		
		this.description = description;
	}
	
	/**
	 * Set the amount.
	 *
	 * @param amount
	 */
	public void setAmount(double amount) {
		
		this.amount = amount;
	}
	
	/**
	 * Set whether it's an income or not.
	 *
	 * @param income
	 */
	public void setIncome(boolean income) {
		
		isIncome = income;
	}
	
	/**
	 * Set the expiration date.
	 *
	 * @param expirationDate
	 */
	public void setExpirationDate(Date expirationDate) {
		
		this.expirationDate = expirationDate;
	}
}
