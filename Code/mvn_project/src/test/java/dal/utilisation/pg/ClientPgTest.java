package dal.utilisation.pg;

import dal.dalexception.DALException;
import dal.entities.pgsql.ClientPgEntity;
import dal.ientites.IDALClientEntity;
import dal.irepositories.IClientRepository;
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
        IDALClientEntity clientOne = new ClientPgEntity();
        IDALClientEntity clientTwo = new ClientPgEntity();
        IDALClientEntity clientTree = new ClientPgEntity();
        IDALClientEntity clientFour = new ClientPgEntity();



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

        clientFour.setUsername("One");
        clientFour.setEmail("One");
        clientFour.setPassword("One");
        clientFour.setIsactivated(true);
        clientFour.setActivationkey("One");
        clientFour.setSalt("One");

        listClients.add(clientOne);
        listClients.add(clientTwo);
        listClients.add(clientTree);
        listClients.add(clientFour);
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void getClient() throws DALException {
        clientRepository = new ClientPgRepository();
        IDALClientEntity client = clientRepository.getClient(1);
        Assert.assertNull(client);

    }

    @org.junit.Test
    public void getClients() throws DALException {
        clientRepository = new ClientPgRepository();
        int size = clientRepository.getClients().size();
        int i = 1;
    }

    @org.junit.Test
    public void getClients1() {
    }

    @org.junit.Test
    public void addClient() throws DALException {
        clientRepository = new ClientPgRepository();
        clientRepository.addClient(listClients.get(1));
        IDALClientEntity client = clientRepository.getClient(0);
        Assert.assertNotNull(client);

    }

    @org.junit.Test
    public void update() throws DALException {

        clientRepository = new ClientPgRepository();
        IDALClientEntity client = clientRepository.getClient(0);
        client.setUsername("updated");
        clientRepository.update(client);


    }

    @org.junit.Test
    public void delete() throws DALException {
        clientRepository = new ClientPgRepository();
        clientRepository.delete(0);
    }
}
