package gui.controller;

import bll.logic.BankAccountLogic;
import bll.logic.CategoryLogic;
import bll.logic.ClientLogic;
import bll.logic.IOTransactionLogic;
import bll.model.BankAccountModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class Controller_formTransaction implements Initializable, IForm {
	
	@FXML private JFXTextField name;
	@FXML private JFXTextField amount;
	@FXML private JFXComboBox<String> time;
	@FXML private JFXCheckBox recurrence;
	@FXML private JFXComboBox<BankAccountLogic> bankAccount;
	@FXML private JFXComboBox<CategoryLogic> category;
	@FXML private JFXButton returnButton;
	@FXML private JFXButton accepteButton;
	
	IController controller;
	boolean isIncome;
	
	public Controller_formTransaction(IController controller, boolean isIncome) {
		this.controller = controller;
		this.isIncome = isIncome;
	}
	
	/**
	 * Return to the previous window
	 *
	 * @param event
	 */
	@FXML @Override public void formCancel(ActionEvent event) {
		controller.formReturn(null);
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
			double amountDouble = Math.abs(Double.parseDouble(amount.getText()));
			amountDouble = isIncome ? amountDouble : amountDouble*(-1);
			
			/*bank account we will do the transaction*/
			BankAccountLogic bal = bankAccount.getValue();
			
			/*Type of transaction*/
			CategoryLogic cl = category.getValue();
			
			/*if recurrence*/
			if (recurrence.isSelected()) {
				String recurrenceText = time.getValue();
			}
			
			//Date actuelle
			String format = "dd/MM/yyyy";
			java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat(format);
			java.util.Date date = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			
			IOTransactionLogic transaction = new IOTransactionLogic(amountDouble, nameText, "", sqlDate, "CHF", cl,
					bal);
			controller.formReturn(transaction);
		}
	}
	
	private void checkRecurrence() {
		
		if (recurrence.isSelected()) {
			
			time.setVisible(true);
		} else {
			
			time.setVisible(false);
		}
	}
	
	/**
	 * TODO
	 *
	 * @return
	 */
	private boolean checkValidInput() {
		
		return true;
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
		
		/*ObservableList<CategoryLogic> items2 = FXCollections.observableArrayList();
		for (CategoryLogic cl : ClientLogic.getInstance().getCategories()) {
			items2.add(cl);
		}
		category.setItems(items2);
		category.getSelectionModel().selectFirst();*/
		
		ObservableList<String> items3 = FXCollections.observableArrayList();
		items3.addAll("Mensuel", "Annuel");
		time.setItems(items3);
		time.getSelectionModel().selectFirst();
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
		
		accepteButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override public void handle(ActionEvent event) {
				
				formValidation(event);
			}
		});
		
		returnButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override public void handle(ActionEvent event) {
				
				formCancel(event);
			}
		});
		
		recurrence.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override public void handle(ActionEvent event) {
				
				checkRecurrence();
			}
		});
	}
}
