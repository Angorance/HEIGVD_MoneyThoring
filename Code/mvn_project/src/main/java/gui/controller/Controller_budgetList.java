package gui.controller;

import bll.logic.BudgetLogic;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.effects.JFXDepthManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * @author Bryan Curchod
 * @version 1.0
 */
public class Controller_budgetList implements IController, Initializable {
	
	@FXML private VBox paneList;
	@FXML private JFXButton btnAdd;
	@FXML private AnchorPane paneForm;
	@FXML private ScrollPane scrollPane;
	
	HashMap<Integer, budgetDisplayer> displayerList;
	
	private class budgetDisplayer extends AnchorPane implements Initializable {
		int maxExpense, currentExpense;
		@FXML private AnchorPane budgetPane;
		@FXML Label lbltitre;
		@FXML Label lblcurrentExpense;
		@FXML Label lblmaxExpense;
		@FXML JFXProgressBar expenseProgress;
		BudgetLogic budget;
		
		budgetDisplayer(BudgetLogic budget){
			JFXDepthManager.setDepth(this, 1);
			this.budget = budget;
		}
		
		private void openDetail() {

			paneForm.setVisible(true);
			paneForm.setMouseTransparent(false);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/formBudget"));
			loader.setController(new Controller_detailBudget(Controller_budgetList.this, budget));
			
			paneForm.getChildren().clear();
			try {
				paneForm.getChildren().add(loader.load());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override public void initialize(URL location, ResourceBundle resources) {
			lbltitre.setText("TITRE DU BUDGET");
			lbltitre.setStyle("-fx-font-size: 24");
			lblcurrentExpense.setText("Dépenses actuelles : 999.99 CHF");
			lblmaxExpense.setText("Plafond : 9999.99 CHF");
			expenseProgress.setProgress(0.5);
			budgetPane.setMinHeight(130);

			JFXDepthManager.setDepth(budgetPane, 1);
			budgetPane.setOnMouseClicked(event -> openDetail());
		}
	}

	/**
	 * load the form to create a new Budget
	 */
	 public void callform(BudgetLogic budget) {
		
		paneForm.setVisible(true);
		paneForm.setMouseTransparent(false);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/formBudget.fxml"));
		loader.setController(new Controller_formBudget(this, budget));
		
		paneForm.getChildren().clear();
		try {
			paneForm.getChildren().add(loader.load());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Delete the displayer and the data in the DB
	 * @param toDelete
	 */
	@Override
	public void deleteItem(Object toDelete) {

	}

	/**
	 * update the datas in the DB and refresh
	 * @param updated
	 */
	@Override public void modifyItem(Object updated) {

	}
	
	@Override public void createItem(Object result) {
		BudgetLogic b = (BudgetLogic)result;
		paneForm.setVisible(false);
		paneForm.setMouseTransparent(true);
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/budgetDisplayer.fxml"));
		loader.setController(new budgetDisplayer(b));
		try {
			paneList.getChildren().add(loader.load());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override public void initialize(URL location, ResourceBundle resources) {
		// event on the click of the button
		btnAdd.setOnAction(event -> callform(null));
		paneForm.setVisible(false);
		paneForm.setMouseTransparent(true);
		scrollPane.setStyle("-fx-background-color:transparent;");
		// we set the basics data
		/*
		for(BudgetLogic : ClientLogic.getInstance().getBudgets()){
			// on crée les budget displayer
		}
		 */
	}
}
