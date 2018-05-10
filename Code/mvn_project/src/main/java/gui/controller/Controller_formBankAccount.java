package gui.controller;

import bll.logic.BankAccountLogic;
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
import javafx.scene.input.MouseEvent;
import org.apache.derby.iapi.util.StringUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller_formBankAccount implements Initializable, IForm {
	
	@FXML private JFXTextField nameAccount;
	@FXML private JFXTextField nameBankAccount;
	@FXML private JFXTextField amount;
	@FXML private JFXComboBox<String> typeAccount;
	@FXML private JFXButton returnButton;
	@FXML private JFXButton accepteButton;
	@FXML private JFXCheckBox defaultAccount;
	
	private IController cba;
	BankAccountLogic bal;
	
	public Controller_formBankAccount(IController cba, BankAccountLogic bal) {
		
		this.cba = cba;
		this.bal = bal;
	}
	
	/**
	 * Return to the previous window
	 *
	 * @param event
	 */
	@FXML @Override public void formCancel(ActionEvent event) {
		
		cba.createItem(null);
	}
	
	/**
	 * TODO
	 *
	 * @param event
	 */
	@FXML @Override public void formValidation(ActionEvent event) {
		
		if (checkValidInput()) {
			String name = nameAccount.getText();
			String bankName = nameBankAccount.getText();
			String type = (String) typeAccount.getValue();
			Double amountDouble = Double.parseDouble(amount.getText());
			boolean isDefault = defaultAccount.isSelected();
			if (bal == null) {
				BankAccountLogic ba = new BankAccountLogic(name, bankName, type, amountDouble, isDefault, 0);
				cba.createItem(ba);
			} else {
				bal.update(name, bankName, type, amountDouble, isDefault);
				cba.modifyItem(bal);
				cba.createItem(null);
			}
		}
	}
	
	/**
	 * Method to check if our field is correct
	 *
	 * @return true if all is correct otherwise false
	 */
	private boolean checkValidInput() {
		
		String name = nameAccount.getText();
		String bankName = nameBankAccount.getText();
		String amountDouble = amount.getText();
		
		boolean check = true;
		
		/*Check if the name is empty*/
		if (name.isEmpty()) {
			nameAccount.setStyle("-jfx-unfocus-color: red;");
			check = false;
		}
		
		/*Check if the bankName is empty*/
		if (bankName.isEmpty()) {
			nameBankAccount.setStyle("-jfx-unfocus-color: red;");
			check = false;
		}
		
		/*Check if the amount is empty and it's not a double*/
		if (amountDouble.isEmpty() || !isDouble(amountDouble)) {
			amount.setStyle("-jfx-unfocus-color: red;-fx-text-fill: red;");
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
	
	
	private void generateComboBoxItem() {
		
		ObservableList<String> items = FXCollections.observableArrayList();
		items.addAll("Compte d'épargne", "Compte courant");
		typeAccount.setItems(items);
		typeAccount.getSelectionModel().selectFirst();
	}
	
	/**
	 * Called to initialize a controller after its root element has been completely processed.
	 *
	 * @param location The location used to resolve relative paths for the root object, or null if the location is not
	 * 		known.
	 * @param resources The resources used to localize the root object, or null if the root object was not localized.
	 */
	@Override public void initialize(URL location, ResourceBundle resources) {
		
		generateComboBoxItem();
		
		if (bal != null) {
			nameAccount.setText(bal.getName());
			nameBankAccount.setText(bal.getBankName());
			amount.setText(String.valueOf(bal.getAmount()));
			typeAccount.getSelectionModel().select(bal.getType());
			defaultAccount.setSelected(bal.isDefault());
		}
		
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
		
		nameAccount.setOnMouseClicked(new EventHandler<MouseEvent>() {
			
			@Override public void handle(MouseEvent event) {
				
				nameAccount.setStyle("-jfx-unfocus-color: black;");
			}
		});
		
		nameBankAccount.setOnMouseClicked(new EventHandler<MouseEvent>() {
			
			@Override public void handle(MouseEvent event) {
				
				nameBankAccount.setStyle("-jfx-unfocus-color: black;");
			}
		});
		
		amount.setOnMouseClicked(new EventHandler<MouseEvent>() {
			
			@Override public void handle(MouseEvent event) {
				
				amount.setStyle("-jfx-unfocus-color: black;-fx-text-fill: black;");
			}
		});
		
	}
}
