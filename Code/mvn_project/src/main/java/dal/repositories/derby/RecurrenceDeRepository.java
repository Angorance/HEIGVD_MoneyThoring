package dal.repositories.derby;


import dal.dalexception.DALException;
import dal.entities.derby.RecurrenceDeEntity;
import dal.ientites.IDALRecurrenceEntity;
import dal.irepositories.IRecurrenceRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * RecurrenceDeRepository give the access methodes for handle the recurrence into derby persistence
 */
public class RecurrenceDeRepository implements IRecurrenceRepository {

    private Session session;
    private Transaction transaction;

    /**
     * Constructor of IotransactionDeRepository
     * @param session current session used
     * @param transaction current transaction used into the same session
     */
    public RecurrenceDeRepository(Session session, Transaction transaction){
        this.session = session;
        this.transaction = transaction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IDALRecurrenceEntity getRecurrence(int id) throws DALException {
        RecurrenceDeEntity recurrence = null;

        try {
            recurrence = (RecurrenceDeEntity) session.createCriteria(RecurrenceDeEntity.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();
        } catch (Exception e) {
            throw new DALException(e);
        }

        return recurrence;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer addRecurrence(IDALRecurrenceEntity recurrence) throws DALException {

        RecurrenceDeEntity newRecurrence = null;
        if (recurrence.getClass() == RecurrenceDeEntity.class)
            newRecurrence = (RecurrenceDeEntity) recurrence;
        else
            throw new DALException();

        try {
            return (Integer) session.save(newRecurrence);
        } catch (Exception e) {
            throw new DALException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IDALRecurrenceEntity> getRecurrences() throws DALException {
        List<IDALRecurrenceEntity> Recurrences = null;
        try {
            Recurrences = session.createQuery("from RecurrenceDeEntity").list();


        } catch (Exception e) {
            throw new DALException(e);
        }
        return Recurrences;
    }
    
    @Override
    public IDALRecurrenceEntity getRecurrenceByTransaction(int id) throws DALException {
    
        IDALRecurrenceEntity recurrenceEntity = null;

        try {
            recurrenceEntity = (RecurrenceDeEntity) session.createCriteria(RecurrenceDeEntity.class)
                    .add(Restrictions.eq("iotransaction_id", id))
                    .uniqueResult();
        } catch (Exception e) {
            throw new DALException(e);
        }
    
        return recurrenceEntity;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(IDALRecurrenceEntity recurrence) throws DALException{

        RecurrenceDeEntity RecurrencePg = null;
        if (recurrence.getClass() == RecurrenceDeEntity.class)
            RecurrencePg = (RecurrenceDeEntity) recurrence;
        else
            throw new DALException();

        try {


            session.update(RecurrencePg);


        } catch (Exception e) {
            throw new DALException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
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
