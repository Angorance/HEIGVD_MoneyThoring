package dal.repositories.pgsql;

import dal.dalexception.DALException;
import dal.entities.pgsql.RecurrencePgEntity;
import dal.ientites.IDALRecurrenceEntity;
import dal.irepositories.IRecurrenceRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * RecurrencePgRepository give the access methodes for handle the recurrence into derby persistence
 */
public class RecurrencePgRepository implements IRecurrenceRepository {
    private Session session;
    private Transaction transaction;

    /**
     * Constructor of IotransactionDeRepository
     * @param session current session used
     * @param transaction current transaction used into the same session
     */
    public RecurrencePgRepository(Session session, Transaction transaction) {
        this.session = session;
        this.transaction = transaction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IDALRecurrenceEntity getRecurrence(int id) throws DALException {
        RecurrencePgEntity recurrence = null;

        try {
            recurrence = (RecurrencePgEntity) session.createCriteria(RecurrencePgEntity.class)
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
    public void addRecurrence(IDALRecurrenceEntity recurrence) throws DALException {
        
        RecurrencePgEntity newRecurrence = null;
        if (recurrence.getClass() == RecurrencePgEntity.class)
            newRecurrence = (RecurrencePgEntity) recurrence;
        else
            throw new DALException();

        try {
            session.save(newRecurrence);
        } catch (Exception e) {
            throw new DALException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IDALRecurrenceEntity> getRecurrences() throws DALException {
        List<IDALRecurrenceEntity> recurrence = null;
        try {
            recurrence = session.createQuery("from RecurrencePgEntity").list();


        } catch (Exception e) {
            throw new DALException(e);
        }
        return recurrence;
    }
    
    @Override
    public IDALRecurrenceEntity getRecurrenceByTransaction(int id) throws DALException {
    
        IDALRecurrenceEntity recurrenceEntity = null;
        
        try {
            recurrenceEntity = (RecurrencePgEntity) session.createCriteria(RecurrencePgEntity.class)
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
    public void update(IDALRecurrenceEntity Recurrence) throws DALException {

        RecurrencePgEntity recurrencePg = null;
        if (Recurrence.getClass() == RecurrencePgEntity.class)
            recurrencePg = (RecurrencePgEntity) Recurrence;
        else
            throw new DALException();

        try {


            session.update(recurrencePg);


        } catch (Exception e) {
            throw new DALException(e);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
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
