package dal.repositories.pgsql;

import dal.dalexception.DALException;
import dal.entities.pgsql.ClientPgEntity;
import dal.ientites.IDALClientEntity;
import dal.irepositories.IClientRepository;
import dal.util.HibernateUtil;
import jdk.jshell.spi.ExecutionControl;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.Collection;
import java.util.List;

public class ClientPgRepository implements IClientRepository {

    public IDALClientEntity getClient(int id) throws DALException {
        Session session = HibernateUtil.getPGSessionFactory().openSession();
        Transaction tr = null;
        ClientPgEntity client = null;
        try {

            tr = session.beginTransaction();

            client = (ClientPgEntity) session.createCriteria(ClientPgEntity.class)
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
        return client;
    }

    public void addClient(IDALClientEntity client) throws DALException {

        Session session = HibernateUtil.getPGSessionFactory().openSession();
        Transaction tr = null;

        //vérifier avec les is instance et
        ClientPgEntity newClient = null;
        if (client.getClass() == ClientPgEntity.class)
            newClient = (ClientPgEntity) client;
        else
            throw new DALException();

        try {
            tr = session.beginTransaction();
            session.save(client);
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

    public Collection<IDALClientEntity> getClients() {
        Session session = HibernateUtil.getPGSessionFactory().openSession();
        Transaction tr = null;
        List<IDALClientEntity> clients = null;
        try {
            tr = session.beginTransaction();

            clients = session.createQuery("from ClientPgEntity").list();

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
        return clients;
    }

    public Collection<IDALClientEntity> getClients(int page, String sort) {
       return null;
    }


    public void update(IDALClientEntity client) throws DALException{
        Session session = HibernateUtil.getPGSessionFactory().openSession();
        Transaction tr = null;
        ClientPgEntity clientPg = null;
        if (client.getClass() == ClientPgEntity.class)
            clientPg = (ClientPgEntity) client;
        else
            throw new DALException();

        try {

            tr = session.beginTransaction();

            session.update(clientPg);

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

    public void delete(int id) {
        Session session = HibernateUtil.getPGSessionFactory().openSession();
        Transaction tr = null;
        IDALClientEntity client = null;
        try {

            tr = session.beginTransaction();

            client = (ClientPgEntity)session.createCriteria(ClientPgEntity.class)
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
                System.out.println(he.toString());
            }
        }

    }
}
