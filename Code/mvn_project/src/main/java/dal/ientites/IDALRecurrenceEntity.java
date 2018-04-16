package dal.ientites;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import java.sql.Date;

public interface IDALRecurrenceEntity {
    @Id
    @Column(name = "id", nullable = false)
    int getId();

    void setId(int id);

    @Basic
    @Column(name = "gap", nullable = false)
    int getGap();

    void setGap(int gap);

    @Basic
    @Column(name = "nextdate", nullable = false)
    Date getNextdate();

    void setNextdate(Date nextdate);

    @Basic
    @Column(name = "iotransaction_id", nullable = false)
    int getIotransactionId();

    void setIotransactionId(int iotransactionId);
}
