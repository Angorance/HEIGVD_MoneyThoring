package dal.orm;

import dal.irepositories.IBankaccountRepository;
import dal.irepositories.IClientRepository;
import dal.repositories.derby.ClientDeRepository;
import dal.repositories.pgsql.ClientPgRepository;

public class DerbyORM implements IORM {

    private IClientRepository clientRepository;

    @Override
    public IClientRepository getClientRepository() {
        if (clientRepository == null) {
            clientRepository = new ClientDeRepository();
        }
        return clientRepository;
    }

    @Override
    public IBankaccountRepository getBankaccountRepository() {
        return null;
    }
}
