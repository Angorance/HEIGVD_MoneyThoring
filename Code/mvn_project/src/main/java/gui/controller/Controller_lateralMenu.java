package gui.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Implement the behavior of the hambuger menu. In our case, simply change the style of the clicked button.
 */
public class Controller_lateralMenu implements Initializable{
    Button selected;

    @FXML private Button btnDashboard;

    @FXML private Button btnBudget;

    @FXML private Button btnTransaction;

    @FXML private Button btnDept;

    @FXML private Button btnBankAccount;

    @FXML private Button btnCategory;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnDashboard.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                setSelected((Button)event.getSource());
            }
        });

        btnBudget.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                setSelected((Button)event.getSource());
            }
        });

        btnTransaction.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                setSelected((Button)event.getSource());
            }
        });

        btnDept.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                setSelected((Button)event.getSource());
            }
        });

        btnBankAccount.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                setSelected((Button)event.getSource());
            }
        });


        btnCategory.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                setSelected((Button)event.getSource());
            }
        });
    }

    public void setSelected(Button btn){
        if(selected != null) {
            selected.getStyleClass().remove("Selected");
        }
        selected = btn;
        selected.getStyleClass().add("Selected");
    }

    public static class Controller_categoryList {
    }
}
