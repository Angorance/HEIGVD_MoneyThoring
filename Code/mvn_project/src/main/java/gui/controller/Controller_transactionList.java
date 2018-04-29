package gui.controller;/* ***********************************************
    HEIG-VD
    Date : 12.04.2018
    Nom du fichier : 
    Auteur(s) : Bryan Curchod
    But:
    
 ************************************************* */

import bll.logic.IOTransactionLogic;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXNodesList;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller_transactionList implements Initializable {



    @FXML private AnchorPane parent;
    @FXML private ComboBox<?> accountSelect;
    @FXML private ComboBox<?> periodSelect;
    @FXML private ComboBox<?> monthSelect;
    @FXML private Label lblTotalDepense;
    @FXML private JFXTreeTableView<?> outGoTreeTableView;
    @FXML private Label lblTotalRevenu;
    @FXML private JFXTreeTableView<?> incomeTreeTableView;
    @FXML private JFXNodesList nodeList;

    JFXButton transactionButton;
    JFXButton outGoButton;
    JFXButton incomeButton;
    public Controller_transactionList() {

    }

    /**
     *
     */
    private void createOutGo(){

    }

    /**
     *
     */
    private void createInCome(){

    }

    private void generateNodeList(){
        transactionButton = new JFXButton("+");
        transactionButton.setButtonType(JFXButton.ButtonType.RAISED);
        //transactionButton.setStyle("-fx-background-color: green");
        transactionButton.getStyleClass().addAll("animated-option-button","animated-option-sub-button2");
        outGoButton = new JFXButton("Dep");
        outGoButton.setButtonType(JFXButton.ButtonType.RAISED);
        outGoButton.getStyleClass().addAll("animated-option-button","animated-option-sub-button3");
        incomeButton = new JFXButton("Rev");
        incomeButton.setButtonType(JFXButton.ButtonType.RAISED);
        incomeButton.getStyleClass().addAll("animated-option-button","animated-option-sub-button3");

        nodeList.addAnimatedNode(transactionButton);
        nodeList.addAnimatedNode(outGoButton);
        nodeList.addAnimatedNode(incomeButton);
        nodeList.setSpacing(5d);
        nodeList.setRotate(180);
    }
    /**
     * Called to initialize a controller after its root element has been completely processed.
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        generateNodeList();
        outGoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                createOutGo();
            }
        });

        incomeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                createInCome();
            }
        });
        // get the account list, and the transaction list associated to each account
    }
}
