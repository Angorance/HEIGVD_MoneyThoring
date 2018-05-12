package gui.controller;

import bll.logic.DebtLogic;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Bryan Curchod
 * @version 1.0
 */
public class Controller_formDebt implements Initializable, IForm {
	
	@FXML private JFXButton btnCancel;
	@FXML private JFXButton btnValider;
	@FXML private JFXTextField txtAmount;
	@FXML private JFXDatePicker dateLimite;
	@FXML private JFXComboBox<?> cbbDebtor;
	
	DebtLogic debt;
	IController parent;
	boolean isIncome;
	
	Controller_formDebt(IController caller, DebtLogic d, boolean isClaim){
		parent = caller;
		debt = d;
		isIncome = isClaim;
	}
	
	@Override public void initialize(URL location, ResourceBundle resources) {
		if(debt != null){
			// TODO remplir les champs avec les informations de la dette
		}
		
		btnCancel.setOnAction(this::formCancel);
		btnValider.setOnAction(this::formValidation);
	}
	
	@Override public void formValidation(ActionEvent event) {
		if(debt == null){
			// TODO créer la dette
		} else {
			// TODO modier la dette
		}
	}
	
	@Override public void formCancel(ActionEvent event) {
		parent.createItem(null);
	}
}
