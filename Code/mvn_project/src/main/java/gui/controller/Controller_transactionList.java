package gui.controller;/* ***********************************************
    HEIG-VD
    Date : 12.04.2018
    Nom du fichier : 
    Auteur(s) : Bryan Curchod
    But:
    
 ************************************************* */

import bll.logic.IOTransactionLogic;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller_transactionList implements Initializable {



    @FXML private AnchorPane parent;
    @FXML private ComboBox<?> accountSelect;
    @FXML private ComboBox<?> periodSelect;
    @FXML private ComboBox<?> monthSelect;
    @FXML private Label lblTotalDepense;
    @FXML private JFXTreeTableView<User> outGoTreeTableView;
    @FXML private Label lblTotalRevenu;
    @FXML private JFXTreeTableView<User> incomeTreeTableView;
    @FXML private JFXNodesList nodeList;

    JFXButton transactionButton;
    JFXButton outGoButton;
    JFXButton incomeButton;

    class User extends RecursiveTreeObject<User> {

        StringProperty userName;
        StringProperty age;
        StringProperty department;

        public User(String department, String age, String userName) {
            this.department = new SimpleStringProperty(department);
            this.userName = new SimpleStringProperty(userName);
            this.age = new SimpleStringProperty(age);
        }

    }

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

        JFXTreeTableColumn<User, String> deptName = new JFXTreeTableColumn<>("Department");
        deptName.setPrefWidth(150);
        deptName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().department;
            }
        });

        JFXTreeTableColumn<User, String> ageCol = new JFXTreeTableColumn<>("Age");
        ageCol.setPrefWidth(150);
        ageCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().age;
            }
        });

        JFXTreeTableColumn<User, String> nameCol = new JFXTreeTableColumn<>("Name");
        nameCol.setPrefWidth(150);
        nameCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<User, String> param) {
                return param.getValue().getValue().userName;
            }
        });

        ObservableList<User> users = FXCollections.observableArrayList();
        users.add(new User("Computer Department", "23", "CD 1"));
        users.add(new User("Sales Department", "22", "Employee 1"));
        users.add(new User("Sales Department", "22", "Employee 2"));
        users.add(new User("Sales Department", "25", "Employee 4"));
        users.add(new User("Sales Department", "25", "Employee 5"));
        users.add(new User("IT Department", "42", "ID 2"));
        users.add(new User("HR Department", "22", "HR 1"));
        users.add(new User("HR Department", "22", "HR 2"));

        final TreeItem<User> root = new RecursiveTreeItem<User>(users, RecursiveTreeObject::getChildren);
        outGoTreeTableView.getColumns().setAll(deptName, ageCol, nameCol);
        outGoTreeTableView.setRoot(root);
        outGoTreeTableView.setShowRoot(false);


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
