package dal.repositories.derby;


import dal.dalexception.DALException;
import dal.entities.derby.DebtDeEntity;
import dal.ientites.IDALDebtEntity;
import dal.irepositories.IDebtRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DebtDeRepository implements IDebtRepository {

    private Session session;
    private Transaction transaction;

    public DebtDeRepository(Session session, Transaction transaction) {
        this.session = session;
        this.transaction = transaction;
    }

    public IDALDebtEntity getDebt(int id) throws DALException {
        DebtDeEntity Debt = null;

        try {
            Debt = (DebtDeEntity) session.createCriteria(DebtDeEntity.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();
        } catch (Exception e) {
            throw new DALException(e);
        }

        return Debt;
    }


    public void addDebt(IDALDebtEntity Debt) throws DALException {

        DebtDeEntity newDebt = null;
        if (Debt.getClass() == DebtDeEntity.class)
            newDebt = (DebtDeEntity) Debt;
        else
            throw new DALException();

        try {
            session.save(newDebt);
        } catch (Exception e) {
            throw new DALException(e);
        }
    }

    public List<IDALDebtEntity> getDebts() throws DALException {
        List<IDALDebtEntity> Debts = null;
        try {
            Debts = session.createQuery("from DebtDeEntity").list();


        } catch (Exception e) {
            throw new DALException(e);
        }
        return Debts;
    }


    public void update(IDALDebtEntity Debt) throws DALException {

        DebtDeEntity DebtPg = null;
        if (Debt.getClass() == DebtDeEntity.class)
            DebtPg = (DebtDeEntity) Debt;
        else
            throw new DALException();

        try {


            session.update(DebtPg);


        } catch (Exception e) {
            throw new DALException(e);
        }
    }

    public void delete(int id) throws DALException {
        IDALDebtEntity Debt = null;
        try {
            Debt = (DebtDeEntity) session.createCriteria(DebtDeEntity.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();

            session.delete(Debt);
        } catch (Exception e) {
            throw new DALException(e);
        }

    }
}
