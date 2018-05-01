package gui.controller;/* ***********************************************
    HEIG-VD
    Date : 12.04.2018
    Nom du fichier : 
    Auteur(s) : Bryan Curchod
    But:
    
 ************************************************* */

import bll.logic.BankAccountLogic;
import bll.logic.ClientLogic;
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
import java.util.*;

public class Controller_transactionList implements Initializable {


    @FXML
    private AnchorPane parent;

    @FXML
    private ComboBox<BankAccountLogic> accountSelect;
    @FXML
    private ComboBox<String> periodSelect;
    @FXML
    private ComboBox<String> monthSelect;

    @FXML
    private Label lblTotalDepense;
    @FXML
    private JFXTreeTableView<WrapperTransaction> outGoTreeTableView;
    @FXML
    private Label lblTotalRevenu;
    @FXML
    private JFXTreeTableView<WrapperTransaction> incomeTreeTableView;
    @FXML
    private JFXNodesList nodeList;

    JFXButton transactionButton;
    JFXButton outGoButton;
    JFXButton incomeButton;


    ObservableList<WrapperTransaction> income = FXCollections.observableArrayList();
    ObservableList<WrapperTransaction> outgo = FXCollections.observableArrayList();
    JFXTreeTableColumn<WrapperTransaction, String> dateIncome;
    JFXTreeTableColumn<WrapperTransaction, String> nameIncome;
    JFXTreeTableColumn<WrapperTransaction, String> amountIncome;
    JFXTreeTableColumn<WrapperTransaction, String> dateOutgo;
    JFXTreeTableColumn<WrapperTransaction, String> nameOutgo;
    JFXTreeTableColumn<WrapperTransaction, String> amoutOutgo;

    public Controller_transactionList() {

    }

    private void setData() {

        boolean selectedAccount = accountSelect.getSelectionModel().isEmpty();
        boolean selectedPeriode = periodSelect.getSelectionModel().isEmpty();
        boolean selectedTime = monthSelect.getSelectionModel().isEmpty();

        dateIncome = new JFXTreeTableColumn<>("Date");
        dateIncome.setMinWidth(150);
        dateIncome.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<WrapperTransaction, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<WrapperTransaction, String> param) {
                return param.getValue().getValue().date;
            }
        });

        nameIncome = new JFXTreeTableColumn<>("nom");
        nameIncome.setMinWidth(150);
        nameIncome.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<WrapperTransaction, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<WrapperTransaction, String> param) {
                return param.getValue().getValue().name;
            }
        });

        amountIncome = new JFXTreeTableColumn<>("montant");
        amountIncome.setMinWidth(150);
        amountIncome.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<WrapperTransaction, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<WrapperTransaction, String> param) {
                return param.getValue().getValue().amount;
            }
        });

        dateOutgo = new JFXTreeTableColumn<>("Date");
        dateOutgo.setMinWidth(150);
        dateOutgo.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<WrapperTransaction, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<WrapperTransaction, String> param) {
                return param.getValue().getValue().date;
            }
        });

        nameOutgo = new JFXTreeTableColumn<>("nom");
        nameOutgo.setMinWidth(150);
        nameOutgo.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<WrapperTransaction, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<WrapperTransaction, String> param) {
                return param.getValue().getValue().name;
            }
        });

        amoutOutgo = new JFXTreeTableColumn<>("montant");
        amoutOutgo.setMinWidth(150);
        amoutOutgo.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<WrapperTransaction, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<WrapperTransaction, String> param) {
                return param.getValue().getValue().amount;
            }
        });
        int year;
        if (!selectedAccount && !selectedPeriode && !selectedTime) {
            income.clear();
            outgo.clear();
            if(periodSelect.getValue().equals("Mensuel")){
                year = Calendar.getInstance().get(Calendar.YEAR);
                int month = monthSelect.getSelectionModel().getSelectedIndex();
                add(year,month);
            }else if(periodSelect.getValue().equals("Annuel")){
                year = Integer.valueOf(monthSelect.getValue());
                for(int i = 0; i < 12; ++i){
                    add(year,i);
                }
            }
            final TreeItem<WrapperTransaction> root = new RecursiveTreeItem<WrapperTransaction>(income, RecursiveTreeObject::getChildren);
            incomeTreeTableView.getColumns().setAll(dateIncome, nameIncome, amountIncome);
            incomeTreeTableView.setRoot(root);
            incomeTreeTableView.setShowRoot(false);


            final TreeItem<WrapperTransaction> root2 = new RecursiveTreeItem<WrapperTransaction>(outgo, RecursiveTreeObject::getChildren);
            outGoTreeTableView.getColumns().setAll(dateOutgo, nameOutgo, amoutOutgo);
            outGoTreeTableView.setRoot(root2);
            outGoTreeTableView.setShowRoot(false);
        }

        setTotal();
    }


    private void setTotal(){
        double totalIncome = 0;
        double totalOutgo = 0;
        for(int i = 0; i < income.size();++i){
            totalIncome += Double.valueOf(income.get(i).amount.getValue());
        }

        for(int i = 0; i < outgo.size();++i){
            totalOutgo += Double.valueOf(outgo.get(i).amount.getValue());
        }

        lblTotalRevenu.setText(String.valueOf(totalIncome));
        lblTotalDepense.setText(String.valueOf(totalOutgo));
    }
    private void add(int year,int month){
        HashMap<Integer,LinkedList<IOTransactionLogic>[]> map = new HashMap<>();
        LinkedList<IOTransactionLogic>[] list = new LinkedList[12];
        Random rdm = new Random();
        for(int i = 0; i < 12 ; ++i){
            list[i] = new LinkedList<IOTransactionLogic>();
            for(int j = 0; j < 10; ++j){
                int amount = 1000 + rdm.nextInt(25000) * (rdm .nextBoolean() ? -1 : 1);
                list[i].add(new IOTransactionLogic(amount,"transaction" + i,"a","10.10.2018","CHF", null,accountSelect.getValue()));
            }
        }
        for(int i = 2000; i <= 2018; ++i){
            map.put(i,list);
        }
        for(IOTransactionLogic transaction : map.get(year)[month]){
            if(transaction.isIncome()){
                income.add(new WrapperTransaction(transaction));
            }else{
                outgo.add(new WrapperTransaction(transaction));
            }
        }
    }

    private void yearOrMonth(){
        ObservableList<String> items3 = FXCollections.observableArrayList();
        items3.clear();
        if (periodSelect.getValue().equals("Mensuel")) {
            items3.addAll("Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre",
                    "Octobre", "Novembre", "Décembre");
        } else if (periodSelect.getValue().equals("Annuel")) {
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            int firstYear = 2000;
            for (int i = firstYear; i <= currentYear; ++i){
                items3.addAll(String.valueOf(i));
            }
        }
        monthSelect.setItems(items3);
    }


    /**
     *
     */
    private void createOutGo() {

    }

    /**
     *
     */
    private void createInCome() {

    }

    private void generateNodeList() {
        transactionButton = new JFXButton("+");
        transactionButton.setButtonType(JFXButton.ButtonType.RAISED);
        //transactionButton.setStyle("-fx-background-color: green");
        transactionButton.getStyleClass().addAll("animated-option-button", "animated-option-sub-button2");
        outGoButton = new JFXButton("Dep");
        outGoButton.setButtonType(JFXButton.ButtonType.RAISED);
        outGoButton.getStyleClass().addAll("animated-option-button", "animated-option-sub-button3");
        incomeButton = new JFXButton("Rev");
        incomeButton.setButtonType(JFXButton.ButtonType.RAISED);
        incomeButton.getStyleClass().addAll("animated-option-button", "animated-option-sub-button3");

        nodeList.addAnimatedNode(transactionButton);
        nodeList.addAnimatedNode(outGoButton);
        nodeList.addAnimatedNode(incomeButton);
        nodeList.setSpacing(5d);
        nodeList.setRotate(180);
    }

    private void generateComboBoxItem() {
        ObservableList<BankAccountLogic> items = FXCollections.observableArrayList();
        for (BankAccountLogic bal : ClientLogic.getInstance().getBankAccounts()) {
            items.add(bal);
        }
        accountSelect.setItems(items);

        ObservableList<String> items2 = FXCollections.observableArrayList();
        items2.addAll("Mensuel", "Annuel");
        periodSelect.setItems(items2);
    }

    private void generateNameColumn(){

    }

    /**
     * Called to initialize a controller after its root element has been completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        generateNodeList();
        generateComboBoxItem();
        accountSelect.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setData();
            }
        });

        periodSelect.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                yearOrMonth();
                setData();
            }
        });

        monthSelect.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setData();
            }
        });

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
