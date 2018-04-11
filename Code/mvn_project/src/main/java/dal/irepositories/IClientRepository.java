package dal.irepositories;

import dal.ientites.IDALClientEntity;

import java.util.Collection;

public interface IClientRepository {

    public IDALClientEntity getClient(int id);

    public Collection<IDALClientEntity> getClients();

    public Collection<IDALClientEntity> getClients(int page, String sort);

    public void update(IDALClientEntity client);

    public void delete(int id);

}
