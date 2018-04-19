package dal.utilisation.orm;

import dal.dalexception.DALException;
import dal.entities.pgsql.ClientPgEntity;
import dal.ientites.IDALClientEntity;
import dal.orm.PgORM;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PgORMTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getClientRepository() {

    }

    @Test
    void getBankaccountRepository() {
    }

    @Test
    void openSession() throws DALException {
//        IDALClientEntity rollbacked = new ClientPgEntity();
//        rollbacked.setUsername("rollbacked");
//        rollbacked.setEmail("rollbacked");
//        rollbacked.setPassword("rollbacked");
//        rollbacked.setIsactivated(true);
//        rollbacked.setActivationkey("rollbacked");
//        rollbacked.setSalt("rollbacked");
//
        IDALClientEntity clientOne = new ClientPgEntity();
        IDALClientEntity clientTwo = new ClientPgEntity();
        clientOne.setUsername("One");
        clientOne.setEmail("One");
        clientOne.setPassword("One");
        clientOne.setIsactivated(true);
        clientOne.setActivationkey("One");
        clientOne.setSalt("One");

        clientTwo.setUsername("Two");
        clientTwo.setEmail("Two");
        clientTwo.setPassword("Two");
        clientTwo.setIsactivated(true);
        clientTwo.setActivationkey("Two");
        clientTwo.setSalt("Two");


        PgORM orm = new PgORM();
        orm.beginTransaction();
        orm.getClientRepository().addClient(clientOne);
        orm.getClientRepository().addClient(clientTwo);
        orm.rollback();
        orm.beginTransaction();
        orm.getClientRepository().addClient(clientTwo);
        orm.commit();
        orm.beginTransaction();
        orm.getClientRepository().addClient(clientOne);

        orm.commit();
        orm.beginTransaction();

        for(IDALClientEntity c : orm.getClientRepository().getClients())
            orm.getClientRepository().delete(c.getId());
        orm.commit();

//        orm.beginTransaction();
//        orm.getClientRepository().addClient(clientOne);
//        orm.commit();
//        int i = 5;





//    orm.beginTransaction();
//        int i = orm.getClientRepository().getClients().size();
//        orm.getClientRepository().addClient(rollbacked);
//        orm.rollback();
//        orm.beginTransaction();
//        orm.getClientRepository().addClient(clientOne);
//        orm.getClientRepository().addClient(clientTwo);
//        orm.commit();
//        orm.sessionClose();
//        orm.beginTransaction();
//        List<IDALClientEntity> lists = orm.getClientRepository().getClients();
//        for(IDALClientEntity c : lists)
//            orm.getClientRepository().delete(c.getId());
//        orm.commit();

    }

    @Test
    void beginTransaction() {
    }

    @Test
    void rollback() {
    }

    @Test
    void commit() {
    }

}