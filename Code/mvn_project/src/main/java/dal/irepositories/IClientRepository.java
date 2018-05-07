package dal.irepositories;

import dal.dalexception.DALException;
import dal.ientites.IDALClientEntity;

import java.util.Collection;
import java.util.List;

/**
 * IClientRepository give the access methodes for handle the client into persistence
 */
public interface IClientRepository {

    /**
     * Retreave a client by cleint id
     *
     * @param id of the client
     * @return IDALClientEntity the client
     * @throws DALException
     */
    public IDALClientEntity getClient(int id) throws DALException;

    /**
     * Retreave all clients
     *
     * @return List<IDALClientEntity> the list of clients
     * @throws DALException
     */
    public List<IDALClientEntity> getClients() throws DALException;

    /**
     * Update client
     *
     * @param client the client would'you update
     * @throws DALException
     */
    public void update(IDALClientEntity client) throws DALException;

    /**
     * Add add client
     *
     * @param client the category would'you add
     * @throws DALException
     */
    public void addClient(IDALClientEntity client) throws DALException;

    /**
     * delete client by id
     * @param id of the client
     * @throws DALException
     */
    public void delete(int id) throws DALException;

    /**
     * test if the pseudo exist
     * @param username of the user
     * @return boolean true if exist, false if dosen't exist
     * @throws DALException
     */
    public boolean pseudoExist(String username) throws DALException;

    /**
     * test if the mail exist
     * @param email of the user
     * @return boolean true if the mail existe, false if dosen't exist
     * @throws DALException
     */
    public boolean mailExist(String email) throws DALException;

    /**
     * retreave a user if username and pasowrd if allerady exist in persisence
     * @param usernameOrEmail the username or email of the user
     * @param password password of the user
     * @return IDALClientEntity client
     * @throws DALException
     */
    public IDALClientEntity checkUserAndPassword(String usernameOrEmail, String password) throws DALException;

    /**
     * test if the user is activated
     * @param usernameOrEmail the username or email of the user
     * @param password the password of the user
     * @return boolean true if the user is activated, false if se user is desactivated
     * @throws DALException
     */
    public boolean isActivated(String usernameOrEmail, String password) throws DALException;

    /**
     * retreave the salt by the username or the email
     * @param usernameOrEmail
     * @return String the salt corresponding at the username or email
     * @throws DALException
     */
    public String retriveSaltByUserLogin(String usernameOrEmail) throws DALException;

}
