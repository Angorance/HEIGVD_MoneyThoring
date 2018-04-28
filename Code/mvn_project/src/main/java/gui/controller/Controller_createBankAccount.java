package gui.controller;

import bll.logic.BankAccountLogic;
import bll.model.BankAccountModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller_createBankAccount implements Initializable,IForm {
    @FXML private JFXTextField nameAccount;
    @FXML private JFXTextField nameBankAccount;
    @FXML private JFXTextField amount;
    @FXML private JFXComboBox<?> typeAccount;
    @FXML private JFXButton returnButton;
    @FXML private JFXButton accepteButton;

    Controller_bankAccount cba;

    public Controller_createBankAccount(Controller_bankAccount cba) {
        this.cba = cba;
    }

    /**
     * Return to the previous window
     * @param event
     */
    @FXML
    @Override
    public void formCancel(ActionEvent event) {
        cba.add(null);

    }

    /**
     * TODO
     * @param event
     */
    @FXML
    @Override
    public void formValidation(ActionEvent event) {
        String name = nameAccount.getText();
        String bankName = nameBankAccount.getText();
        String type = (String)typeAccount.getValue();
        Double amountDouble = Double.parseDouble(amount.getText());
        BankAccountLogic ba = new BankAccountLogic(name,bankName,type,amountDouble,false);
        cba.add(ba);
    }

    /**
     * Called to initialize a controller after its root element has been completely processed.
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        accepteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                formValidation(event);
            }
        });

        returnButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                formCancel(event);
            }
        });
    }
}
