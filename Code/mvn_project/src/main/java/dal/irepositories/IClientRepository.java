package dal.irepositories;

import dal.dalexception.DALException;
import dal.ientites.IDALClientEntity;

import java.util.Collection;
import java.util.List;

public interface IClientRepository {

    public IDALClientEntity getClient(int id) throws DALException;

    public List<IDALClientEntity> getClients() throws DALException;

    public void update(IDALClientEntity client) throws DALException;

    public void addClient(IDALClientEntity client) throws DALException;

    public void delete(int id) throws DALException;

}
