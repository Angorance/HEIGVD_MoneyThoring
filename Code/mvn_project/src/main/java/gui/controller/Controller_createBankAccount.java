package gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class Controller_createBankAccount {
    @FXML private JFXTextField nameAccount;
    @FXML private JFXTextField nameBankAccount;
    @FXML private JFXTextField amount;
    @FXML private JFXComboBox<?> typeAccount;
    @FXML private JFXButton returnButton;
    @FXML private JFXButton accepteButton;
    /**
     * Return to the previous window
     * @param event
     */
    @FXML void returnButton(ActionEvent event) {
        closeStage();
    }

    /**
     *
     * @param event
     */
    @FXML void validationButton(ActionEvent event) {
        String nameAccount_text = nameAccount.getText();
        String nameBankAccount_text = nameBankAccount.getText();
        String typeAccount_text = typeAccount.getTypeSelector();
        double amount_text = Double.parseDouble(amount.getText());
        closeStage();
    }

    public void closeStage(){
        ((Stage)nameAccount.getScene().getWindow()).close();
    }
}
