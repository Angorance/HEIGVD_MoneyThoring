package dal.ientites;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import java.sql.Date;

/**
 * IDALRecurrenceEntity interface.
 * interface who represent a recurrence entity
 *
 * @author Guillaume Zaretti
 * @version 1.2
 */
public interface IDALRecurrenceEntity {

	@Id
	@Column(name = "id", nullable = false)
	int getId();

	/**
	 * set the  id of the recurrence
	 *
	 * @param recurrence id
	 */
	void setId(int id);

	/**
	 * get the gap
	 * @return gap of the recurrence
	 */
	@Basic
	@Column(name = "gap", nullable = false)
	int getGap();

	/**
	 * set gap of the recurrence
	 * @param gap of the recurrence
	 */
	void setGap(int gap);

	/**
	 * get next date of the recurrence
	 * @return the next date
	 */
	@Basic
	@Column(name = "nextdate", nullable = false)
	Date getNextdate();

	/**
	 * set next date of the recurence
	 * @param nextdate of the recurence
	 */
	void setNextdate(Date nextdate);

	/**
	 * get iotransaction id of the recurence
	 * @return the iotransaction id of the recurence
	 */
	@Basic
	@Column(name = "iotransaction_id", nullable = false)
	int getIotransactionId();

	/**
	 * set iotransaction id of the recurence
	 * @param iotransactionId id of the iotransaction
	 */
	void setIotransactionId(int iotransactionId);
}
