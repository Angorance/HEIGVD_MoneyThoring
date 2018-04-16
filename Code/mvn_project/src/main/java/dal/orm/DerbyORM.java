package dal.orm;

import dal.irepositories.IBankaccountRepository;
import dal.irepositories.IClientRepository;
import dal.repositories.derby.BankaccountDeRepository;
import dal.repositories.derby.ClientDeRepository;
import dal.repositories.pgsql.ClientPgRepository;

public class DerbyORM implements IORM {

    private IClientRepository clientRepository;
    private IBankaccountRepository bankaccountRepository;

    @Override
    public IClientRepository getClientRepository() {
        if (clientRepository == null) {
            clientRepository = new ClientDeRepository();
        }
        return clientRepository;
    }

    @Override
    public IBankaccountRepository getBankaccountRepository() {
        if(bankaccountRepository == null){
            bankaccountRepository = new BankaccountDeRepository();
        }
        return bankaccountRepository;
    }
}
