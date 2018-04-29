package dal.repositories.pgsql;

import dal.dalexception.DALException;
import dal.entities.pgsql.ClientPgEntity;
import dal.ientites.IDALClientEntity;
import dal.irepositories.ICategoryRepository;
import dal.irepositories.IClientRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class CategoryPgRepository implements ICategoryRepository {
    private Session session;
    private Transaction transaction;
    public CategoryPgRepository(Session session, Transaction transaction) {
        this.session = session;
        this.transaction = transaction;
    }

    public IDALClientEntity getClient(int id) throws DALException {
        ClientPgEntity client = null;

        try {
            client = (ClientPgEntity) session.createCriteria(ClientPgEntity.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();
        } catch (Exception e) {
            throw new DALException(e);
        }

        return client;
    }

    public void addClient(IDALClientEntity client) throws DALException {
        System.out.println("session du repo " + session);
        ClientPgEntity newClient = null;
        if (client.getClass() == ClientPgEntity.class)
            newClient = (ClientPgEntity) client;
        else
            throw new DALException();

        try {
            session.save(newClient);
        } catch (Exception e) {
            throw new DALException(e);
        }
    }

    public List<IDALClientEntity> getClients() throws DALException {
        List<IDALClientEntity> clients = null;
        try {
            clients = session.createQuery("from ClientPgEntity").list();


        } catch (Exception e) {
            throw new DALException(e);
        }
        return clients;
    }

    public void update(IDALClientEntity client) throws DALException {

        ClientPgEntity clientPg = null;
        if (client.getClass() == ClientPgEntity.class)
            clientPg = (ClientPgEntity) client;
        else
            throw new DALException();

        try {


            session.update(clientPg);


        } catch (Exception e) {
            throw new DALException(e);
        }

    }

    public void delete(int id) throws DALException {
        IDALClientEntity client = null;
        try {
            client = (ClientPgEntity) session.createCriteria(ClientPgEntity.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();

            session.delete(client);
        } catch (Exception e) {
            throw new DALException(e);
        }
    }

    @Override
    public boolean pseudoExist(String username) throws DALException {
        ClientPgEntity client = null;

        try {
            client = (ClientPgEntity) session.createCriteria(ClientPgEntity.class)
                    .add(Restrictions.eq("username", username))
                    .uniqueResult();
        } catch (Exception e) {
            throw new DALException(e);
        }

        return (client != null);
    }

    @Override
    public boolean mailExsit(String email) throws DALException {
        ClientPgEntity client = null;

        try {
            client = (ClientPgEntity) session.createCriteria(ClientPgEntity.class)
                    .add(Restrictions.eq("email", email))
                    .uniqueResult();
        } catch (Exception e) {
            throw new DALException(e);
        }

        return (client != null);
    }
}
