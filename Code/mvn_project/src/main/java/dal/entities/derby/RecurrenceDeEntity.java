package dal.entities.derby;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "RECURRENCE", schema = "MONEYTHORING", catalog = "")
public class RecurrenceDeEntity {
    private int id;
    private int gap;
    private Date nextdate;
    private int iotransactionId;
    private IotransactionDeEntity iotransactionByIotransactionId;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "GAP", nullable = false)
    public int getGap() {
        return gap;
    }

    public void setGap(int gap) {
        this.gap = gap;
    }

    @Basic
    @Column(name = "NEXTDATE", nullable = false)
    public Date getNextdate() {
        return nextdate;
    }

    public void setNextdate(Date nextdate) {
        this.nextdate = nextdate;
    }

    @Basic
    @Column(name = "IOTRANSACTION_ID", nullable = false)
    public int getIotransactionId() {
        return iotransactionId;
    }

    public void setIotransactionId(int iotransactionId) {
        this.iotransactionId = iotransactionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecurrenceDeEntity that = (RecurrenceDeEntity) o;

        if (id != that.id) return false;
        if (gap != that.gap) return false;
        if (iotransactionId != that.iotransactionId) return false;
        if (nextdate != null ? !nextdate.equals(that.nextdate) : that.nextdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + gap;
        result = 31 * result + (nextdate != null ? nextdate.hashCode() : 0);
        result = 31 * result + iotransactionId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "IOTRANSACTION_ID", referencedColumnName = "ID", nullable = false)
    public IotransactionDeEntity getIotransactionByIotransactionId() {
        return iotransactionByIotransactionId;
    }

    public void setIotransactionByIotransactionId(IotransactionDeEntity iotransactionByIotransactionId) {
        this.iotransactionByIotransactionId = iotransactionByIotransactionId;
    }
}
