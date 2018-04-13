package gui.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;


class BankAccount{
    private static LinkedList<BankAccount> bankAccounts = new LinkedList<BankAccount>();
    public static int no = 1;
    private int id;
    private String type;
    private String name;
    private String bankName;
    private double amount;

    public BankAccount(String type, String name, String bankName,double amount){
        id = no++;
        this.type = type;
        this.name = name;
        this.bankName = bankName;
        this.amount = amount;
        bankAccounts.add(this);
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getBankName() {
        return bankName;
    }

    public double getAmount() {
        return amount;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public static LinkedList<BankAccount> getBankAccounts() {
        return bankAccounts;
    }
}

public class Controller_bankAccount implements Initializable {

    /**
     * class to display a bank account with a GridPane
     */
    class AccountDisplayer extends GridPane {
        /*Default size*/
        private final int WIDTH = 100;
        private final int HEIGHT = 50;

        private Label nameAccount;
        private Label amountAccount;

        /**
         * Constructor of the class
         * @param bankAccount   bank account we want to display
         */
        public AccountDisplayer(BankAccount bankAccount) {

            nameAccount = new Label(bankAccount.getName());
            amountAccount = new Label("" + bankAccount.getAmount() + " CHF");

            this.add(nameAccount,0,0);
            this.add(amountAccount,0,1);

            this.getStyleClass().add("AccountDisplay");
            this.setPrefSize(WIDTH,HEIGHT);
        }
    }

    @FXML private FlowPane frame_bankAccount;
    @FXML private Button create_button;

    /**
     *Event button that will load the account creation page
     * @param actionEvent
     */
    public void createButton(ActionEvent actionEvent){

        /*Create a new account displayer*/

        /*AccountDisplayer accountDisplayer = new AccountDisplayer(
                new BankAccount("Compte courant","Mon compte 6",
                        "UBS",5678.95));

        add(accountDisplayer);*/

        System.out.println("Compte ajouté");
    }

    /**
     * Methode to add a accountDisplayer in our frame
     * @param accountDisplayer accountDisplayer who want to display in out frame
     */
    public void add(AccountDisplayer accountDisplayer){
        frame_bankAccount.getChildren().add(accountDisplayer);
        FlowPane.setMargin(accountDisplayer,new Insets(5,5,5,5));
    }


    /**
     * Called to initialize a controller after its root element has been completely processed.
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Remove all children from FlowPane container (frame_bankAccount)
        frame_bankAccount.getChildren().removeAll();

        //Go through the list of bank accounts and add it to our frame
        for(BankAccount bankAccount : BankAccount.getBankAccounts()){
            AccountDisplayer accountDisplayer = new AccountDisplayer(bankAccount);
            add(accountDisplayer);
        }

        /*Add event at our button*/
        create_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                createButton(event);
            }
        });
    }
}
