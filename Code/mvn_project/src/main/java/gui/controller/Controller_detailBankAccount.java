package gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller_detailBankAccount implements Initializable {

    Controller_bankAccount cba;
    @FXML private BarChart<?, ?> barChart;
    @FXML private Label nameBankAccount;

    /**
     * Constructor of our Controller
     * @param cba
     */
    public Controller_detailBankAccount(Controller_bankAccount cba){

        this.cba = cba;
    }

    /**
     * Called to initialize a controller after its root element has been completely processed.
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
