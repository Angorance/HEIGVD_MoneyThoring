package dal.repositories.pgsql;

import dal.dalexception.DALException;
import dal.entities.pgsql.IotransactionPgEntity;
import dal.ientites.IDALIotransactionEntity;
import dal.irepositories.IIotransactionRepository;
import dal.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class IotransactionPgRepository implements IIotransactionRepository {

    private Session session;
    private Transaction transaction;

    public IotransactionPgRepository(Session session, Transaction transaction){
        this.session = session;
        this.transaction = transaction;
    }



    @Override
    public IDALIotransactionEntity getIotransaction(int id) throws DALException {
        Session session = HibernateUtil.getPGSessionFactory().openSession();
        Transaction tr = null;
        IotransactionPgEntity bankaccont = null;
        try {

            tr = session.beginTransaction();

            bankaccont = (IotransactionPgEntity) session.createCriteria(IotransactionPgEntity.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();

            tr.commit();
        } catch (Exception e) {

            try {
                tr.rollback();
            } catch (HibernateException he) {
                throw he;
            }
        } finally {
            try {
                //session.flush();
                session.close();
            } catch (HibernateException he) {
                System.out.println(he.toString());
            }
        }
        return bankaccont;
    }

    @Override
    public List<IDALIotransactionEntity> getIotransactions() throws DALException {
        Session session = HibernateUtil.getPGSessionFactory().openSession();
        Transaction tr = null;
        List<IDALIotransactionEntity> Iotransactions = null;
        try {
            tr = session.beginTransaction();

            Iotransactions = session.createQuery("from IotransactionPgEntity").list();

            tr.commit();
        } catch (Exception e) {
            try {
                tr.rollback();
            } catch (HibernateException he) {
                throw he;
            }
        } finally {
            try {
                //session.flush();
                session.close();
            } catch (HibernateException he) {
                System.out.println(he.toString());
            }
        }
        return Iotransactions;
    }

    @Override
    public void update(IDALIotransactionEntity Iotransaction) throws DALException {

        Session session = HibernateUtil.getPGSessionFactory().openSession();
        Transaction tr = null;
        IotransactionPgEntity IotransactionPG = null;
        if (Iotransaction.getClass() == IotransactionPgEntity.class)
            IotransactionPG = (IotransactionPgEntity) Iotransaction;
        else
            throw new DALException();

        try {

            tr = session.beginTransaction();

            session.update(IotransactionPG);

            tr.commit();
        } catch (Exception e) {
            try {
                tr.rollback();
            } catch (HibernateException he) {
                throw he;
            }
        } finally {
            try {
                //session.flush();
                session.close();
            } catch (HibernateException he) {
                System.out.println(he.toString());
            }
        }
    }

    @Override
    public void addIotransaction(IDALIotransactionEntity Iotransaction) throws DALException {
        Session session = HibernateUtil.getPGSessionFactory().openSession();
        Transaction tr = null;

        IDALIotransactionEntity newIotransaction = null;
        if (Iotransaction.getClass() == IotransactionPgEntity.class)
            newIotransaction = (IotransactionPgEntity) Iotransaction;
        else
            throw new DALException();

        try {
            tr = session.beginTransaction();
            session.save(newIotransaction);
            tr.commit();
        } catch (RuntimeException e) {
            try {
                tr.rollback();
            } catch (HibernateException he) {
                throw he;
            }
        } finally {
            try {
                //session.flush();
                session.close();
            } catch (HibernateException he) {
                System.out.println(he.toString());
            }
        }

    }

    public void delete(int id) {
        Session session = HibernateUtil.getPGSessionFactory().openSession();
        Transaction tr = null;
        IDALIotransactionEntity Iotransaction = null;
        try {

            tr = session.beginTransaction();

            Iotransaction = (IotransactionPgEntity) session.createCriteria(IotransactionPgEntity.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();

            session.delete(Iotransaction);

            tr.commit();
        } catch (Exception e) {
            try {
                tr.rollback();
            } catch (HibernateException he) {
                throw he;
            }
        } finally {
            try {
                //session.flush();
                session.close();
            } catch (HibernateException he) {
                System.out.println(he.toString());
            }
        }

    }
}
