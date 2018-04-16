package dal.repositories.derby;

import dal.dalexception.DALException;
import dal.entities.derby.BankaccountDeEntity;
import dal.ientites.IDALBankaccountEntity;
import dal.irepositories.IBankaccountRepository;
import dal.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class BankaccountDeRepository implements IBankaccountRepository {


    @Override
    public IDALBankaccountEntity getBankaccount(int id) throws DALException {
        Session session = HibernateUtil.getPGSessionFactory().openSession();
        Transaction tr = null;
        BankaccountDeEntity bankaccont = null;
        try {

            tr = session.beginTransaction();

            bankaccont = (BankaccountDeEntity) session.createCriteria(BankaccountDeEntity.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();

            tr.commit();
        } catch (Exception e) {

            try {
                tr.rollback();
            } catch (HibernateException he) {
                throw he;
            }
        } finally {
            try {
                //session.flush();
                session.close();
            } catch (HibernateException he) {
                System.out.println(he.toString());
            }
        }
        return bankaccont;
    }

    @Override
    public List<IDALBankaccountEntity> getBankaccounts() throws DALException {
        Session session = HibernateUtil.getPGSessionFactory().openSession();
        Transaction tr = null;
        List<IDALBankaccountEntity> bankaccounts = null;
        try {
            tr = session.beginTransaction();

            bankaccounts = session.createQuery("from BankaccountPgEntity").list();

            tr.commit();
        } catch (Exception e) {
            try {
                tr.rollback();
            } catch (HibernateException he) {
                throw he;
            }
        } finally {
            try {
                //session.flush();
                session.close();
            } catch (HibernateException he) {
                System.out.println(he.toString());
            }
        }
        return bankaccounts;
    }

    @Override
    public List<IDALBankaccountEntity> getBankaccounts(int page, String sort) {
        return null;
    }

    @Override
    public void update(IDALBankaccountEntity bankaccount) throws DALException {

        Session session = HibernateUtil.getPGSessionFactory().openSession();
        Transaction tr = null;
        BankaccountDeEntity bankaccountPG = null;
        if (bankaccount.getClass() == BankaccountDeEntity.class)
            bankaccountPG = (BankaccountDeEntity) bankaccount;
        else
            throw new DALException();

        try {

            tr = session.beginTransaction();

            session.update(bankaccountPG);

            tr.commit();
        } catch (Exception e) {
            try {
                tr.rollback();
            } catch (HibernateException he) {
                throw he;
            }
        } finally {
            try {
                //session.flush();
                session.close();
            } catch (HibernateException he) {
                System.out.println(he.toString());
            }
        }
    }

    @Override
    public void addBankaccount(IDALBankaccountEntity bankaccount) throws DALException {
        Session session = HibernateUtil.getPGSessionFactory().openSession();
        Transaction tr = null;

        IDALBankaccountEntity newbankaccount = null;
        if (bankaccount.getClass() == BankaccountDeEntity.class)
            newbankaccount = (BankaccountDeEntity) bankaccount;
        else
            throw new DALException();

        try {
            tr = session.beginTransaction();
            session.save(newbankaccount);
            tr.commit();
        } catch (RuntimeException e) {
            try {
                tr.rollback();
            } catch (HibernateException he) {
                throw he;
            }
        } finally {
            try {
                //session.flush();
                session.close();
            } catch (HibernateException he) {
                System.out.println(he.toString());
            }
        }

    }

    public void delete(int id) {
        Session session = HibernateUtil.getPGSessionFactory().openSession();
        Transaction tr = null;
        IDALBankaccountEntity bankaccount = null;
        try {

            tr = session.beginTransaction();

            bankaccount = (BankaccountDeEntity) session.createCriteria(BankaccountDeEntity.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();

            session.delete(bankaccount);

            tr.commit();
        } catch (Exception e) {
            try {
                tr.rollback();
            } catch (HibernateException he) {
                throw he;
            }
        } finally {
            try {
                //session.flush();
                session.close();
            } catch (HibernateException he) {
                System.out.println(he.toString());
            }
        }

    }
}
