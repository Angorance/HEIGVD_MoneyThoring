package gui.controller.budget;

import bll.logic.BudgetLogic;
import bll.logic.ClientLogic;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.effects.JFXDepthManager;
import gui.controller.IController;
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
public class Controller_listBudget implements IController, Initializable {
	
	@FXML private VBox paneList;
	@FXML private JFXButton btnAdd;
	@FXML private AnchorPane paneForm;
	@FXML private ScrollPane scrollPane;
	
	HashMap<Integer, budgetDisplayer> displayerList;
	
	private class budgetDisplayer extends AnchorPane implements Initializable {
		@FXML private AnchorPane budgetPane;
		@FXML private Label lbltitre;
		@FXML private Label lblcurrentExpense;
		@FXML private Label lblmaxExpense;
		@FXML private JFXProgressBar expenseProgress;
		private BudgetLogic budget;
		
		budgetDisplayer(BudgetLogic budget){
			JFXDepthManager.setDepth(this, 1);
			this.budget = budget;
		}
		
		private void openDetail() {

			paneForm.setVisible(true);
			paneForm.setMouseTransparent(false);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/budgetDetail.fxml"));
			loader.setController(new Controller_detailBudget(Controller_listBudget.this, budget));
			
			paneForm.getChildren().clear();
			try {
				paneForm.getChildren().add(loader.load());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override public void initialize(URL location, ResourceBundle resources) {
			redraw();

			budgetPane.setMinHeight(130);
			JFXDepthManager.setDepth(budgetPane, 1);
			budgetPane.setOnMouseClicked(event -> openDetail());
			
			
		}

		public void redraw() {
			lbltitre.setText(budget.getName());
			lbltitre.setStyle("-fx-font-size: 24");
			lblcurrentExpense.setText("Dépenses actuelles : 999.99 CHF"); // TODO calculer les dépenses totales du budget
			lblmaxExpense.setText("Plafond : "+ Double.toString(budget.getAmount()) +"CHF");
			expenseProgress.setProgress(0.5); // TODO Dépensé/plafond
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
		
		unloadform();
		if (toDelete != null) {
			BudgetLogic b = (BudgetLogic) toDelete;
			paneList.getChildren().remove(displayerList.get(b.getId()));
			b.supp();
		}
	}

	/**
	 * update the datas in the DB and refresh
	 * @param updated
	 */
	@Override public void modifyItem(Object updated) {
		unloadform();
		BudgetLogic b = (BudgetLogic)updated;
		displayerList.get(b.getId()).redraw();
	}
	
	@Override public void createItem(Object result) {
		unloadform();
		
		if(result != null) {
			
			BudgetLogic b = (BudgetLogic) result;
			add(b);
		}
	}
	
	private void unloadform() {
		
		paneForm.getChildren().clear();
		paneForm.setMouseTransparent(true);
		paneForm.setVisible(false);
	}
	
	private void add(BudgetLogic b){
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/budgetDisplayer.fxml"));
		budgetDisplayer bd = new budgetDisplayer(b);
		loader.setController(bd);
		try {
			bd = loader.load();
			paneList.getChildren().add(bd);
			displayerList.put(b.getId(), bd);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override public void initialize(URL location, ResourceBundle resources) {
		btnAdd.setOnAction(event -> callform(null));
		paneForm.setVisible(false);
		paneForm.setMouseTransparent(true);

		// event on the click of the button
		scrollPane.setStyle("-fx-background-color:transparent;");
		
		displayerList = new HashMap<>();
		// we set the basics data
		for(BudgetLogic b: ClientLogic.getInstance().getBudgets()){
			add(b);
		}
	}
}
