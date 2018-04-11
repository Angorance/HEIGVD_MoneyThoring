package dal.repositories.pgsql;

import dal.entities.pgsql.ClientPGEntity;
import dal.ientites.IDALClientEntity;
import dal.irepositories.IClientRepository;
import dal.util.HibernateUtil;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.Collection;

public class ClientPgRepository implements IClientRepository {
    public IDALClientEntity getClient(int id) {
        Session session = HibernateUtil.getPGSessionFactory().openSession();
        Transaction tr = null;
        ClientPGEntity client = null;
        try {

            tr = session.beginTransaction();

            client = (ClientPGEntity) session.createCriteria(ClientPGEntity.class)
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
                session.close();
            } catch (HibernateException he) {
                System.out.println(he.toString());
            }
        }
        return client;
    }

    public Collection<IDALClientEntity> getClients() {
        return null;
    }

    public Collection<IDALClientEntity> getClients(int page, String sort) {
        return null;
    }

    public void update(IDALClientEntity client) {

    }

    public void delete(int id) {

    }
}
