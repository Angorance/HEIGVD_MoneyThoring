package dal.repositories.derby;


import dal.dalexception.DALException;
import dal.entities.derby.IotransactionDeEntity;
import dal.ientites.IDALIotransactionEntity;
import dal.irepositories.IIotransactionRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * IotransactionDeRepository give the access methodes for handle the iotransaction into derby persistence
 */
public class IotransactionDeRepository implements IIotransactionRepository {

    private Session session;
    private Transaction transaction;

    /**
     * Constructor of IotransactionDeRepository
     * @param session current session used
     * @param transaction current transaction used into the same session
     */
    public IotransactionDeRepository(Session session, Transaction transaction){
        this.session = session;
        this.transaction = transaction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IDALIotransactionEntity getIotransaction(int id) throws DALException {
        IotransactionDeEntity iotransaction = null;

        try {
            iotransaction = (IotransactionDeEntity) session.createCriteria(IotransactionDeEntity.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();
        } catch (Exception e) {
            throw new DALException(e);
        }

        return iotransaction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IDALIotransactionEntity> getIotransactions() throws DALException {
        List<IDALIotransactionEntity> iotransaction = null;
        try {
            iotransaction = session.createQuery("from IotransactionDeEntity").list();


        } catch (Exception e) {
            throw new DALException(e);
        }
        return iotransaction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer addIotransaction(IDALIotransactionEntity iotransaction) throws DALException {

        IotransactionDeEntity newIotransaction = null;
        if (iotransaction.getClass() == IotransactionDeEntity.class)
            newIotransaction = (IotransactionDeEntity) iotransaction;
        else
            throw new DALException();

        try {
            return (Integer) session.save(newIotransaction);
        } catch (Exception e) {
            throw new DALException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(IDALIotransactionEntity iotransaction) throws DALException{

        IotransactionDeEntity iotransactionDe = null;
        if (iotransaction.getClass() == IotransactionDeEntity.class)
            iotransactionDe = (IotransactionDeEntity) iotransaction;
        else
            throw new DALException();

        try {


            session.update(iotransactionDe);


        } catch (Exception e) {
            throw new DALException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(int id) throws DALException {
        IDALIotransactionEntity iotransaction = null;
        try {
            iotransaction = (IotransactionDeEntity) session.createCriteria(IotransactionDeEntity.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();
            session.delete(iotransaction);
        } catch (Exception e) {
            throw new DALException(e);
        }

    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<IDALIotransactionEntity> getIotransactionsByBankaccount(int bankaccountId) throws DALException {
        List<IDALIotransactionEntity> iotransaction = null;
        try {
            iotransaction = session.createQuery("from IotransactionDeEntity where  bankaccountId = :bankaccountId").setParameter("bankaccountId", bankaccountId).list();
        } catch (Exception e) {
            throw new DALException(e);
        }
        return iotransaction;
    }
}
