//package dal.utilisation.derby;
//
//import dal.dalexception.DALException;
//import dal.ientites.IDALClientEntity;
//import dal.irepositories.IClientRepository;
//import dal.repositories.derby.ClientDeRepository;
//import dal.repositories.pgsql.ClientPgRepository;
//
//public class ClientTest {
//
//    public IClientRepository clientRepository;
//
//    @org.junit.Before
//    public void setUp() throws Exception {
//        clientRepository = new ClientDeRepository();
//    }
//
//    @org.junit.After
//    public void tearDown() throws Exception {
//    }
//
//    @org.junit.Test
//    public void getClient() throws DALException {
//       IDALClientEntity client = clientRepository.getClient(0);
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
//    public void addClient() {
//    }
//
//    @org.junit.Test
//    public void update() {
//    }
//
//    @org.junit.Test
//    public void delete() {
//    }
//}
