package dal.repositories.derby;

import dal.ientites.IDALClientEntity;
import dal.irepositories.IClientRepository;

import java.util.Collection;

public class ClientDeRepository implements IClientRepository {
    public IDALClientEntity getClient(int id) {
        return null;
    }

    public Collection<IDALClientEntity> getClients() {
        return null;
    }

    public Collection<IDALClientEntity> getClients(int page, String sort) {
        return null;
    }

    public void update(IDALClientEntity client) {

    }

    public void delete(int id) {

    }
}
