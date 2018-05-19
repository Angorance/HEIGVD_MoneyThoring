package gui.controller.transaction;

import bll.logic.*;
import com.jfoenix.controls.*;
import gui.Utility;
import gui.controller.IController;
import gui.controller.IForm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;

public class Controller_formTransaction implements Initializable, IForm {
	
	@FXML private JFXTextField name;
	@FXML private JFXTextField amount;
	//@FXML private JFXComboBox<String> time;
	//@FXML private JFXCheckBox recurrence;
	@FXML private JFXComboBox<BankAccountLogic> bankAccount;
	@FXML private JFXComboBox<CategoryLogic> category;
	@FXML private JFXButton returnButton;
	@FXML private JFXButton accepteButton;
	@FXML private JFXButton deleteButton;
	@FXML private JFXDatePicker datePicker;
	@FXML private JFXCheckBox checkSharedBudget;
	@FXML private JFXComboBox<BudgetLogic> cbxBudgets;
	
	private IController controller;
	private boolean isIncome;
	private IOTransactionLogic tr;
	private BudgetLogic bl;
	
	public Controller_formTransaction(IController controller, boolean isIncome, IOTransactionLogic tr) {
		
		this.controller = controller;
		this.isIncome = isIncome;
		this.tr = tr;
	}
	
	/**
	 * Return to the previous window
	 *
	 * @param event
	 */
	@FXML @Override public void formCancel(ActionEvent event) {
		
		controller.createItem(null);
	}
	
	/**
	 * TODO
	 *
	 * @param event
	 */
	@FXML @Override public void formValidation(ActionEvent event) {
		
		if (checkValidInput()) {
			/*name of transaction*/
			String nameText = name.getText();
			
			/*transaction amount*/
			double amountDouble = Math.abs(Utility.truncateDouble(Double.parseDouble(amount.getText()), 2));
			amountDouble = isIncome ? amountDouble : amountDouble * (-1);
			
			/*bank account we will do the transaction*/
			BankAccountLogic bal = bankAccount.getValue();
			
			/*Type of transaction*/
			CategoryLogic cl = category.getValue();
			
			/*if (recurrence.isSelected()) {
				String recurrenceText = time.getValue();
			}*/
			
			java.sql.Date sqlDate = java.sql.Date.valueOf(datePicker.getValue());
			if (checkSharedBudget.isSelected()) {
				bl = cbxBudgets.getSelectionModel().getSelectedItem();
			}
			
			if (tr == null) {
				IOTransactionLogic transaction = new IOTransactionLogic(amountDouble, nameText, "toto", sqlDate, "CHF",
						cl, bal, bl);
				
				controller.createItem(transaction);
			} else {
				tr.update(amountDouble, nameText, "toto", sqlDate, "CHF", cl, bal, bl);
				controller.modifyItem(tr);
			}
		}
	}
	
	private void delete() {
		
		controller.deleteItem(tr);
	}
	
	/*private void checkRecurrence() {
		
		if (recurrence.isSelected()) {
			time.setVisible(true);
		} else {
			time.setVisible(false);
		}
	}*/
	
	/**
	 * Method to check if our field is correct
	 *
	 * @return true if all is correct otherwise false
	 */
	private boolean checkValidInput() {
		
		String nameText = name.getText();
		String amountDouble = amount.getText();
		boolean check = true;
		
		/*Check if the bankName is empty*/
		if (nameText.isEmpty()) {
			name.setStyle("-jfx-unfocus-color: red;");
			check = false;
		}
		
		/*Check if the amount is empty and it's not a double*/
		if (amountDouble.isEmpty() || !isDouble(amountDouble)) {
			amount.setStyle("-jfx-unfocus-color: red;-fx-text-fill: red;");
			check = false;
		}
		
		if (!checkSharedBudget.isSelected() && (datePicker.getValue() == null || datePicker.getValue()
				.isAfter(LocalDate.now()))) {
			datePicker.setStyle("-jfx-default-color: red;");
			check = false;
		}
		
		BudgetLogic budgetLogic = cbxBudgets.getValue();
		if (checkSharedBudget.isSelected() && (datePicker.getValue().isAfter(budgetLogic.getEndingDate().toLocalDate())
				|| datePicker.getValue().isBefore(budgetLogic.getStartingDate().toLocalDate()))) {
			datePicker.setStyle("-jfx-default-color: red;");
			check = false;
			
		}
		
		return check;
	}
	
	/**
	 * Check if a string is a double
	 *
	 * @param str string to check
	 *
	 * @return true if is a double otherwise false
	 */
	private boolean isDouble(String str) {
		
		try {
			Double.parseDouble(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	private void generateComboBox() {
		
		ObservableList<BankAccountLogic> items = FXCollections.observableArrayList();
		for (BankAccountLogic bal : ClientLogic.getInstance().getBankAccounts()) {
			if (bal.isDefault()) {
				items.add(0, bal);
			} else {
				items.add(bal);
			}
		}
		bankAccount.setItems(items);
		bankAccount.getSelectionModel().selectFirst();
		
		ObservableList<CategoryLogic> items2 = FXCollections.observableArrayList();
		for (CategoryLogic cl : ClientLogic.getInstance().getCategories()) {
			items2.add(cl);
		}
		category.setItems(items2);
		category.getSelectionModel().selectFirst();
		
		/*ObservableList<String> items3 = FXCollections.observableArrayList();
		items3.addAll("Mensuel", "Annuel");
		time.setItems(items3);
		time.getSelectionModel().selectFirst();*/
		
		ObservableList<BudgetLogic> items3 = FXCollections.observableArrayList();
		for (BudgetLogic bl : ClientLogic.getInstance().getBudgets()) {
			if (bl.isShared()) {
				items3.add(bl);
			}
		}
		cbxBudgets.setItems(items3);
		cbxBudgets.getSelectionModel().selectFirst();
		
	}
	
	/**
	 * Called to initialize a controller after its root element has been completely processed.
	 *
	 * @param location The location used to resolve relative paths for the root object, or null if the location is not
	 * 		known.
	 * @param resources The resources used to localize the root object, or null if the root object was not localized.
	 */
	@Override public void initialize(URL location, ResourceBundle resources) {
		
		
		generateComboBox();
		deleteButton.setVisible(false);
		
		if (tr != null) {
			
			deleteButton.setVisible(true);
			
			name.setText(tr.getName());
			amount.setText(String.valueOf(tr.getAmount()));
			for (BankAccountLogic bal : ClientLogic.getInstance().getBankAccounts()) {
				if (bal.getId() == tr.getBankAccountID()) {
					bankAccount.getSelectionModel().select(bal);
					break;
				}
			}
			
			for (CategoryLogic cl : ClientLogic.getInstance().getCategories()) {
				if (cl.getId() == tr.getCategoryID()) {
					category.getSelectionModel().select(cl);
					break;
				}
			}
			
			datePicker.setValue(tr.getDate().toLocalDate());
			if(tr.getBudget() != null) {
				checkSharedBudget.setSelected(tr.getBudget().isShared());
				cbxBudgets.getSelectionModel().select(tr.getBudget());
			}
			
		}
		
		accepteButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override public void handle(ActionEvent event) {
				
				formValidation(event);
			}
		});
		
		deleteButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override public void handle(ActionEvent event) {
				
				delete();
			}
		});
		
		returnButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override public void handle(ActionEvent event) {
				
				formCancel(event);
			}
		});
		
		/*recurrence.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override public void handle(ActionEvent event) {
				
				checkRecurrence();
			}
		});*/
		
		name.setOnMouseClicked(new EventHandler<MouseEvent>() {
			
			@Override public void handle(MouseEvent event) {
				
				name.setStyle("-jfx-unfocus-color: black;");
			}
		});
		
		amount.setOnMouseClicked(new EventHandler<MouseEvent>() {
			
			@Override public void handle(MouseEvent event) {
				
				amount.setStyle("-jfx-unfocus-color: black;-fx-text-fill: black;");
			}
		});
		
		datePicker.setOnMouseClicked(new EventHandler<MouseEvent>() {
			
			@Override public void handle(MouseEvent event) {
				
				datePicker.setStyle("-jfx-default-color: green;");
			}
		});
		
		checkSharedBudget.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override public void handle(ActionEvent event) {
				
				cbxBudgets.setVisible(checkSharedBudget.isSelected());
			}
		});
		
		if(!ClientLogic.getInstance().isOnline()){
			/*recurrence.setDisable(true);
			recurrence.setSelected(false);*/
			checkSharedBudget.setDisable(true);
		}
	}
}
