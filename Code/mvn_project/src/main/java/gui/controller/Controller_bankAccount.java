package gui.controller;

import gui.model.createBankAccount;
import bll.model.BankAccountModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

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
        public AccountDisplayer(BankAccountModel bankAccount) {

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
    @FXML private AnchorPane paneform;

    /**
     * Event on the create button that will load the account creation page
     * @param actionEvent
     */
    public void createButton(ActionEvent actionEvent){

        // we load the form fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/formBankAccount.fxml"));
        Controller_createBankAccount cba = new Controller_createBankAccount(this);
        loader.setController(cba);

        paneform.setVisible(true);
        paneform.setMouseTransparent(false);
        try {
            AnchorPane pane = loader.load();
            // todo faire en sorte que le pane prenne les dimmensions du parent (paneform)
            paneform.getChildren().add(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void add(BankAccountModel bc){
        paneform.getChildren().clear();
        paneform.setMouseTransparent(true);
        paneform.setVisible(false);
        if(bc != null) {
            AccountDisplayer accountDisplayer = new AccountDisplayer(bc);
            addToFrame(accountDisplayer);
        }
    }

    private void addToFrame(AccountDisplayer accountDisplayer){
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
        paneform.setVisible(false);
        paneform.setMouseTransparent(true);

        //Go through the list of bank accounts and add it to our frame
        /*for(BankAccountModel bankAccount : BankAccount.getBankAccounts()){
            AccountDisplayer accountDisplayer = new AccountDisplayer(bankAccount);
            addToFrame(accountDisplayer);
        }*/

        /*Add event at our button*/
        create_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                createButton(event);
            }
        });
    }
}
