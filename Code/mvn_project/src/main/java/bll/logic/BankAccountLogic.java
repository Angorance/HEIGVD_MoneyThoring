package bll.logic;

import bll.model.BankAccountModel;

/**
 * ClientLogic class.
 * Implements the business logic of the ClientModel. The methods allow to
 * change the attributes of the client like his email, his username or his
 * password. Before changing these attributes, the methods check their integrity
 * to avoid data problems.
 *
 * @authors Daniel Gonzalez Lopez
 * @version 1.0
 */
public class BankAccountLogic extends BankAccountModel {

    public BankAccountLogic() {}

    /**
     * Increment the bank account by the value of the parameter.
     *
     * @param income Income to add to the amount.
     */
    public void increment(double income) {
        setAmount(getAmount() + income);
    }

    /**
     * Decrement the bank account by the value of the parameter.
     *
     * @param outgo Outgo to sub to the amount.
     */
    public void decrement(double outgo) {
        setAmount(getAmount() - outgo);
    }

    /**
     * "Suppress" the bank account. It simply makes it invisible for the user.
     */
    public void supp() {
        setVisible(false);
    }
}
