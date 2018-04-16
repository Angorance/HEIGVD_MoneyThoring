//package dal.utilisation.pg;
//import bll.mappers.DAL.DALClientMapper;
//import bll.model.ClientModel;
//import dal.dalexception.DALException;
//import dal.entities.pgsql.ClientPgEntity;
//import dal.ientites.IDALClientEntity;
//import dal.irepositories.IClientRepository;
//import dal.repositories.pgsql.ClientPgRepository;
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.*;
//
//public class ClientPgMappedTest {
//
//    public IClientRepository clientRepository;
//
//    public List<ClientModel> listClients =  new ArrayList();
//
//
//    @org.junit.Before
//    public void setUp() throws Exception {
//        ClientModel clientOne = new ClientModel();
//        ClientModel clientTwo = new ClientModel();
//        ClientModel clientTree = new ClientModel();
//        ClientModel clientFour = new ClientModel();
//
//
//
//        clientOne.setUsername("One");
//        clientOne.setEmail("One");
//        clientOne.setPassword("One");
//        clientOne.setActivated(true);
//        clientOne.setKey("One");
//        clientOne.setSalt("One");
//
//        clientTwo.setUsername("Two");
//        clientTwo.setEmail("Two");
//        clientTwo.setPassword("Two");
//        clientTwo.setActivated(true);
//        clientTwo.setKey("Two");
//        clientTwo.setSalt("Two");
//
//        clientTree.setUsername("Tree");
//        clientTree.setEmail("Tree");
//        clientTree.setPassword("Tree");
//        clientTree.setActivated(true);
//        clientTree.setKey("Tree");
//        clientTree.setSalt("Tree");
//
//        clientFour.setUsername("One");
//        clientFour.setEmail("One");
//        clientFour.setPassword("One");
//        clientFour.setActivated(true);
//        clientFour.setKey("One");
//        clientFour.setSalt("One");
//
//        listClients.add(clientOne);
//        listClients.add(clientTwo);
//        listClients.add(clientTree);
//        listClients.add(clientFour);
//    }
//
//    @org.junit.After
//    public void tearDown() throws Exception {
//    }
//
//    @org.junit.Test
//    public void getClient() throws DALException {
//        clientRepository = new ClientPgRepository();
//        ClientModel client = DALClientMapper.toBo(clientRepository.getClient(1));
//
//        Assert.assertNull(client);
//    }
//
//    @org.junit.Test
//    public void addClient() throws DALException {
//        clientRepository = new ClientPgRepository();
//        clientRepository.addClient(DALClientMapper.toDboPG(listClients.get(1)));
//        ClientModel client = DALClientMapper.toBo(clientRepository.getClient(0));
//        Assert.assertNotNull(client);
//    }
//
//    @org.junit.Test
//    public void getClients() {
//    }
//
//    @org.junit.Test
//    public void getClients1() {
//    }
//
//    @org.junit.Test
//    public void update() throws DALException {
//        clientRepository = new ClientPgRepository();
//        ClientModel client = DALClientMapper.toBo(clientRepository.getClient(0));
//        client.setUsername("updated");
//        clientRepository.update(DALClientMapper.toDboPG(client));
//        client = DALClientMapper.toBo(clientRepository.getClient(0));
//        Assert.assertNull(client);
//
//    }
//
//    @org.junit.Test
//    public void delete() {
//
//    }
//}
