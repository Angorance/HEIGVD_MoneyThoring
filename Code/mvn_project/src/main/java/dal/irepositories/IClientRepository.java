package dal.irepositories;

import dal.dalexception.DALException;
import dal.ientites.IDALClientEntity;

import java.util.Collection;

public interface IClientRepository {

    public IDALClientEntity getClient(int id) throws DALException;

    public Collection<IDALClientEntity> getClients();

    public Collection<IDALClientEntity> getClients(int page, String sort);

    public void update(IDALClientEntity client);

    public void addClient(IDALClientEntity client) throws DALException;

    public void delete(int id);

}
