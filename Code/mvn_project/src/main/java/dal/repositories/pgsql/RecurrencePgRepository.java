package dal.repositories.pgsql;

import dal.dalexception.DALException;
import dal.entities.pgsql.RecurrencePgEntity;
import dal.ientites.IDALRecurrenceEntity;
import dal.irepositories.IRecurrenceRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class RecurrencePgRepository implements IRecurrenceRepository {
    private Session session;
    private Transaction transaction;

    public RecurrencePgRepository(Session session, Transaction transaction) {
        this.session = session;
        this.transaction = transaction;
    }

    public IDALRecurrenceEntity getRecurrence(int id) throws DALException {
        RecurrencePgEntity Recurrence = null;

        try {
            Recurrence = (RecurrencePgEntity) session.createCriteria(RecurrencePgEntity.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();
        } catch (Exception e) {
            throw new DALException(e);
        }

        return Recurrence;
    }

    public void addRecurrence(IDALRecurrenceEntity Recurrence) throws DALException {
        
        RecurrencePgEntity newRecurrence = null;
        if (Recurrence.getClass() == RecurrencePgEntity.class)
            newRecurrence = (RecurrencePgEntity) Recurrence;
        else
            throw new DALException();

        try {
            session.save(newRecurrence);
        } catch (Exception e) {
            throw new DALException(e);
        }
    }

    public List<IDALRecurrenceEntity> getRecurrences() throws DALException {
        List<IDALRecurrenceEntity> Recurrences = null;
        try {
            Recurrences = session.createQuery("from RecurrencePgEntity").list();


        } catch (Exception e) {
            throw new DALException(e);
        }
        return Recurrences;
    }

    public void update(IDALRecurrenceEntity Recurrence) throws DALException {

        RecurrencePgEntity RecurrencePg = null;
        if (Recurrence.getClass() == RecurrencePgEntity.class)
            RecurrencePg = (RecurrencePgEntity) Recurrence;
        else
            throw new DALException();

        try {


            session.update(RecurrencePg);


        } catch (Exception e) {
            throw new DALException(e);
        }

    }

    public void delete(int id) throws DALException {
        IDALRecurrenceEntity Recurrence = null;
        try {
            Recurrence = (RecurrencePgEntity) session.createCriteria(RecurrencePgEntity.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();

            session.delete(Recurrence);
        } catch (Exception e) {
            throw new DALException(e);
        }
    }
}
