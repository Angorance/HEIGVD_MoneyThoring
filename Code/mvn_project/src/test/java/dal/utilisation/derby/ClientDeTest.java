package dal.utilisation.derby;

import dal.dalexception.DALException;
import dal.entities.derby.ClientDeEntity;
import dal.ientites.IDALClientEntity;
import dal.irepositories.IClientRepository;
import dal.repositories.derby.ClientDeRepository;
import dal.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientDeTest {

    public IClientRepository clientRepository;

    public List<IDALClientEntity> listClients =  new ArrayList();

    @org.junit.Before
    public void setUp() throws Exception {
        IDALClientEntity clientOne = new ClientDeEntity();
        IDALClientEntity clientTwo = new ClientDeEntity();
        IDALClientEntity clientTree = new ClientDeEntity();
        IDALClientEntity clientFour = new ClientDeEntity();



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

        clientRepository = new ClientDeRepository();
        clientRepository.addClient(clientOne);
        clientRepository.addClient(clientTwo);
        clientRepository.addClient(clientTree);
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }


    @org.junit.Test
    public void getClient() throws DALException {
//        clientRepository = new ClientDeRepository();
//        IDALClientEntity client = clientRepository.getClient(1);
//        Assert.assertNull(client);

    }

    @org.junit.Test
    public void getClients() throws DALException {
        clientRepository = new ClientDeRepository();
        int size = clientRepository.getClients().size();
        int i = 1;
    }

    @org.junit.Test
    public void getClients1() {
    }

    @org.junit.Test
    public void addClient() throws DALException {
        clientRepository = new ClientDeRepository();
        clientRepository.addClient(listClients.get(3));
        Assert.assertNotEquals(clientRepository.getClients().size(), 0);


    }

    @org.junit.Test
    public void update() throws DALException {

        clientRepository = new ClientDeRepository();
        IDALClientEntity client = (IDALClientEntity) clientRepository.getClients().toArray()[0];
        client.setUsername("updated");
        clientRepository.update(client);


    }

    @org.junit.Test
    public void delete() throws DALException {
        clientRepository = new ClientDeRepository();
        clientRepository.delete(800);
    }
}
