//package dal.repositories.derby;
//
//import dal.dalexception.DALException;
//import dal.ientites.IDALClientEntity;
//import dal.irepositories.IClientRepository;
//import dal.util.HibernateUtil;
//import org.hibernate.HibernateException;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import org.hibernate.criterion.Restrictions;
//
//import java.util.Collection;
//
//public class ClientDeRepository implements IClientRepository {
//    public IDALClientEntity getClient(int id) {
//        Session session = HibernateUtil.getPGSessionFactory().openSession();
//        Transaction tr = null;
//        ClientDeEntity client = null;
//        try {
//
//            tr = session.beginTransaction();
//
//            client = (ClientDeEntity) session.createCriteria(ClientDeEntity.class)
//                    .add(Restrictions.eq("id", id))
//                    .uniqueResult();
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
//                System.out.println(he.toString());
//            }
//        }
//        return client;
//    }
//
//    public Collection<IDALClientEntity> getClients() {
//        return null;
//    }
//
//    public Collection<IDALClientEntity> getClients(int page, String sort) {
//        return null;
//    }
//
//    public void addClient(IDALClientEntity client) throws DALException {
//        Session session = HibernateUtil.getPGSessionFactory().openSession();
//        Transaction tr = null;
//
//        ClientDeEntity newClient = null;
//        if (client.getClass() == ClientDeEntity.class)
//            newClient = (ClientDeEntity) client;
//        else
//            throw new DALException();
//        try {
//            tr = session.beginTransaction();
//            session.save(client);
//            tr.commit();
//        } catch (RuntimeException e) {
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
//                System.out.println(he.toString());
//            }
//        }
//    }
//
//    public void update(IDALClientEntity client) {
//
//    }
//
//
//    public void delete(int id) {
//
//    }
//}
