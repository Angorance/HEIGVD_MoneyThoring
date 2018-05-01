package dal.repositories.derby;


import dal.dalexception.DALException;
import dal.entities.derby.IotransactionDeEntity;
import dal.ientites.IDALIotransactionEntity;
import dal.irepositories.IIotransactionRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class IotransactionDeRepository implements IIotransactionRepository {

    private Session session;
    private Transaction transaction;

    public IotransactionDeRepository(Session session, Transaction transaction){
        this.session = session;
        this.transaction = transaction;
    }

    public IDALIotransactionEntity getIotransaction(int id) throws DALException {
        IotransactionDeEntity Iotransaction = null;

        try {
            Iotransaction = (IotransactionDeEntity) session.createCriteria(IotransactionDeEntity.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();
        } catch (Exception e) {
            throw new DALException(e);
        }

        return Iotransaction;
    }

    @Override
    public List<IDALIotransactionEntity> getIotransactions() throws DALException {
        List<IDALIotransactionEntity> Iotransactions = null;
        try {
            Iotransactions = session.createQuery("from IotransactionDeEntity").list();


        } catch (Exception e) {
            throw new DALException(e);
        }
        return Iotransactions;
    }


    public void addIotransaction(IDALIotransactionEntity Iotransaction) throws DALException {

        IotransactionDeEntity newIotransaction = null;
        if (Iotransaction.getClass() == IotransactionDeEntity.class)
            newIotransaction = (IotransactionDeEntity) Iotransaction;
        else
            throw new DALException();

        try {
            session.save(newIotransaction);
        } catch (Exception e) {
            throw new DALException(e);
        }
    }




    public void update(IDALIotransactionEntity Iotransaction) throws DALException{

        IotransactionDeEntity IotransactionPg = null;
        if (Iotransaction.getClass() == IotransactionDeEntity.class)
            IotransactionPg = (IotransactionDeEntity) Iotransaction;
        else
            throw new DALException();

        try {


            session.update(IotransactionPg);


        } catch (Exception e) {
            throw new DALException(e);
        }
    }

    public void delete(int id) throws DALException {
        IDALIotransactionEntity Iotransaction = null;
        try {
            Iotransaction = (IotransactionDeEntity) session.createCriteria(IotransactionDeEntity.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();

            session.delete(Iotransaction);
        } catch (Exception e) {
            throw new DALException(e);
        }

    }
}
