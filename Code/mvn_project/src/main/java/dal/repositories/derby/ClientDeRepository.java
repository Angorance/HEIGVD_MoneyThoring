package dal.repositories.derby;


import dal.dalexception.DALException;
import dal.entities.derby.ClientDeEntity;
import dal.ientites.IDALClientEntity;
import dal.irepositories.IClientRepository;
import dal.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.Collection;
import java.util.List;

public class ClientDeRepository implements IClientRepository {
    public IDALClientEntity getClient(int id) throws DALException {
        Session session = HibernateUtil.getDeSessionFactory().openSession();
        Transaction tr = null;
        ClientDeEntity client = null;
        try {

            tr = session.beginTransaction();

            client = (ClientDeEntity) session.createCriteria(ClientDeEntity.class)
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
                throw new DALException(he);
            }
        }
        return client;
    }

    public void addClient(IDALClientEntity client) throws DALException {

        Session session = HibernateUtil.getDeSessionFactory().openSession();
        Transaction tr = null;

        //vérifier avec les is instance et
        ClientDeEntity newClient = null;
        if (client.getClass() == ClientDeEntity.class)
            newClient = (ClientDeEntity) client;
        else
            throw new DALException();

        try {
            tr = session.beginTransaction();
            session.save(client);
            tr.commit();
        } catch (RuntimeException e) {
            try {
                tr.rollback();
                throw new DALException(e);
            } catch (HibernateException he) {
                throw he;
            }
        } finally {
            try {
                //session.flush();
                session.close();
            } catch (HibernateException he) {
                throw new DALException(he);
            }
        }
    }

    public Collection<IDALClientEntity> getClients() throws DALException {
        Session session = HibernateUtil.getDeSessionFactory().openSession();
        Transaction tr = null;
        List<IDALClientEntity> clients = null;
        try {
            tr = session.beginTransaction();

            clients = session.createQuery("from ClientDeEntity").list();

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
                throw new DALException(he);
            }
        }
        return clients;
    }

    public Collection<IDALClientEntity> getClients(int page, String sort) {
        return null;
    }


    public void update(IDALClientEntity client) throws DALException{
        Session session = HibernateUtil.getDeSessionFactory().openSession();
        Transaction tr = null;
        ClientDeEntity clientDe = null;
        if (client.getClass() == ClientDeEntity.class)
            clientDe = (ClientDeEntity) client;
        else
            throw new DALException();

        try {

            tr = session.beginTransaction();

            session.update(clientDe);

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
                throw new DALException(he);
            }
        }

    }

    public void delete(int id) throws DALException {
        Session session = HibernateUtil.getDeSessionFactory().openSession();
        Transaction tr = null;
        IDALClientEntity client = null;
        try {

            tr = session.beginTransaction();

            client = (ClientDeEntity)session.createCriteria(ClientDeEntity.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();

            session.delete(client);

            tr.commit();
        }
        catch (Exception e) {
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
                throw new DALException(he);
            }
        }

    }
}
