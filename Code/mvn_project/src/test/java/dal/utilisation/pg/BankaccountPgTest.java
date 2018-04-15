package dal.utilisation.pg;

import bll.model.ClientModel;
import dal.dalexception.DALException;
import dal.entities.pgsql.BankaccountPgEntity;
import dal.entities.pgsql.ClientPgEntity;
import dal.ientites.IDALBankaccountEntity;
import dal.ientites.IDALClientEntity;
import dal.irepositories.IBankaccountRepository;
import dal.irepositories.IClientRepository;
import dal.repositories.pgsql.BankaccountPgRepository;
import dal.repositories.pgsql.ClientPgRepository;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class BankaccountPgTest {

   // public IBankaccountRepository bankaccountRepository;

    public IClientRepository clientRepository;
    public List<IDALClientEntity> listClients =  new ArrayList();
  //   public List<IDALBankaccountEntity> listBankAccounts = new ArrayList();

    @org.junit.Before
    public void setUp() throws Exception {

//
//        IDALBankaccountEntity bankaccountEntityOne = new BankaccountPgEntity();
//        IDALBankaccountEntity bankaccountEntityTwo = new BankaccountPgEntity();
//        IDALBankaccountEntity bankaccountEntityTree = new BankaccountPgEntity();
//        IDALBankaccountEntity bankaccountEntityFour = new BankaccountPgEntity();
//
//        bankaccountEntityOne.setClientId(((IDALClientEntity)clientRepository.getClients().toArray()[0]).getId());
//        bankaccountEntityOne.setAmount(100);
//        bankaccountEntityOne.setName("compte clientOne");
//        bankaccountEntityOne.setNamebank("bank yverdon");
//        bankaccountEntityOne.setTypeaccount("cmpte commun");
//        bankaccountEntityOne.setIsdefault(true);
//        bankaccountEntityOne.setIsvisible(true);
//
//        bankaccountEntityTwo.setClientId(((IDALClientEntity)clientRepository.getClients().toArray()[1]).getId());
//        bankaccountEntityTwo.setAmount(200);
//        bankaccountEntityTwo.setName("compte clientTwo");
//        bankaccountEntityTwo.setNamebank("bank yverdon");
//        bankaccountEntityTwo.setTypeaccount("cmpte commun");
//        bankaccountEntityTwo.setIsdefault(true);
//        bankaccountEntityTwo.setIsvisible(true);
//
//        bankaccountEntityTree.setClientId(((IDALClientEntity)clientRepository.getClients().toArray()[2]).getId());
//        bankaccountEntityTree.setAmount(300);
//        bankaccountEntityTree.setName("compte clientTree");
//        bankaccountEntityTree.setNamebank("bank yverdon");
//        bankaccountEntityTree.setTypeaccount("cmpte commun");
//        bankaccountEntityTree.setIsdefault(true);
//        bankaccountEntityTree.setIsvisible(true);
//
//        bankaccountEntityFour.setClientId(((IDALClientEntity)clientRepository.getClients().toArray()[3]).getId());
//        bankaccountEntityFour.setAmount(400);
//        bankaccountEntityFour.setName("compte clientTree");
//        bankaccountEntityFour.setNamebank("bank yverdon");
//        bankaccountEntityFour.setTypeaccount("cmpte commun");
//        bankaccountEntityFour.setIsdefault(true);
//        bankaccountEntityFour.setIsvisible(true);
//
//        bankaccountRepository = new BankaccountPgRepository();
//        Collection<IDALBankaccountEntity> liBankaccount = listBankAccounts;
//        Iterator<IDALBankaccountEntity> itBankaccount = liBankaccount.iterator();
//        while (itBankaccount.hasNext()) {
//            bankaccountRepository.addBankaccount(itBankaccount.next());
//        }


    }

    @org.junit.After
    public void tearDown() throws Exception {

//        bankaccountRepository = new BankaccountPgRepository();
//        Collection<IDALBankaccountEntity> liBankaccount = bankaccountRepository.getBankaccounts();
//        Iterator<IDALBankaccountEntity> itBankaccount = liBankaccount.iterator();
//        while (itBankaccount.hasNext()) {
//            bankaccountRepository.delete(itBankaccount.next().getId());
//        }
//
//
        clientRepository = new ClientPgRepository();
        Collection<IDALClientEntity> liClient = clientRepository.getClients();
        Iterator<IDALClientEntity> it = liClient.iterator();
        while (it.hasNext()) {
            clientRepository.delete(it.next().getId());
        }
    }

    @org.junit.Test
    public void getClient() throws DALException {

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

        clientRepository = new ClientPgRepository();

        clientRepository.addClient(clientOne);
        clientRepository.addClient(clientTwo);
        clientRepository.addClient(clientTree);
        int i = 0;
//        clientRepository = new ClientPgRepository();
//        for (IDALClientEntity cli : listClients){
//            System.out.println(cli.toString());
//            clientRepository.addClient(cli);
//        }

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
