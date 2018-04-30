package dal.repositories.pgsql;

import dal.dalexception.DALException;
import dal.entities.pgsql.DebtPgEntity;
import dal.ientites.IDALDebtEntity;
import dal.irepositories.IDebtRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DebtPgRepository implements IDebtRepository {
    private Session session;
    private Transaction transaction;
    public DebtPgRepository(Session session, Transaction transaction) {
        this.session = session;
        this.transaction = transaction;
    }


    @Override
    public IDALDebtEntity getDebt(int id) throws DALException {
        DebtPgEntity Debt = null;

        try {
            Debt = (DebtPgEntity) session.createCriteria(DebtPgEntity.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();
        } catch (Exception e) {
            throw new DALException(e);
        }

        return Debt;
    }



    @Override
    public List<IDALDebtEntity> getDebts() throws DALException {

        List<IDALDebtEntity> Debts = null;
        try {
            Debts = session.createQuery("from DebtPgEntity").list();


        } catch (Exception e) {
            throw new DALException(e);
        }
        return Debts;
    }


    @Override
    public void update(IDALDebtEntity Debt) throws DALException {
        DebtPgEntity DebtPg = null;
        if (Debt.getClass() == DebtPgEntity.class)
            DebtPg = (DebtPgEntity) Debt;
        else
            throw new DALException();

        try {


            session.update(DebtPg);


        } catch (Exception e) {
            throw new DALException(e);
        }
    }

    @Override
    public void addDebt(IDALDebtEntity Debt) throws DALException {
        DebtPgEntity newDebt = null;
        if (newDebt.getClass() == DebtPgEntity.class)
            newDebt = (DebtPgEntity) newDebt;
        else
            throw new DALException();

        try {
            session.save(newDebt);
        } catch (Exception e) {
            throw new DALException(e);
        }
    }

    @Override
    public void delete(int id) throws DALException {
        IDALDebtEntity Debt = null;
        try {
            Debt = (DebtPgEntity) session.createCriteria(DebtPgEntity.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();

            session.delete(Debt);
        } catch (Exception e) {
            throw new DALException(e);
        }
    }
}
