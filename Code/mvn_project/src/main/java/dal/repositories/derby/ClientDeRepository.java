package dal.repositories.derby;


import dal.dalexception.DALException;
import dal.entities.derby.ClientDeEntity;
import dal.entities.pgsql.ClientPgEntity;
import dal.ientites.IDALClientEntity;
import dal.irepositories.IClientRepository;
import dal.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.Collection;
import java.util.List;

/**
 * ClientDeRepository give the access methodes for handle the client into derby persistence
 */
public class ClientDeRepository implements IClientRepository {

    private Session session;
    private Transaction transaction;

    /**
     * Constructor of ClientDeRepository
     * @param session current session used
     * @param transaction current transaction used into the same session
     */
    public  ClientDeRepository(Session session, Transaction transaction){
        this.session = session;
        this.transaction = transaction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IDALClientEntity getClient(int id) throws DALException {
        ClientDeEntity client = null;

        try {
            client = (ClientDeEntity) session.createCriteria(ClientDeEntity.class)
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

        ClientDeEntity newClient = null;
        if (client.getClass() == ClientDeEntity.class)
            newClient = (ClientDeEntity) client;
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
            clients = session.createQuery("from ClientDeEntity").list();


        } catch (Exception e) {
            throw new DALException(e);
        }
        return clients;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void update(IDALClientEntity client) throws DALException{

        ClientDeEntity clientPg = null;
        if (client.getClass() == ClientDeEntity.class)
            clientPg = (ClientDeEntity) client;
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
            client = (ClientDeEntity) session.createCriteria(ClientDeEntity.class)
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
        ClientDeEntity client = null;

        try {
            client = (ClientDeEntity) session.createCriteria(ClientDeEntity.class)
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
        ClientDeEntity client = null;

        try {
            client = (ClientDeEntity) session.createCriteria(ClientDeEntity.class)
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
        ClientDeEntity client = null;

        try {
            client = (ClientDeEntity) session.createCriteria(ClientDeEntity.class)
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
    public boolean isActivated(String usernameOrEmail, String password) throws DALException {
        
        ClientDeEntity client = null;
        boolean isActivated = false;
        
        try {
            client = (ClientDeEntity) session.createCriteria(ClientDeEntity.class)
                    .add(Restrictions.and(Restrictions.or(Restrictions.eq("email", usernameOrEmail),
                            Restrictions.eq("username", usernameOrEmail)))).uniqueResult();
        } catch (Exception e) {
            throw new DALException(e);
        }
        
        if(client != null)
            isActivated = client.getIsactivated();
        
        return isActivated;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String retriveSaltByUserLogin(String usernameOrEmail) throws DALException {
        ClientDeEntity client = null;
        String salt = "";
        try {
            client = (ClientDeEntity) session.createCriteria(ClientDeEntity.class)
                    .add(Restrictions.and(Restrictions.or(Restrictions.eq("email", usernameOrEmail),
                            Restrictions.eq("username", usernameOrEmail)))).uniqueResult();
        } catch (Exception e) {
            throw new DALException(e);
        }

        if(client != null)
            salt = client.getSalt();

        return salt;
    }
}
