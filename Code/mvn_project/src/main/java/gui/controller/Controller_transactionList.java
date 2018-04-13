package gui.controller;/* ***********************************************
    HEIG-VD
    Date : 12.04.2018
    Nom du fichier : 
    Auteur(s) : Bryan Curchod
    But:
    
 ************************************************* */

import com.jfoenix.controls.JFXListCell;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller_transactionList implements Initializable {
    @FXML private ComboBox<?> accountSelect;

    @FXML private ChoiceBox<?> btnAdd;

    @FXML private Label lblTotalDepense;

    @FXML private JFXListCell<?> listDepense;

    @FXML private Label lblTotalRevenu;

    @FXML private JFXListCell<?> listRevenu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // get the account list, and the transaction list associated to each account
    }
}
