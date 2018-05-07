package gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.effects.JFXDepthManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Bryan Curchod
 * @version 1.0
 */
public class Controller_budgetList implements IController, Initializable {
	
	@FXML private VBox paneList;
	@FXML private JFXButton btnAdd;
	@FXML private AnchorPane paneForm;
	
	private class budgetDisplayer extends AnchorPane implements Initializable {
		int maxExpense, currentExpense;
		@FXML private AnchorPane budgetPane;
		@FXML Label lbltitre;
		@FXML Label lblcurrentExpense;
		@FXML Label lblmaxExpense;
		@FXML JFXProgressBar expenseProgress;
		// BudgetLogic b;
		
		budgetDisplayer(Object budget){
			/*lbltitre = new Label();
			lblcurrentExpense = new Label();
			lblmaxExpense = new Label();
			expenseProgress = new JFXProgressBar();*/
			//expenseProgress.setMinHeight(15.0);
			
			/*this.setPrefHeight(100);
			
			this.setBackground(new Background(new BackgroundFill(Color.GRAY, new CornerRadii(10), null)));
			
			this.setPadding(new Insets(10));
			this.getChildren().add(lblcurrentExpense);
			this.getChildren().add(lblmaxExpense);
			this.getChildren().add(lbltitre);
			this.getChildren().add(expenseProgress);
			
			/*setLeftAnchor(this, 20.0);
			setRightAnchor(this, 20.0);
			setRightAnchor(lblmaxExpense, 10.0);
			setTopAnchor(lblmaxExpense, 35.0);
			setLeftAnchor(lbltitre, 10.0);
			setTopAnchor(lbltitre, 20.0);
			setLeftAnchor(lblcurrentExpense, 10.0);
			setTopAnchor(lblcurrentExpense, 350.0);
			setLeftAnchor(expenseProgress, 10.0);
			setRightAnchor(expenseProgress, 10.0);
			setBottomAnchor(expenseProgress, 10.0);*/
			
			JFXDepthManager.setDepth(this, 1);
			
		}
		
		private void openDetail() {
			
			System.out.println("ouverture du détail");
			/*
			paneForm.setVisible(true);
			paneForm.setMouseTransparent(false);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/formBudget"));
			loader.setController(new Controller_detailBudget(Controller_budgetList.this, null));
			
			paneForm.getChildren().clear();
			try {
				paneForm.getChildren().add(loader.load());
			} catch (IOException e) {
				e.printStackTrace();
			}*/
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
	 public void callform() {
		
		paneForm.setVisible(true);
		paneForm.setMouseTransparent(false);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/formBudget"));
		loader.setController(new Controller_formBudget(this));
		
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
	@Override
	public void modifyItem(Object updated) {

	}
	
	@Override public void createItem(Object result) {
		paneForm.setVisible(false);
		paneForm.setMouseTransparent(true);
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/budgetDisplayer.fxml"));
		loader.setController(new budgetDisplayer(result));
		try {
			paneList.getChildren().add(loader.load());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override public void deleteItem(Object toDelete) {
	
	}
	
	@Override public void modifyItem(Object toUpdated) {
	
	}
	
	@Override public void initialize(URL location, ResourceBundle resources) {
		// event on the click of the button
		btnAdd.setOnAction(event -> createItem(null));//callform());
		paneForm.setVisible(false);
		paneForm.setMouseTransparent(true);
		// we set the basics data
		/*
		for(BudgetLogic : ClientLogic.getInstance().getBudgets()){
			// on crée les budget displayer
		}
		 */
	}
}
