//package dal.utilisation.derby;
//
//import bll.mappers.DAL.DALClientMapper;
//import bll.model.ClientModel;
//import dal.dalexception.DALException;
//import dal.entities.derby.BankaccountDeEntity;
//import dal.entities.derby.ClientDeEntity;
//import dal.ientites.IDALBankaccountEntity;
//import dal.ientites.IDALClientEntity;
//import dal.irepositories.IBankaccountRepository;
//import dal.irepositories.IClientRepository;
//import dal.repositories.derby.BankaccountDeRepository;
//import dal.repositories.derby.ClientDeRepository;
//import dal.util.HibernateUtil;
//import org.hibernate.HibernateException;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class BankaccountDeTest {
//
//    // public IBankaccountRepository bankaccountRepository;
//
//    public IClientRepository clientRepository;
//    public IBankaccountRepository bankaccountRepository;
//    public List<IDALClientEntity> listClients = new ArrayList();
//    public List<IDALBankaccountEntity> listBankAccounts = new ArrayList();
//
//    @org.junit.Before
//    public void setUp() throws Exception {
//
//        IDALClientEntity clientOne = new ClientDeEntity();
//        IDALClientEntity clientTwo = new ClientDeEntity();
//        IDALClientEntity clientTree = new ClientDeEntity();
//        IDALClientEntity clientFour = new ClientDeEntity();
//
//        clientOne.setUsername("One");
//        clientOne.setEmail("One");
//        clientOne.setPassword("One");
//        clientOne.setIsactivated(true);
//        clientOne.setActivationkey("One");
//        clientOne.setSalt("One");
//
//        clientTwo.setUsername("Two");
//        clientTwo.setEmail("Two");
//        clientTwo.setPassword("Two");
//        clientTwo.setIsactivated(true);
//        clientTwo.setActivationkey("Two");
//        clientTwo.setSalt("Two");
//
//        clientTree.setUsername("Tree");
//        clientTree.setEmail("Tree");
//        clientTree.setPassword("Tree");
//        clientTree.setIsactivated(true);
//        clientTree.setActivationkey("Tree");
//        clientTree.setSalt("Tree");
//
//        clientFour.setUsername("Four");
//        clientFour.setEmail("Four");
//        clientFour.setPassword("Four");
//        clientFour.setIsactivated(true);
//        clientFour.setActivationkey("Four");
//        clientFour.setSalt("Four");
//
//        listClients.add(clientOne);
//        listClients.add(clientTwo);
//        listClients.add(clientTree);
//        listClients.add(clientFour);
//
//
//        clientRepository = new ClientDeRepository();
//        for (IDALClientEntity cli : listClients) {
//            System.out.println(cli.toString());
//            clientRepository.addClient(cli);
//        }
//
//
//        IDALBankaccountEntity bankaccountEntityOne = new BankaccountDeEntity();
//        IDALBankaccountEntity bankaccountEntityTwo = new BankaccountDeEntity();
//        IDALBankaccountEntity bankaccountEntityTree = new BankaccountDeEntity();
//        IDALBankaccountEntity bankaccountEntityFour = new BankaccountDeEntity();
//
//        bankaccountEntityOne.setClientId(((IDALClientEntity) clientRepository.getClients().get(0)).getId());
//        bankaccountEntityOne.setAmount(100);
//        bankaccountEntityOne.setName("compte clientOnsde");
//        bankaccountEntityOne.setNamebank("bank yvsdfaerdon");
//        bankaccountEntityOne.setTypeaccount("cmptasdfe commun");
//        bankaccountEntityOne.setIsdefault(true);
//        bankaccountEntityOne.setIsvisible(true);
//
//        bankaccountEntityTwo.setClientId(((IDALClientEntity) clientRepository.getClients().get(1)).getId());
//        bankaccountEntityTwo.setAmount(200);
//        bankaccountEntityTwo.setName("compte clieqwfntTwo");
//        bankaccountEntityTwo.setNamebank("bank reqryverdon");
//        bankaccountEntityTwo.setTypeaccount("cmwergpte commun");
//        bankaccountEntityTwo.setIsdefault(true);
//        bankaccountEntityTwo.setIsvisible(true);
//
//        bankaccountEntityTree.setClientId(((IDALClientEntity) clientRepository.getClients().get(2)).getId());
//        bankaccountEntityTree.setAmount(300);
//        bankaccountEntityTree.setName("compte clientTree");
//        bankaccountEntityTree.setNamebank("bank yverdon");
//        bankaccountEntityTree.setTypeaccount("cmpte commun");
//        bankaccountEntityTree.setIsdefault(true);
//        bankaccountEntityTree.setIsvisible(true);
//
//        bankaccountEntityFour.setClientId(((IDALClientEntity) clientRepository.getClients().get(2)).getId());
//        bankaccountEntityFour.setAmount(400);
//        bankaccountEntityFour.setName("compte clientTree");
//        bankaccountEntityFour.setNamebank("bank yverdon");
//        bankaccountEntityFour.setTypeaccount("cmpte commun");
//        bankaccountEntityFour.setIsdefault(true);
//        bankaccountEntityFour.setIsvisible(true);
//
//        listBankAccounts.add(bankaccountEntityOne);
//        listBankAccounts.add(bankaccountEntityTwo);
//        listBankAccounts.add(bankaccountEntityTree);
//        listBankAccounts.add(bankaccountEntityFour);
//
//
//        bankaccountRepository = new BankaccountDeRepository();
//        for (IDALBankaccountEntity cli : listBankAccounts) {
//            bankaccountRepository.addBankaccount(cli);
//        }
//
//
//    }
//
//    @org.junit.After
//    public void tearDown() throws Exception {
//
//        bankaccountRepository = new BankaccountDeRepository();
//        for (IDALBankaccountEntity cli : bankaccountRepository.getBankaccounts()) {
//            bankaccountRepository.delete(cli.getId());
//        }
//
//        clientRepository = new ClientDeRepository();
//        for (IDALClientEntity cli : clientRepository.getClients()) {
//            bankaccountRepository.delete(cli.getId());
//        }
//    }
//
//    @org.junit.Test
//    public void getClient() throws DALException {
//        int i = 0;
//
//    }
//
//    @org.junit.Test
//    public void getClients() throws DALException {
//        Session session = HibernateUtil.getDeSessionFactory().openSession();
//        Transaction tr = null;
//        List<IDALClientEntity> clients = null;
//        try {
//            tr = session.beginTransaction();
//
//            clients = session.createQuery("from ClientDeEntity").list();
//            IDALClientEntity cli = clients.get(1);
//            DALClientMapper.toDboDe(DALClientMapper.toBo(cli));
//
//
//            int i =  ((ClientDeEntity)cli).getBankaccountsById().size();
//             IBankaccountRepository rpi = new BankaccountDeRepository();7
//
//
//
//
//            tr.commit();
//        } catch (Exception e) {
//            try {
//                tr.rollback();
//            } catch (HibernateException he) {
//                throw he;
//            }
//        } finally {
//            try {
//                //session.flush();
//                session.close();
//            } catch (HibernateException he) {
//                throw new DALException(he);
//            }
//        }
//
//    }
//
//    @org.junit.Test
//    public void getClients1() {
//    }
//
//    @org.junit.Test
//    public void addClient() throws DALException {
//    }
//
//    @org.junit.Test
//    public void update() throws DALException {
//
//    }
//
//    @org.junit.Test
//    public void delete() throws DALException {
//    }
//}
