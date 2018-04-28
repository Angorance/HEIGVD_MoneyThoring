package dal.irepositories;

import dal.dalexception.DALException;
import dal.ientites.IDALRecurrenceEntity;

import java.util.List;

public interface IRecurrenceRepository {
    public IDALRecurrenceEntity getRecurrence(int id) throws DALException;

    public List<IDALRecurrenceEntity> getRecurrences() throws DALException;

    public List<IDALRecurrenceEntity> getRecurrences(int page, String sort);

    public void update(IDALRecurrenceEntity Recurrence) throws DALException;

    public void addRecurrence(IDALRecurrenceEntity Recurrence) throws DALException;

    public void delete(int id) throws DALException;
}
