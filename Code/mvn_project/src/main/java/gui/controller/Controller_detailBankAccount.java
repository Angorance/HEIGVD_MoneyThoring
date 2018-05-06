package gui.controller;

import bll.logic.BankAccountLogic;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller_detailBankAccount implements Initializable {

    Controller_bankAccount cba;
    @FXML private Label name;
    @FXML private Label nameBankAccount;
    @FXML private Label typeBankAccount;
    @FXML private Label dateLastTransaction;
    @FXML private Label amountBankAccount;
    @FXML private LineChart<?, ?> lineChart;
    @FXML private CategoryAxis axeX;
    @FXML private NumberAxis axeY;
    @FXML private JFXButton returnButton;
    BankAccountLogic bal;

    /**
     * Constructor of our controller
     * @param cba TODO
     * @param bal TODO
     */
    public Controller_detailBankAccount(Controller_bankAccount cba, BankAccountLogic bal){
        this.bal = bal;
        this.cba = cba;
    }
    
    private void returnFrame() {
        cba.createItem(null);
    }

    /**
     * Called to initialize a controller after its root element has been completely processed.
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        /*Set the name of the account*/
        name.setText(bal.getName());

        /*Set the name of the bank account*/
        nameBankAccount.setText(bal.getBankName());

        /*Set the type of the account*/
        typeBankAccount.setText(bal.getType());

        //if the list of transaction is not empty, we get the last transaction date
        if(!bal.getTransactions().isEmpty()) {
            dateLastTransaction.setText(bal.getTransactions().get(bal.getTransactions().size() - 1).getDate());
        }

        /*Change the color if the amount is bigger or lesser than 0*/
        if(bal.getAmount() >= 0){
            amountBankAccount.setTextFill(Color.GREEN);
        }else{
            amountBankAccount.setTextFill(Color.RED);
        }

        /*Set the amount of the bank account*/
        amountBankAccount.setText(String.valueOf(bal.getAmount()) + " CHF");

        /*TODO mettre les données dans le graphique*/
        /*TODO max min des valeur de l'axe Y*/
        XYChart.Series series = new XYChart.Series();
        series.setName("Evolution du solde");
        Random random = new Random();
        for(int i = 0; i < 31; ++i){
            int value = 15000 + random.nextInt(5000);
            series.getData().add(new XYChart.Data(String.valueOf(i+1),value));
        }

        lineChart.getData().addAll(series);
        
        returnButton.setOnAction(new EventHandler<ActionEvent>() {
    
            @Override public void handle(ActionEvent event) {
                returnFrame();
            }
        });


    }
}
