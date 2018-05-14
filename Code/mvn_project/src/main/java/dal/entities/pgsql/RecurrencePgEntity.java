package dal.entities.pgsql;

import dal.ientites.IDALRecurrenceEntity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

/**
 * TODO
 */
@Entity
@Table(name = "recurrence", schema = "moneythoring", catalog = "moneythoring")
public class RecurrencePgEntity implements IDALRecurrenceEntity {
	
	private int id;
	private int gap;
	private Date nextdate;
	private int iotransactionId;
	
	@Id
	@Column(name = "id", nullable = false)
	public int getId() {
		
		return id;
	}
	
	public void setId(int id) {
		
		this.id = id;
	}
	
	@Basic
	@Column(name = "gap", nullable = false)
	public int getGap() {
		
		return gap;
	}
	
	public void setGap(int gap) {
		
		this.gap = gap;
	}
	
	@Basic
	@Column(name = "nextdate", nullable = false)
	public Date getNextdate() {
		
		return nextdate;
	}
	
	public void setNextdate(Date nextdate) {
		
		this.nextdate = nextdate;
	}
	
	@Basic
	@Column(name = "iotransaction_id", nullable = false)
	public int getIotransactionId() {
		
		return iotransactionId;
	}
	
	public void setIotransactionId(int iotransactionId) {
		
		this.iotransactionId = iotransactionId;
	}
	
	@Override
	public boolean equals(Object o) {
		
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		RecurrencePgEntity that = (RecurrencePgEntity) o;
		return id == that.id && gap == that.gap
				&& iotransactionId == that.iotransactionId && Objects
				.equals(nextdate, that.nextdate);
	}
	
	@Override
	public int hashCode() {
		
		return Objects.hash(id, gap, nextdate, iotransactionId);
	}
}
