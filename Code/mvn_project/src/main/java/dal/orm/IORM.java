package dal.orm;

import dal.dalexception.DALException;
import dal.irepositories.IBankaccountRepository;
import dal.irepositories.ICategoryRepository;
import dal.irepositories.IClientRepository;

public interface IORM {
    /**
     * Construct an single instance of clientrepositoriy and return it
     * @return an instance of IClientRepository
     */
    IClientRepository getClientRepository();

    /**
     * Construct an single instance of clientrepositoriy and return it
     * @return an instance of IBankaccountRepository
     */
    IBankaccountRepository getBankaccountRepository();

    /**
     * Construct an single instance of categoryrepostiory and return it
     * @return an instance of ICategoryRepository
     */
    ICategoryRepository getCategoryRepository();

    /**
     * begin a transaction shared bitween all repositories
     * @throws DALException
     */
    void beginTransaction() throws DALException;

    /**
     * rollback transaction shared bitween all repositories
     * @throws DALException
     */
    void rollback() throws DALException;

    /**
     * commit transaction shared bitween all repositories
     * @throws DALException
     */
    void commit() throws DALException;


}
