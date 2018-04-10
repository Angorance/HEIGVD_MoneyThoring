package mt.repositories.derby;

import mt.entities.derby.ClientEntity;
import mt.repositories.derby.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ClientRepository {

        public void addClient( ClientEntity client) {
            Transaction trns = null;
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                trns = session.beginTransaction();
                session.save(client);
                session.getTransaction().commit();
            } catch (RuntimeException e) {
                if (trns != null) {
                    trns.rollback();
                }
                e.printStackTrace();
            } finally {
                session.flush();
                session.close();
            }
        }

        public void deleteClient(int clientId) {
            Transaction trns = null;
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                trns = session.beginTransaction();
                ClientEntity client = (ClientEntity) session.load(ClientEntity.class, new Integer(clientId));
                session.delete(client);
                session.getTransaction().commit();
            } catch (RuntimeException e) {
                if (trns != null) {
                    trns.rollback();
                }
                e.printStackTrace();
            } finally {
                session.flush();
                session.close();
            }
        }

        public void updateClient(ClientEntity client) {
            Transaction trns = null;
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                trns = session.beginTransaction();
                session.update(client);
                session.getTransaction().commit();
            } catch (RuntimeException e) {
                if (trns != null) {
                    trns.rollback();
                }
                e.printStackTrace();
            } finally {
                session.flush();
                session.close();
            }
        }

        public List<ClientEntity> getAllClient() {
            List<ClientEntity> clients = new ArrayList<ClientEntity>();
            Transaction trns = null;
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                trns = session.beginTransaction();
                clients = session.createQuery("from ClientEntity ").list();
            } catch (RuntimeException e) {
                e.printStackTrace();
            } finally {
                session.flush();
                session.close();
            }
            return clients;
        }

        public ClientEntity getClientById(int clientId) {
            ClientEntity client = null;
            Transaction trns = null;
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                trns = session.beginTransaction();
                String queryString = "from ClientEntity where id = :id";
                Query query = session.createQuery(queryString);
                query.setInteger("id", clientId);
                client = (ClientEntity) query.uniqueResult();
            } catch (RuntimeException e) {
                e.printStackTrace();
            } finally {
                session.flush();
                session.close();
            }
            return client;
        }


        public static void exempleUtilisation(){
            ClientRepository repo = new ClientRepository();


            ClientEntity client = new ClientEntity();
            client.setId(2);
            client.setEmail("fDaniel@mt.com");
            client.setIsactivated(true);
            client.setPassword("fpwd");
            client.setSalt("fsalt");
            client.setUsername("fdaniboune");
            client.setActivationkey("factivationKey");
            client.setEmail("fdaniel@example.com");
            repo.addClient(client);

/*
            client.setEmail("daniel@updatedJdbc.com");
            client.setId(1);
            repo.updateClient(client);


            for (ClientEntity iter : repo.getAllClient()) {
                System.out.println(iter);
            }
            System.out.println(repo.getClientById(8));

            try {
                HibernateUtil.getSessionFactory().close();
            } catch (Exception e) {

                e.printStackTrace();
            }*/
        }



}
