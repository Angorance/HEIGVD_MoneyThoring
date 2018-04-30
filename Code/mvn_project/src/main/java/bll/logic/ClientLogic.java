package bll.logic;

import bll.model.ClientModel;
import dal.orm.PgORM;

import java.util.ArrayList;


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
public class ClientLogic extends ClientModel {

    private ArrayList<BankAccountLogic> bankAccounts;
    private ArrayList<CategoryLogic> categories;


    private ClientLogic() {}


    /**
     * TODO
     */
    public static class Instance {
        static ClientLogic instance = new ClientLogic();
    }

    /**
     * TODO
     * @return
     */
    public static ClientLogic getInstance() {
        return Instance.instance;
    }


    /**
     * TODO
     * @param email
     * @param username
     * @param password
     */
    public void setClient(String email, String username, String password) {

        setEmail(email);
        setUsername(username);
        
        try {
            setPassword(Authentication.hash(password));
        } catch (Exception e) {
            System.out.println(e);
        }
        
        setKey("SYdjcvkbejbsl");

        bankAccounts = new ArrayList<>();
        categories = new ArrayList<>();
        
        // TODO - Manage if connected and use Derby if necessary.
        createUser(new PgORM());
    }


    // GETTERS

    /**
     * Get the list of bank accounts of the client.
     *
     * @return ArrayList of bank accounts.
     */
    public ArrayList<BankAccountLogic> getBankAccounts() {
        return bankAccounts;
    }


    // SETTERS

    /**
     * Change the email of the client by the new one given in parameter.
     * Before setting the new email, setEmail() verifies its format and sends
     * a validation key to verify the email. For this purpose, it changes the
     * flag isValidated.
     *
     * @see ClientModel#setEmail(String)
     * @see ClientModel#setActivated(boolean)
     *
     * @param email New email to set.
     */
    @Override
    public void setEmail(String email) {
        // TODO - check email format
        // TODO - send validation key

        super.setEmail(email);
    }

    /**
     * Change the username of the client by the new one given in parameter.
     * Before setting the new username, setUsername() verifies it is not already
     * being used by another client.
     *
     * TODO - Logic works if online. If not, when synchronising, add random number ?
     *
     * @see ClientModel#setUsername(String)
     *
     * @param username New username to set.
     */
    @Override
    public void setUsername(String username) {
        // TODO - Check if not already used.

        super.setUsername(username);
    }

    /**
     * Change the password of the client by the new one given in parameter.
     * Before setting, it hashes the password with the client salt.
     *
     * @see ClientModel#setPassword(String)
     *
     * @param password New password to set.
     */
    @Override
    public void setPassword(String password) {
        String hashedPassword = password + getSalt();

        // TODO - Hash password

        super.setPassword(hashedPassword);
    }

    /**
     * Link a bank account to its client.
     *
     * @param ba Bank account to add to the list.
     */
    public void addBankAccount(BankAccountLogic ba) {
        ba.setClientId(getId());
        bankAccounts.add(ba);
    }

    /**
     * TODO
     *
     * @param ca
     */
    public void addCategory(CategoryLogic ca) {
        categories.add(ca);
    }


    // SUPPRESSORS

    /**
     * Suppress the client's account and his data.
     */
    public void supp() {
        // TODO - Suppress the account !
    }
}
