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

import java.net.URL;
import java.util.ResourceBundle;

public class Controller_formModBankAccount implements Initializable,IForm {
	@FXML private JFXTextField nameAccount;
	@FXML private JFXTextField nameBankAccount;
	@FXML private JFXTextField amount;
	@FXML private JFXComboBox<String> typeAccount;
	@FXML private JFXButton returnButton;
	@FXML private JFXButton accepteButton;
	@FXML private JFXCheckBox defaultAccount;
	
	Controller_detailBankAccount cdb;
	BankAccountLogic bal;
	
	Controller_formModBankAccount(Controller_detailBankAccount cdb, BankAccountLogic bal){
		this.cdb = cdb;
		this.bal = bal;
	}
	
	
	/**
	 * Return to the previous window
	 *
	 * @param event
	 */
	@FXML @Override public void formCancel(ActionEvent event) {
		
		cdb.formReturn(null);
	}
	
	/**
	 * TODO
	 *
	 * @param event
	 */
	@FXML @Override public void formValidation(ActionEvent event) {
		
		if (checkValidInput()) {
			String name = nameAccount.getText();
			String nameBankAccount = nameAccount.getText();
			double amountDouble = Double.parseDouble(amount.getText());
			String type = typeAccount.getValue();
			boolean isDefault = defaultAccount.isSelected();
			cdb.modify(name, nameBankAccount,type,amountDouble,isDefault);
			cdb.formReturn(null);
		}
	}
	
	private boolean checkValidInput() {
		// on vérifie tous les champs, s'ils sont erronés on les mets en rouge
		return true;
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
		
		nameAccount.setText(bal.getName());
		nameBankAccount.setText(bal.getName());
		amount.setText(String.valueOf(bal.getAmount()));
		typeAccount.getSelectionModel().select(bal.getType());
		defaultAccount.setSelected(bal.isDefault());
		
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
	}
}
