package dal.utilisation.orm;

import dal.dalexception.DALException;
import dal.entities.derby.ClientDeEntity;
import dal.entities.pgsql.ClientPgEntity;
import dal.ientites.IDALClientEntity;
import dal.orm.DerbyORM;
import dal.orm.PgORM;
import org.junit.Assert;
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
    void testRefactoringWithPg() throws DALException {
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
        IDALClientEntity clientTree = new ClientPgEntity();
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

        clientTree.setUsername("Tree");
        clientTree.setEmail("Tree");
        clientTree.setPassword("Tree");
        clientTree.setIsactivated(true);
        clientTree.setActivationkey("Tree");
        clientTree.setSalt("Tree");



        //-----------
        PgORM orm = new PgORM();
        orm.beginTransaction();
        orm.getClientRepository().addClient(clientOne);
        orm.getClientRepository().addClient(clientTwo);

        orm.commit();

        orm.beginTransaction();
        orm.getClientRepository().addClient(clientTree);
        orm.commit();
        //-----------




//        PgORM orm = new PgORM();
//        orm.beginTransaction();
//        orm.getClientRepository().addClient(clientOne);
//        orm.getClientRepository().addClient(clientTwo);


//        orm.rollback();
//        orm.beginTransaction();
//        orm.getClientRepository().addClient(clientTwo);
//        orm.commit();
//        orm.beginTransaction();
//        orm.getClientRepository().addClient(clientOne);

        //orm.commit();
       // orm.beginTransaction();

//        for(IDALClientEntity c : orm.getClientRepository().getClients())
//            orm.getClientRepository().delete(c.getId());
//        orm.commit();

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
    void testRefactoringWithDe() throws DALException {
        IDALClientEntity rollbacked = new ClientDeEntity();
        rollbacked.setUsername("trrr");
        rollbacked.setEmail("trrr");
        rollbacked.setPassword("trrr");
        rollbacked.setIsactivated(true);
        rollbacked.setActivationkey("trrr");
        rollbacked.setSalt("trrr");
//
        IDALClientEntity clientOne = new ClientDeEntity();
        IDALClientEntity clientTwo = new ClientDeEntity();
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


        DerbyORM orm = new DerbyORM();
        orm.beginTransaction();
        orm.getClientRepository().addClient(clientOne);
        orm.getClientRepository().addClient(clientTwo);
        orm.rollback();
        orm.beginTransaction();
        orm.getClientRepository().addClient(clientTwo);
        orm.commit();
        orm.beginTransaction();
        orm.getClientRepository().addClient(clientOne);
        orm.getClientRepository().addClient(rollbacked);

        orm.commit();
        orm.beginTransaction();
        Assert.assertTrue(orm.getClientRepository().mailExist(clientTwo.getEmail()));
        Assert.assertTrue(orm.getClientRepository().pseudoExist(clientOne.getUsername()));
        Assert.assertFalse(orm.getClientRepository().pseudoExist(clientOne.getUsername()+"blabla"));
        Assert.assertFalse(orm.getClientRepository().mailExist(clientOne.getEmail() + "vabd"));

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

}