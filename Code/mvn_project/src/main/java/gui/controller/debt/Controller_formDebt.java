package gui.controller.debt;

import bll.logic.DebtLogic;
import bll.model.ClientModel;
import com.jfoenix.controls.*;
import gui.controller.IController;
import gui.controller.IForm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

/**
 * @author Bryan Curchod
 * @version 1.0
 */
public class Controller_formDebt implements Initializable, IForm {
	
	@FXML
	private JFXButton btnCancel;
	@FXML
	private JFXButton btnValider;
	@FXML
	private JFXTextField txtAmount;
	@FXML
	private JFXDatePicker dateLimite;
	@FXML
	private JFXComboBox<ClientModel> cbbOtherUser;
	@FXML
	private JFXTextField txtDescription;
	@FXML
	private JFXToggleButton tglDebitor;
	
	DebtLogic debt;
	IController parent;
	boolean isIncome;
	
	Controller_formDebt(IController caller, DebtLogic d, boolean isClaim) {
		
		parent = caller;
		debt = d;
		isIncome = isClaim;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		if (debt != null) {
			// TODO remplir les champs avec les informations de la dette
		}
		
		
		// TODO désactiver le champ du choix de l'utilisateur et de la récurrence si on est déconnecté
		btnCancel.setOnAction(this::formCancel);
		btnValider.setOnAction(this::formValidation);
	}
	
	@Override
	public void formValidation(ActionEvent event) {
		
		if (debt == null) {
			
			// TODO - Gérer le cas d'une dette simple (sans deuxième utilisateur !)
			debt = new DebtLogic("debt", txtDescription.getText(),
					Double.parseDouble(txtAmount.getText()), isIncome,
					Date.valueOf(dateLimite.getValue()),
					cbbOtherUser.getValue());
			parent.createItem(debt);
		} else {
			// TODO modier la dette
			parent.modifyItem(parent);
		}
	}
	
	@Override
	public void formCancel(ActionEvent event) {
		
		parent.createItem(null);
	}
}
