package bll.logic;

import bll.model.IOTransactionModel;

import java.sql.Date;

/**
 * TODO
 *
 * @author Daniel Gonzalez Lopez, Héléna Line Reymond
 * @version 1.0
 */
public class IOTransactionLogic extends IOTransactionModel {

    private CategoryLogic category;


    public IOTransactionLogic() {}
    
    /**
     * TODO
     * @param amount
     * @param name
     * @param description
     * @param date
     * @param currency
     * @param category
     * @param bankAccount
     */
    public IOTransactionLogic(double amount, String name, String description,
                              Date date, String currency, CategoryLogic category,
                              BankAccountLogic bankAccount) {

        super(amount, name, description, date, currency, (amount >= 0));

        this.category = category;

        bankAccount.addTransaction(this);
    }
}
