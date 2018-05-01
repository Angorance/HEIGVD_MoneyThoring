package dal.repositories.derby;


import dal.dalexception.DALException;
import dal.entities.derby.RecurrenceDeEntity;
import dal.ientites.IDALRecurrenceEntity;
import dal.irepositories.IRecurrenceRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class RecurrenceDeRepository implements IRecurrenceRepository {

    private Session session;
    private Transaction transaction;

    public RecurrenceDeRepository(Session session, Transaction transaction){
        this.session = session;
        this.transaction = transaction;
    }

    public IDALRecurrenceEntity getRecurrence(int id) throws DALException {
        RecurrenceDeEntity Recurrence = null;

        try {
            Recurrence = (RecurrenceDeEntity) session.createCriteria(RecurrenceDeEntity.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();
        } catch (Exception e) {
            throw new DALException(e);
        }

        return Recurrence;
    }

    public void addRecurrence(IDALRecurrenceEntity Recurrence) throws DALException {

        RecurrenceDeEntity newRecurrence = null;
        if (Recurrence.getClass() == RecurrenceDeEntity.class)
            newRecurrence = (RecurrenceDeEntity) Recurrence;
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
            Recurrences = session.createQuery("from RecurrenceDeEntity").list();


        } catch (Exception e) {
            throw new DALException(e);
        }
        return Recurrences;
    }




    public void update(IDALRecurrenceEntity Recurrence) throws DALException{

        RecurrenceDeEntity RecurrencePg = null;
        if (Recurrence.getClass() == RecurrenceDeEntity.class)
            RecurrencePg = (RecurrenceDeEntity) Recurrence;
        else
            throw new DALException();

        try {


            session.update(RecurrencePg);


        } catch (Exception e) {
            throw new DALException(e);
        }
    }

    public void delete(int id) throws DALException {
        IDALRecurrenceEntity recurrence = null;
        try {
            recurrence = (RecurrenceDeEntity) session.createCriteria(RecurrenceDeEntity.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();

            session.delete(recurrence);
        } catch (Exception e) {
            throw new DALException(e);
        }

    }
}
