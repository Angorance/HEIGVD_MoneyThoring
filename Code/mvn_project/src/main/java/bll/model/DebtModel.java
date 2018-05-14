package bll.model;

import bll.mappers.DAL.DALDebtMapper;
import dal.irepositories.IDebtRepository;
import dal.orm.IORM;

import java.sql.Date;

/**
 * @author Daniel Gonzalez Lopez
 * @version 1.0
 */
public class DebtModel {

	private int id;
	private int creatorID;
	private int contributorID;
	
	private String name;
	private String description;
	
	private double amount;
	
	private boolean isIncome;
	
	private Date expirationDate;
	
	
	protected DebtModel() {}
	
	
	/**
	 * Create a transaction entry for the user into the database.
	 *
	 * @param orm ORM instance to use.
	 */
	protected void createIOTransaction(IORM orm) {
		
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
	 * Update transaction entry with new data.
	 *
	 * @param orm ORM instance to use.
	 */
	protected void updateIOTransaction(IORM orm) {
		
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
	 * TODO
	 *
	 * @return
	 */
	public int getId() {
		
		return id;
	}
	
	/**
	 * TODO
	 *
	 * @return
	 */
	public int getCreatorID() {
		
		return creatorID;
	}
	
	/**
	 * TODO
	 *
	 * @return
	 */
	public int getContributorID() {
		
		return contributorID;
	}
	
	/**
	 * TODO
	 *
	 * @return
	 */
	public String getName() {
		
		return name;
	}
	
	/**
	 * TODO
	 *
	 * @return
	 */
	public String getDescription() {
		
		return description;
	}
	
	/**
	 * TODO
	 *
	 * @return
	 */
	public double getAmount() {
		
		return amount;
	}
	
	/**
	 * TODO
	 *
	 * @return
	 */
	public boolean isIncome() {
		
		return isIncome;
	}
	
	/**
	 * TODO
	 *
	 * @return
	 */
	public Date getExpirationDate() {
		
		return expirationDate;
	}
	
	
	/**
	 * TODO
	 *
	 * @param id
	 */
	public void setId(int id) {
		
		this.id = id;
	}
	
	/**
	 * TODO
	 *
	 * @param creatorID
	 */
	public void setCreatorID(int creatorID) {
		
		this.creatorID = creatorID;
	}
	
	/**
	 * TODO
	 *
	 * @param contributorID
	 */
	public void setContributorID(int contributorID) {
		
		this.contributorID = contributorID;
	}
	
	/**
	 * TODO
	 *
	 * @param name
	 */
	public void setName(String name) {
		
		this.name = name;
	}
	
	/**
	 * TODO
	 *
	 * @param description
	 */
	public void setDescription(String description) {
		
		this.description = description;
	}
	
	/**
	 * TODO
	 *
	 * @param amount
	 */
	public void setAmount(double amount) {
		
		this.amount = amount;
	}
	
	/**
	 * TODO
	 *
	 * @param income
	 */
	public void setIncome(boolean income) {
		
		isIncome = income;
	}
	
	/**
	 * TODO
	 *
	 * @param expirationDate
	 */
	public void setExpirationDate(Date expirationDate) {
		
		this.expirationDate = expirationDate;
	}
}
