package dal.orm;

import dal.irepositories.IBankaccountRepository;
import dal.irepositories.IClientRepository;

public interface IORM {
    IClientRepository getClientRepository();
    IBankaccountRepository getBankaccountRepository();
}
