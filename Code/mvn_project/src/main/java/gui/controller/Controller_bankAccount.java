package mt.gui.controller;

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


class AccountDisplayer extends GridPane {
    /*Default size*/
    private final int WIDTH = 100;
    private final int HEIGHT = 50;

    private Label nameAccount;
    private Label amountAccount;

    public AccountDisplayer(BankAccount bankAccount) {
        nameAccount = new Label(bankAccount.getName());
        amountAccount = new Label("" + bankAccount.getAmount() + " CHF");

        this.add(nameAccount,0,0);
        this.add(amountAccount,0,1);

        this.getStyleClass().add("AccountDisplay");
        this.setPrefSize(WIDTH,HEIGHT);
    }
}


public class Controller_bankAccount implements Initializable {
    @FXML private FlowPane frame_bankAccount;
    @FXML private Button create_button;

    public void createButton(ActionEvent actionEvent){
        AccountDisplayer accountDisplayer = new AccountDisplayer(new BankAccount("Compte courant","Mon compte 6","UBS",5678.95));
        add(accountDisplayer);
        System.out.println("Compte ajouté");
    }

    public void add(AccountDisplayer accountDisplayer){
        frame_bankAccount.getChildren().add(accountDisplayer);
        FlowPane.setMargin(accountDisplayer,new Insets(5,5,5,5));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Remove all children from FlowPane container (frame_bankAccount)
        frame_bankAccount.getChildren().removeAll();

        for(BankAccount bankAccount : BankAccount.getBankAccounts()){
            AccountDisplayer accountDisplayer = new AccountDisplayer(bankAccount);
            add(accountDisplayer);
        }

        create_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                createButton(event);
            }
        });
    }
}
