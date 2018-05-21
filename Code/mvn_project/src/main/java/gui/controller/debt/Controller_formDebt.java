package gui.controller.debt;

import bll.logic.ClientLogic;
import bll.logic.DebtLogic;
import bll.model.ClientModel;
import com.jfoenix.controls.*;
import gui.Utility;
import gui.controller.IController;
import gui.controller.IForm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextFormatter;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * form to create/edit/delete a debt
 *
 * @author Bryan Curchod
 * @version 1.2
 */
public class Controller_formDebt implements Initializable, IForm {
	
	private static final int MAX_CHARS = 254;
	@FXML
	private JFXButton btnCancel;
	@FXML
	private JFXButton btnValider;
	@FXML
	private JFXButton btnDelete;
	@FXML
	private JFXTextField txtAmount;
	@FXML
	private JFXDatePicker dateLimite;
	@FXML
	private JFXComboBox<ClientModel> cbbOtherUser;
	@FXML
	private JFXTextField txtNom;
	@FXML
	private JFXTextArea txtDescription;
	@FXML
	private JFXToggleButton tglDebitor;
	
	DebtLogic debt;
	IController parent;
	boolean isIncome;
	
	/**
	 * constructor, stock the caller controller reference,
	 * the object to handle and if the object is a debt or a claim
	 *
	 * @param caller controller that called the form
	 * @param d object to edit
	 * @param isClaim define if the object is a claim or a debt for the
	 * 		creator
	 */
	Controller_formDebt(IController caller, DebtLogic d, boolean isClaim) {
		
		parent = caller;
		debt = d;
		isIncome = isClaim;
	}
	
	/**
	 * Called to initialize a controller after its root element has been
	 * completely processed.
	 * set up the fields
	 *
	 * @param location The location used to resolve relative paths for the
	 * 		root object, or null if the location is not
	 * 		known.
	 * @param resources The resources used to localize the root object, or
	 * 		null if the root object was not localized.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// gather every client except the current user
		ObservableList<ClientModel> UserItem = FXCollections
				.observableArrayList();
		for (ClientModel u : ClientLogic.getInstance().getAllUsers()) {
			if (u.getId() != ClientLogic.getInstance().getId()) {
				UserItem.add(u);
			}
		}
		
		if (debt != null) {
			txtAmount.setText(Double.toString(debt.getAmount()));
			txtNom.setText(debt.getName());
			txtDescription.setText(debt.getDescription());
			dateLimite.setValue(
					LocalDate.parse(debt.getExpirationDate().toString()));
			cbbOtherUser.setValue(debt.getContributor());
			btnDelete.setVisible(true);
			
			if (debt.getCreatorID() != ClientLogic.getInstance().getId()) {
				btnValider.setDisable(true);
				btnDelete.setDisable(true);
			}
		}
		
		txtDescription.setTextFormatter(new TextFormatter<String>(
				change -> change.getControlNewText().length() <= MAX_CHARS
						? change : null));
		
		cbbOtherUser.setItems(UserItem);
		btnCancel.setOnAction(this::formCancel);
		btnValider.setOnAction(this::formValidation);
		btnDelete.setOnAction(event -> parent.deleteItem(debt));
		
		// disable the user selection in offline mode
		if (!ClientLogic.getInstance().isOnline()) {
			cbbOtherUser.setDisable(true);
		}
	}
	
	/**
	 * validate the user's input and create/edit the object
	 *
	 * @param event
	 */
	@Override
	public void formValidation(ActionEvent event) {
		
		if (checkValidInput()) {
			
			Double effectiveValue = Utility
					.truncateDouble(Double.valueOf(txtAmount.getText()), 2);
			if (debt == null) {
				ClientModel uConcerned = cbbOtherUser.getValue();
				debt = new DebtLogic(txtNom.getText(), txtDescription.getText(),
						Math.abs(effectiveValue), isIncome,
						Date.valueOf(dateLimite.getValue()), uConcerned);
				parent.createItem(debt);
			} else {
				debt.update(txtNom.getText(), txtDescription.getText(),
						Math.abs(effectiveValue),
						Date.valueOf(dateLimite.getValue()));
				parent.modifyItem(debt);
			}
		}
	}
	
	/**
	 * Check the user input and highlight the incorrect one
	 *
	 * @return true if everything is acceptable
	 */
	private boolean checkValidInput() {
		
		boolean allGood = true;
		if (txtNom.getText().isEmpty()) {
			txtNom.setStyle("-jfx-unfocus-color: red;");
			allGood = false;
		}
		
		if (dateLimite.getValue() == null) {
			dateLimite.setStyle("-jfx-unfocus-color: red;");
			allGood = false;
		}
		
		if (txtAmount.getText().isEmpty() || !Utility
				.isDouble(txtAmount.getText())) {
			txtAmount.setStyle("-jfx-unfocus-color: red;");
			allGood = false;
			
		}
		
		
		return allGood;
	}
	
	/**
	 * cancel the form operation
	 *
	 * @param event calling event information
	 */
	@Override
	public void formCancel(ActionEvent event) {
		
		parent.createItem(null);
	}
}
