package gui.controller.debt;

import bll.logic.ClientLogic;
import bll.logic.DebtLogic;
import bll.model.ClientModel;
import com.jfoenix.controls.*;
import gui.controller.IController;
import gui.controller.IForm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * @author Bryan Curchod
 * @version 1.0
 */
public class Controller_formDebt implements Initializable, IForm {
	
	@FXML private JFXButton btnCancel;
	@FXML private JFXButton btnValider;
	@FXML private JFXButton btnDelete;
	@FXML private JFXTextField txtAmount;
	@FXML private JFXDatePicker dateLimite;
	@FXML private JFXComboBox<ClientModel> cbbOtherUser;
	@FXML private JFXTextField txtNom;
	@FXML private JFXTextArea txtDescription;
	@FXML private JFXToggleButton tglDebitor;
	
	DebtLogic debt;
	IController parent;
	boolean isIncome;
	
	Controller_formDebt(IController caller, DebtLogic d, boolean isClaim) {
		
		parent = caller;
		debt = d;
		isIncome = isClaim;
	}
	
	@Override public void initialize(URL location, ResourceBundle resources) {
		
		// gather every client except the current user
		ObservableList<ClientModel> UserItem = FXCollections.observableArrayList();
		for(ClientModel u : ClientLogic.getInstance().getAllUsers()){
			if(u.getId() != ClientLogic.getInstance().getId()){
				UserItem.add(u);
			}
		}
		
		if (debt != null) {
			txtAmount.setText(Double.toString(debt.getAmount()));
			txtNom.setText(debt.getName());
			txtDescription.setText(debt.getDescription());
			dateLimite.setValue(LocalDate.parse(debt.getExpirationDate().toString()));
			cbbOtherUser.setValue(debt.getContributor());
			btnDelete.setVisible(true);
		}
		
		cbbOtherUser.setItems(UserItem);
		btnCancel.setOnAction(this::formCancel);
		btnValider.setOnAction(this::formValidation);
		btnDelete.setOnAction(event -> parent.deleteItem(debt));
		
		// disable the user selection in offline mode
		if(ClientLogic.getInstance().isOnline()){
			cbbOtherUser.setDisable(true);
		}
	}
	
	@Override public void formValidation(ActionEvent event) {
		
		if (debt == null) {
			ClientModel uConcerned = cbbOtherUser.getValue();
			debt = new DebtLogic(txtNom.getText(), txtDescription.getText(), Double.parseDouble(txtAmount.getText()), isIncome,
					Date.valueOf(dateLimite.getValue()),uConcerned);
			parent.createItem(debt);
		} else {
			debt.update(txtNom.getText(), txtDescription.getText(), Double.parseDouble(txtAmount.getText()), Date.valueOf(dateLimite.getValue()));
			parent.modifyItem(parent);
		}
	}
	
	@Override public void formCancel(ActionEvent event) {
		parent.createItem(null);
	}
}
