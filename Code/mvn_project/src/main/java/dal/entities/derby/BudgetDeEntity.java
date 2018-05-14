package dal.entities.derby;

import dal.ientites.IDALBudgetEntity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

/**
 * TODO
 */
@Entity
@Table(name = "budget", schema = "moneythoring", catalog = "moneythoring")
public class BudgetDeEntity implements IDALBudgetEntity {
	
	private int id;
	private String name;
	private double amount;
	private boolean isshared;
	private boolean isrecurrent;
	private Date startingdate;
	private Date endingdate;
	private Integer gap;
	private int clientId;
	
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
	@Column(name = "isshared", nullable = false)
	public boolean isIsshared() {
		
		return isshared;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setIsshared(boolean isshared) {
		
		this.isshared = isshared;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Basic
	@Column(name = "isrecurrent", nullable = false)
	public boolean isIsrecurrent() {
		
		return isrecurrent;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setIsrecurrent(boolean isrecurrent) {
		
		this.isrecurrent = isrecurrent;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Basic
	@Column(name = "startingdate", nullable = false)
	public Date getStartingdate() {
		
		return startingdate;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setStartingdate(Date startingdate) {
		
		this.startingdate = startingdate;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Basic
	@Column(name = "endingdate", nullable = false)
	public Date getEndingdate() {
		
		return endingdate;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setEndingdate(Date endingdate) {
		
		this.endingdate = endingdate;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Basic
	@Column(name = "gap", nullable = true)
	public Integer getGap() {
		
		return gap;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setGap(Integer gap) {
		
		this.gap = gap;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Basic
	@Column(name = "client_id", nullable = false)
	public int getClientId() {
		
		return clientId;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setClientId(int clientId) {
		
		this.clientId = clientId;
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
		BudgetDeEntity that = (BudgetDeEntity) o;
		return id == that.id && Double.compare(that.amount, amount) == 0
				&& isshared == that.isshared && isrecurrent == that.isrecurrent
				&& clientId == that.clientId && Objects.equals(name, that.name)
				&& Objects.equals(startingdate, that.startingdate) && Objects
				.equals(endingdate, that.endingdate) && Objects
				.equals(gap, that.gap);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		
		return Objects
				.hash(id, name, amount, isshared, isrecurrent, startingdate,
						endingdate, gap, clientId);
	}
}
