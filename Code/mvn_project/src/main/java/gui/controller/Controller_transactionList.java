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
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.*;

public class Controller_transactionList implements Initializable, IController {
	
	@FXML private AnchorPane parent;
	@FXML private AnchorPane paneform;
	@FXML private ComboBox<BankAccountLogic> accountSelect;
	@FXML private ComboBox<String> periodSelect;
	@FXML private ComboBox<String> monthSelect;
	@FXML private Label lblTotalDepense;
	@FXML private JFXTreeTableView<WrapperTransaction> outGoTreeTableView;
	@FXML private Label lblTotalRevenu;
	@FXML private JFXTreeTableView<WrapperTransaction> incomeTreeTableView;
	@FXML private JFXNodesList nodeList;
	
	private JFXButton transactionButton;
	private JFXButton outGoButton;
	private JFXButton incomeButton;
	
	private ObservableList<WrapperTransaction> income = FXCollections.observableArrayList();
	private ObservableList<WrapperTransaction> outgo = FXCollections.observableArrayList();
	
	private BankAccountLogic bal;
	
	
	/**
	 * Load the fom
	 *
	 * @param isIncome
	 */
	public void callform(boolean isIncome, IOTransactionLogic tr) {
		
		/* we load the form fxml*/
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/formTransaction.fxml"));
		
		/*Create a instance of the controller of bank account form*/
		Controller_formTransaction controller = new Controller_formTransaction(this, isIncome, tr);
		
		/*Sets the controller associated with the root object*/
		loader.setController(controller);
		
		paneform.setVisible(true);
		paneform.setMouseTransparent(false);
		
		try {
			
			AnchorPane pane = loader.load();
			paneform.getChildren().add(pane);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * Methode to create the item in the TreeTableView
	 *
	 * @param result the transaction to insert in the TreeTableView
	 */
	@Override public void createItem(Object result) {
		
		unloadform();
		
		if (result != null) {
			IOTransactionLogic tr = (IOTransactionLogic) result;
			for (BankAccountLogic bal : ClientLogic.getInstance().getBankAccounts()) {
				if (bal.getId() == tr.getBankAccountID()) {
					accountSelect.getSelectionModel().select(bal);
					break;
				}
			}
			
			
			periodSelect.getSelectionModel().select("Annuel");
			Date date = tr.getDate();
			int year = date.toLocalDate().getYear();
			monthSelect.getSelectionModel().select(year);
			
			setData();
		}
	}
	
	/**
	 * Unload the form bank account
	 */
	private void unloadform() {
		
		paneform.getChildren().clear();
		paneform.setMouseTransparent(true);
		paneform.setVisible(false);
	}
	
	/**
	 * Delete the displayer and the data in the DB
	 *
	 * @param toDelete transaction to delete
	 */
	@Override public void deleteItem(Object toDelete) {
		
		unloadform();
		IOTransactionLogic tr = (IOTransactionLogic) toDelete;
		
		periodSelect.getSelectionModel().select("Annuel");
		Date date = tr.getDate();
		int year = date.toLocalDate().getYear();
		monthSelect.getSelectionModel().select(year);
		
		tr.supp();
		
		setData();
		
	}
	
	/**
	 * update the datas in the DB and refresh
	 *
	 * @param updated
	 */
	@Override public void modifyItem(Object updated) {
		unloadform();
		IOTransactionLogic tr = (IOTransactionLogic) updated;
		
		periodSelect.getSelectionModel().select("Annuel");
		Date date = tr.getDate();
		int year = date.toLocalDate().getYear();
		monthSelect.getSelectionModel().select(year);
		
		setData();
	}
	
	/**
	 * Set the data in the TreeTableView
	 */
	private void setData() {
		
		bal = accountSelect.getValue();
		boolean selectedAccount = accountSelect.getSelectionModel().isEmpty();
		boolean selectedPeriode = periodSelect.getSelectionModel().isEmpty();
		boolean selectedTime = monthSelect.getSelectionModel().isEmpty();
		
		JFXTreeTableColumn<WrapperTransaction, String> dateIncome = new JFXTreeTableColumn<>("Date");
		dateIncome.setMinWidth(150);
		dateIncome.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<WrapperTransaction, String>, ObservableValue<String>>() {
					
					@Override public ObservableValue<String> call(
							TreeTableColumn.CellDataFeatures<WrapperTransaction, String> param) {
						
						return param.getValue().getValue().date;
					}
				});
		
		JFXTreeTableColumn<WrapperTransaction, String> nameIncome = new JFXTreeTableColumn<>("nom");
		nameIncome.setMinWidth(150);
		nameIncome.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<WrapperTransaction, String>, ObservableValue<String>>() {
					
					@Override public ObservableValue<String> call(
							TreeTableColumn.CellDataFeatures<WrapperTransaction, String> param) {
						
						return param.getValue().getValue().name;
					}
				});
		
		JFXTreeTableColumn<WrapperTransaction, String> amountIncome = new JFXTreeTableColumn<>("montant");
		amountIncome.setMinWidth(150);
		amountIncome.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<WrapperTransaction, String>, ObservableValue<String>>() {
					
					@Override public ObservableValue<String> call(
							TreeTableColumn.CellDataFeatures<WrapperTransaction, String> param) {
						
						return param.getValue().getValue().amount;
					}
				});
		
		JFXTreeTableColumn<WrapperTransaction, String> dateOutgo = new JFXTreeTableColumn<>("Date");
		dateOutgo.setMinWidth(150);
		dateOutgo.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<WrapperTransaction, String>, ObservableValue<String>>() {
					
					@Override public ObservableValue<String> call(
							TreeTableColumn.CellDataFeatures<WrapperTransaction, String> param) {
						
						return param.getValue().getValue().date;
					}
				});
		
		JFXTreeTableColumn<WrapperTransaction, String> nameOutgo = new JFXTreeTableColumn<>("nom");
		nameOutgo.setMinWidth(150);
		nameOutgo.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<WrapperTransaction, String>, ObservableValue<String>>() {
					
					@Override public ObservableValue<String> call(
							TreeTableColumn.CellDataFeatures<WrapperTransaction, String> param) {
						
						return param.getValue().getValue().name;
					}
				});
		
		JFXTreeTableColumn<WrapperTransaction, String> amoutOutgo = new JFXTreeTableColumn<>("montant");
		amoutOutgo.setMinWidth(150);
		amoutOutgo.setCellValueFactory(
				new Callback<TreeTableColumn.CellDataFeatures<WrapperTransaction, String>, ObservableValue<String>>() {
					
					@Override public ObservableValue<String> call(
							TreeTableColumn.CellDataFeatures<WrapperTransaction, String> param) {
						
						return param.getValue().getValue().amount;
					}
				});
		
		
		if (!selectedAccount && !selectedPeriode && !selectedTime) {
			income.clear();
			outgo.clear();
			if (periodSelect.getValue().equals("Mensuel")) {
				int year = Calendar.getInstance().get(Calendar.YEAR);
				int month = monthSelect.getSelectionModel().getSelectedIndex();
				add(year, month);
			} else if (periodSelect.getValue().equals("Annuel")) {
				int year = Integer.valueOf(monthSelect.getValue());
				for (int i = 0; i < 12; ++i) {
					add(year, i);
				}
			}
			
			
			TreeItem<WrapperTransaction> root = new RecursiveTreeItem<WrapperTransaction>(income,
					RecursiveTreeObject::getChildren);
			incomeTreeTableView.getColumns().setAll(dateIncome, nameIncome, amountIncome);
			incomeTreeTableView.setRoot(root);
			incomeTreeTableView.setShowRoot(false);
			
			root = new RecursiveTreeItem<WrapperTransaction>(outgo, RecursiveTreeObject::getChildren);
			outGoTreeTableView.getColumns().setAll(dateOutgo, nameOutgo, amoutOutgo);
			outGoTreeTableView.setRoot(root);
			outGoTreeTableView.setShowRoot(false);
		}
		
		setTotal();
	}
	
	
	/**
	 * Set the total amount of outgo and income
	 */
	private void setTotal() {
		
		double totalIncome = 0;
		double totalOutgo = 0;
		for (int i = 0; i < income.size(); ++i) {
			totalIncome += Double.valueOf(income.get(i).amount.getValue());
		}
		
		for (int i = 0; i < outgo.size(); ++i) {
			totalOutgo += Double.valueOf(outgo.get(i).amount.getValue());
		}
		
		lblTotalRevenu.setText(String.valueOf(totalIncome));
		lblTotalDepense.setText(String.valueOf(totalOutgo));
	}
	
	private void add(int year, int month) {
		
		for (IOTransactionLogic tr : bal.getTransactions().get(year)[month]) {
			if (tr.isIncome()) {
				income.add(new WrapperTransaction(tr));
			} else {
				outgo.add(new WrapperTransaction(tr));
			}
		}
	}
	
	private void yearOrMonth() {
		
		ObservableList<String> items3 = FXCollections.observableArrayList();
		items3.clear();
		if (periodSelect.getValue().equals("Mensuel")) {
			items3.addAll("Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre",
					"Octobre", "Novembre", "Décembre");
		} else if (periodSelect.getValue().equals("Annuel")) {
			for (Object year : IOTransactionLogic.getYearsWithTransactions().toArray()) {
				items3.addAll(String.valueOf((Integer) year));
			}
		}
		monthSelect.setItems(items3);
	}
	
	private void generateNodeList() {
		
		transactionButton = new JFXButton("+");
		transactionButton.setButtonType(JFXButton.ButtonType.RAISED);
		transactionButton.getStyleClass().addAll("transaction-animate-button", "transaction-animate-button-sub");
		outGoButton = new JFXButton("Dep");
		outGoButton.setButtonType(JFXButton.ButtonType.RAISED);
		outGoButton.getStyleClass().addAll("transaction-animate-button", "transaction-animate-button-sub-2");
		incomeButton = new JFXButton("Rev");
		incomeButton.setButtonType(JFXButton.ButtonType.RAISED);
		incomeButton.getStyleClass().addAll("transaction-animate-button", "transaction-animate-button-sub-2");
		
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
	
	
	/**
	 * Called to initialize a controller after its root element has been
	 * completely processed.
	 *
	 * @param location The location used to resolve relative paths for the
	 * 		root object, or null if the location is not
	 * 		known.
	 * @param resources The resources used to localize the root object, or
	 * 		null if the root object was not localized.
	 */
	@Override public void initialize(URL location, ResourceBundle resources) {
		
		unloadform();
		generateNodeList();
		generateComboBoxItem();
		setTotal();
		
		accountSelect.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override public void handle(ActionEvent event) {
				
				setData();
			}
		});
		
		periodSelect.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override public void handle(ActionEvent event) {
				
				yearOrMonth();
				setData();
			}
		});
		
		monthSelect.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override public void handle(ActionEvent event) {
				
				setData();
			}
		});
		
		outGoButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override public void handle(ActionEvent event) {
				
				callform(false, null);
			}
		});
		
		incomeButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override public void handle(ActionEvent event) {
				
				callform(true, null);
			}
		});
		
		incomeTreeTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			
			@Override public void handle(MouseEvent event) {
				
				if (event.getButton().equals(MouseButton.PRIMARY)) {
					if (event.getClickCount() == 2) {
						int t = incomeTreeTableView.getSelectionModel().getFocusedIndex();
						if (t >= 0) {
							IOTransactionLogic tr = incomeTreeTableView.getTreeItem(t).getValue().getTransaction();
							callform(true, tr);
						}
					}
				}
			}
		});
		
		
		outGoTreeTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			
			@Override public void handle(MouseEvent event) {
				
				if (event.getButton().equals(MouseButton.PRIMARY)) {
					if (event.getClickCount() == 2) {
						int t = outGoTreeTableView.getSelectionModel().getFocusedIndex();
						if (t >= 0) {
							IOTransactionLogic tr = outGoTreeTableView.getTreeItem(t).getValue().getTransaction();
							callform(false, tr);
						}
					}
				}
			}
		});
		
	}
	
}
