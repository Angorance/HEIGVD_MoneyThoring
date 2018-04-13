package dal.utilisation.pg;
import dal.entities.pgsql.ClientPgEntity;
import dal.ientites.IDALClientEntity;
import dal.irepositories.IClientRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ClientMappedTest {

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
    public void getClient() {
    }

    @org.junit.Test
    public void addClient() {
    }

    @org.junit.Test
    public void getClients() {
    }

    @org.junit.Test
    public void getClients1() {
    }

    @org.junit.Test
    public void update() {
    }

    @org.junit.Test
    public void delete() {
    }
}
