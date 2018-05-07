package dal.irepositories;

import dal.dalexception.DALException;
import dal.ientites.IDALClientEntity;

import java.util.Collection;
import java.util.List;

/**
 * IClientRepository give the access methods for handle the client into persistence
 */
public interface IClientRepository {

    /**
     * Retreave a client by client id
     *
     * @param id of the client
     * @return IDALClientEntity the client
     * @throws DALException
     */
    public IDALClientEntity getClient(int id) throws DALException;

    /**
     * Retrieve all clients
     *
     * @return List<IDALClientEntity> the list of clients
     * @throws DALException
     */
    public List<IDALClientEntity> getClients() throws DALException;

    /**
     * Update client
     *
     * @param client the client to update
     * @throws DALException
     */
    public void update(IDALClientEntity client) throws DALException;

    /**
     * Add client
     *
     * @param client the category to add
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
     * @return boolean true if exists, false if doesn't exist
     * @throws DALException
     */
    public boolean pseudoExist(String username) throws DALException;

    /**
     * test if the mail exist
     * @param email of the user
     * @return boolean true if the mail exists, false if doesn't exist
     * @throws DALException
     */
    public boolean mailExist(String email) throws DALException;

    /**
     * retrieve a user if username and password already exists in persistence
     * @param usernameOrEmail the username or email of the user
     * @param password password of the user
     * @return IDALClientEntity client
     * @throws DALException
     */
    public IDALClientEntity checkUserAndPassword(String usernameOrEmail, String password) throws DALException;

    /**
     * retrieve the salt by the username or the email
     * @param usernameOrEmail
     * @return String the salt corresponding to the username or email
     * @throws DALException
     */
    public String retriveSaltByUserLogin(String usernameOrEmail) throws DALException;

}
