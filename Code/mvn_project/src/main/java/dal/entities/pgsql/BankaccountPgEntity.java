package dal.entities.pgsql;

import dal.ientites.IDALBankaccountEntity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Guillaume zaretti
 * @version 1.2
 * @see IDALBankaccountEntity
 */
@Entity
@Table(name = "bankaccount", schema = "moneythoring", catalog = "moneythoring")
public class BankaccountPgEntity implements IDALBankaccountEntity {
	
	private int id;
	private String name;
	private String namebank;
	private String typeaccount;
	private double amount;
	private boolean isdefault;
	private boolean isvisible;
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
	@Column(name = "namebank", nullable = true, length = 50)
	public String getNamebank() {
		
		return namebank;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setNamebank(String namebank) {
		
		this.namebank = namebank;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Basic
	@Column(name = "typeaccount", nullable = false, length = 100)
	public String getTypeaccount() {
		
		return typeaccount;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setTypeaccount(String typeaccount) {
		
		this.typeaccount = typeaccount;
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
	
	public void setAmount(double amount) {
		
		this.amount = amount;
	}
	
	@Basic
	@Column(name = "isdefault", nullable = false)
	public boolean isIsdefault() {
		
		return isdefault;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setIsdefault(boolean isdefault) {
		
		this.isdefault = isdefault;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Basic
	@Column(name = "isvisible", nullable = false)
	public boolean isIsvisible() {
		
		return isvisible;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setIsvisible(boolean isvisible) {
		
		this.isvisible = isvisible;
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
		BankaccountPgEntity that = (BankaccountPgEntity) o;
		return id == that.id && Double.compare(that.amount, amount) == 0
				&& isdefault == that.isdefault && isvisible == that.isvisible
				&& clientId == that.clientId && Objects.equals(name, that.name)
				&& Objects.equals(namebank, that.namebank) && Objects
				.equals(typeaccount, that.typeaccount);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		
		return Objects.hash(id, name, namebank, typeaccount, amount, isdefault,
				isvisible, clientId);
	}
}
