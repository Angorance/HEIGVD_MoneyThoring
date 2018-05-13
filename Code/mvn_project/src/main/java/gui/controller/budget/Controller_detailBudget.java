package gui.controller.budget;

import bll.logic.BudgetLogic;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXNodesList;
import com.jfoenix.controls.JFXProgressBar;
import gui.controller.IController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Bryan Curchod
 * @version 1.0
 */
public class Controller_detailBudget implements Initializable, IController {
	
	@FXML private Label lblTitre;
	@FXML private Label lblSolde;
	@FXML private Label lblPlafond;
	@FXML private JFXProgressBar progessBar;
	@FXML private JFXNodesList nodeModifDelete;
	@FXML private AnchorPane graphPane;
	@FXML private ScrollPane scrollpane;
	@FXML private AnchorPane paneForm;
	@FXML private JFXButton btnRetour;
	
	JFXButton btnEdit;
	JFXButton btnDelete;
	JFXButton btnMenu;
	
	BudgetLogic budget;
	IController parent;
	
	public Controller_detailBudget(IController p, BudgetLogic b) {
		
		parent = p;
		budget = b;
	}
	
	@Override public void initialize(URL location, ResourceBundle resources) {
		
		scrollpane.setStyle("-fx-background-color: transparent");
		
		// nodelist initialisation
		btnEdit = new JFXButton("e");
		btnEdit.setButtonType(JFXButton.ButtonType.FLAT);
		btnEdit.getStyleClass().addAll("transaction-animate-button", "transaction-animate-button-sub-2");
		btnEdit.setOnAction(event -> callForm(budget));
		
		btnDelete = new JFXButton("d");
		btnDelete.setButtonType(JFXButton.ButtonType.FLAT);
		btnDelete.getStyleClass().addAll("transaction-animate-button", "transaction-animate-button-sub-2");
		btnDelete.setOnAction(event -> deleteItem(budget));
		
		btnMenu = new JFXButton("v");
		btnMenu.setButtonType(JFXButton.ButtonType.FLAT);
		btnMenu.getStyleClass().addAll("transaction-animate-button", "transaction-animate-button-sub");
		
		nodeModifDelete.addAnimatedNode(btnMenu);
		nodeModifDelete.addAnimatedNode(btnEdit);
		nodeModifDelete.addAnimatedNode(btnDelete);
		nodeModifDelete.setSpacing(5d);
		
		paneForm.setVisible(false);
		paneForm.setMouseTransparent(true);
		
		btnRetour.setOnAction(event -> parent.modifyItem(budget));

		lblTitre.setText(budget.getName());
		lblPlafond.setText(String.valueOf(budget.getAmount()));
		// TODO initialiser les champs
		// TODO ajouter le graphique (barre ? circulaire ?)
		// TODO lister les dépenses
		
	}
	
	private void callForm(BudgetLogic b) {
		
		paneForm.setVisible(true);
		paneForm.setMouseTransparent(false);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/formBudget.fxml"));
		loader.setController(new Controller_formBudget(this, b));
		
		paneForm.getChildren().clear();
		try {
			paneForm.getChildren().add(loader.load());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override public void createItem(Object result) {
		unloadform();
	}
	
	@Override public void deleteItem(Object toDelete) {
		
		parent.deleteItem(budget);
	}
	
	@Override public void modifyItem(Object toUpdated) {
		unloadform();
		BudgetLogic bl = (BudgetLogic)toUpdated;
		lblTitre.setText(bl.getName());
		lblPlafond.setText(String.valueOf(bl.getAmount()));
		//TODO progresse bar
		//TODO Dépense
		//TODO Refresh les graphique
	}
	
	private void unloadform() {
		
		paneForm.getChildren().clear();
		paneForm.setMouseTransparent(true);
		paneForm.setVisible(false);
	}
}
