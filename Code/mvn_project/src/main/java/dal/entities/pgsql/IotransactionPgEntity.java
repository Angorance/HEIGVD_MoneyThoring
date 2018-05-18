package dal.entities.pgsql;

import dal.ientites.IDALIotransactionEntity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

/**
 * @author Guillaume zaretti
 * @version 1.2
 * @see IDALIotransactionEntity
 */
@Entity
@Table(name = "iotransaction", schema = "moneythoring",
		catalog = "moneythoring")
public class IotransactionPgEntity implements IDALIotransactionEntity {
	
	private int id;
	private String name;
	private String description;
	private Date datetransaction;
	private double amount;
	private String currency;
	private boolean isincome;
	private int categoryId;
	private int bankaccountId;
	private Integer budgetId;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Id
	@Column(name = "id", nullable = false)
	public int getId() {
		
		return id;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setId(int id) {
		
		this.id = id;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Basic
	@Column(name = "name", nullable = false, length = 50)
	public String getName() {
		
		return name;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setName(String name) {
		
		this.name = name;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Basic
	@Column(name = "description", nullable = true, length = 255)
	public String getDescription() {
		
		return description;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setDescription(String description) {
		
		this.description = description;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Basic
	@Column(name = "datetransaction", nullable = false)
	public Date getDatetransaction() {
		
		return datetransaction;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setDatetransaction(Date datetransaction) {
		
		this.datetransaction = datetransaction;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Basic
	@Column(name = "amount", nullable = false, precision = 0)
	public double getAmount() {
		
		return amount;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setAmount(double amount) {
		
		this.amount = amount;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Basic
	@Column(name = "currency", nullable = false, length = 50)
	public String getCurrency() {
		
		return currency;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setCurrency(String currency) {
		
		this.currency = currency;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Basic
	@Column(name = "isincome", nullable = false)
	public boolean isIsincome() {
		
		return isincome;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setIsincome(boolean isincome) {
		
		this.isincome = isincome;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Basic
	@Column(name = "category_id", nullable = false)
	public int getCategoryId() {
		
		return categoryId;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setCategoryId(int categoryId) {
		
		this.categoryId = categoryId;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Basic
	@Column(name = "bankaccount_id", nullable = false)
	public int getBankaccountId() {
		
		return bankaccountId;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setBankaccountId(int bankaccountId) {
		
		this.bankaccountId = bankaccountId;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Basic
	@Column(name = "budget_id", nullable = true)
	public Integer getBudgetId() {
		
		return budgetId;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setBudgetId(Integer budgetId) {
		
		this.budgetId = budgetId;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object o) {
		
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		IotransactionPgEntity that = (IotransactionPgEntity) o;
		return id == that.id && Double.compare(that.amount, amount) == 0
				&& isincome == that.isincome && categoryId == that.categoryId
				&& bankaccountId == that.bankaccountId && Objects
				.equals(name, that.name) && Objects
				.equals(description, that.description) && Objects
				.equals(datetransaction, that.datetransaction) && Objects
				.equals(currency, that.currency) && Objects
				.equals(budgetId, that.budgetId);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		
		return Objects
				.hash(id, name, description, datetransaction, amount, currency,
						isincome, categoryId, bankaccountId, budgetId);
	}
}
