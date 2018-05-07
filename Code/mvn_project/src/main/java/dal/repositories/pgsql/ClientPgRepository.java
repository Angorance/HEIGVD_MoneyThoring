package dal.repositories.pgsql;

import dal.dalexception.DALException;
import dal.entities.pgsql.ClientPgEntity;
import dal.ientites.IDALClientEntity;
import dal.irepositories.IClientRepository;
import dal.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Transaction;

import java.util.List;

/**
 * ClientPgRepository give the access methodes for handle the client into postgres persistence
 */
public class ClientPgRepository implements IClientRepository {
    private Session session;
    private Transaction transaction;

    /**
     * Constructor of ClientPgRepository
     * @param session current session used
     * @param transaction current transaction used into the same session
     */
    public ClientPgRepository(Session session, Transaction transaction) {
        this.session = session;
        this.transaction = transaction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void addClient(IDALClientEntity client) throws DALException {

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

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IDALClientEntity> getClients() throws DALException {
        List<IDALClientEntity> clients = null;
        try {
            clients = session.createQuery("from ClientPgEntity").list();


        } catch (Exception e) {
            throw new DALException(e);
        }
        return clients;
    }

    /**
     * {@inheritDoc}
     */
    @Override
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

    /**
     * {@inheritDoc}
     */
    @Override
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

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean mailExist(String email) throws DALException {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public IDALClientEntity checkUserAndPassword(String usernameOrEmail, String password) throws DALException {
        ClientPgEntity client = null;

        try {
            client = (ClientPgEntity) session.createCriteria(ClientPgEntity.class)
                    .add(Restrictions.and(Restrictions.or(Restrictions.eq("email", usernameOrEmail),
                            Restrictions.eq("username", usernameOrEmail)),
                            Restrictions.eq("password", password))).uniqueResult();
        } catch (Exception e) {
            throw new DALException(e);
        }

        return client;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String retriveSaltByUserLogin(String usernameOrEmail) throws DALException {
        ClientPgEntity client = null;
        String salt = "";
        try {
            client = (ClientPgEntity) session.createCriteria(ClientPgEntity.class)
                    .add(Restrictions.and(Restrictions.or(Restrictions.eq("email", usernameOrEmail),
                            Restrictions.eq("username", usernameOrEmail)))).uniqueResult();
        } catch (Exception e) {
            throw new DALException(e);
        }

        if (client != null)
            salt = client.getSalt();

        return salt;
    }
}
