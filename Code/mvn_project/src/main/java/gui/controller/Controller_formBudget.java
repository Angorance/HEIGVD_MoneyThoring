package gui.controller;

import bll.logic.BudgetLogic;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Bryan Curchod
 * @version 1.0
 */
public class Controller_formBudget implements IForm, Initializable {
	
	@FXML private JFXTextField txtName;
	@FXML private JFXTextField txtCeiling;
	@FXML private JFXComboBox<?> cmbCategorySelect;
	@FXML private FlowPane paneCategoryList;
	@FXML private JFXButton btnCancel;
	@FXML private JFXButton btnValidation;
	@FXML private JFXButton btnDelete;
	
	private BudgetLogic budget;
	private IController parent;
	
	public Controller_formBudget(IController parent, BudgetLogic budget) {
		this.parent = parent;
		this.budget = budget;
	}
	
	@Override public void formValidation(ActionEvent event) {
		if(budget == null){
			// TODO création de budget
			// retour au parent
			parent.createItem(budget);
		} else {
			// TODO édition du budget
			// retour au parent
			parent.modifyItem(budget);
		}
	
	}
	
	@Override public void formCancel(ActionEvent event) {
		parent.createItem(null);
	}
	
	@Override public void initialize(URL location, ResourceBundle resources) {
		if(budget != null){
			btnDelete.setVisible(true);
			//TODO remplir les champs
		}
		
		// TODO set la liste de catégories disponible
		
		btnCancel.setOnAction(this::formCancel);
		btnValidation.setOnAction(this::formValidation);
		btnDelete.setOnAction(event -> parent.deleteItem(budget));
	
	}
}
