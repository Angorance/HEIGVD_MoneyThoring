package dal.entities.derby;

import dal.ientites.IDALIotransactionEntity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

/**
 * TODO
 */
@Entity
@Table(name = "IOTRANSACTION", schema = "MONEYTHORING", catalog = "")
public class IotransactionDeEntity implements IDALIotransactionEntity {
	
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
	@Column(name = "ID", nullable = false)
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
	@Column(name = "NAME", nullable = false, length = 50)
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
	@Column(name = "DESCRIPTION", nullable = true, length = 255)
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
	@Column(name = "DATETRANSACTION", nullable = false)
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
	@Column(name = "AMOUNT", nullable = false, precision = 0)
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
	@Column(name = "CURRENCY", nullable = false, length = 50)
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
	@Column(name = "ISINCOME", nullable = false)
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
	@Column(name = "CATEGORY_ID", nullable = false)
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
	@Column(name = "BANKACCOUNT_ID", nullable = false)
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
	@Column(name = "BUDGET_ID", nullable = true)
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
		IotransactionDeEntity that = (IotransactionDeEntity) o;
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
