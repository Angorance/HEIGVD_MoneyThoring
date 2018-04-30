package dal.utilisation.pg;

import dal.dalexception.DALException;
import dal.entities.pgsql.ClientPgEntity;
import dal.ientites.IDALClientEntity;
import dal.irepositories.IClientRepository;
import dal.orm.IORM;
import dal.orm.PgORM;
import dal.repositories.pgsql.ClientPgRepository;
import org.junit.Assert;
import org.postgresql.sspi.ISSPIClient;

import java.util.ArrayList;
import java.util.List;

public class ClientPgTest {

    public IClientRepository clientRepository;

    public List<IDALClientEntity> listClients =  new ArrayList();

    @org.junit.Before
    public void setUp() throws Exception {

    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void getClient() throws DALException {


    }

    @org.junit.Test
    public void getClients() throws DALException {

    }

    @org.junit.Test
    public void getClients1() {
    }

    @org.junit.Test
    public void addClient() throws DALException {


    }

    @org.junit.Test
    public void update() throws DALException {

        IORM orm = new PgORM();

        orm.beginTransaction();
        List<IDALClientEntity> clients = orm.getClientRepository().getClients();
        Assert.assertTrue(orm.getClientRepository().checkUserAndPassword(clients.get(0).getUsername(), clients.get(0).getPassword()));

    }

    @org.junit.Test
    public void delete() throws DALException {
    }
}
