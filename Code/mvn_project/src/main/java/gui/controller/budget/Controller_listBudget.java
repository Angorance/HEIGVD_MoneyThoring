package gui.controller.budget;

import bll.logic.*;
import bll.model.IOTransactionModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.effects.JFXDepthManager;
import gui.controller.IController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.ResourceBundle;


/**
 * budget list controller
 * manage the budget displayer list
 *
 * @author Bryan Curchod
 * @version 1.3
 */
public class Controller_listBudget implements IController, Initializable {
	
	@FXML
	private VBox paneList;
	@FXML
	private JFXButton btnAdd;
	@FXML
	private AnchorPane paneForm;
	@FXML
	private ScrollPane scrollPane;
	
	HashMap<Integer, budgetDisplayer> displayerList;
	
	/**
	 * Inner class to display a budget information
	 */
	private class budgetDisplayer extends AnchorPane implements Initializable {
		
		@FXML
		private AnchorPane budgetPane;
		@FXML
		private Label lbltitre;
		@FXML
		private Label lblcurrentExpense;
		@FXML
		private Label lblmaxExpense;
		@FXML
		private JFXProgressBar expenseProgress;
		private BudgetLogic budget;
		double outgo;
		
		/**
		 * construct a displayer
		 *
		 * @param budget budget to display
		 */
		budgetDisplayer(BudgetLogic budget) {
			
			JFXDepthManager.setDepth(this, 1);
			this.budget = budget;
			this.setStyle(
					"-fx-background-color: #f0f0f0; -fx-background-radius: 10");
			
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/gui/view/budgetDisplayer.fxml"));
			loader.setController(this);
			try {
				this.getChildren().add(loader.load());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		/**
		 * open the budget's detail view
		 *
		 * @param outgo total budget's outgo
		 */
		private void openDetail(double outgo) {
			
			paneForm.setVisible(true);
			paneForm.setMouseTransparent(false);
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/gui/view/budgetDetail.fxml"));
			loader.setController(
					new Controller_detailBudget(Controller_listBudget.this,
							budget, outgo));
			
			paneForm.getChildren().clear();
			try {
				paneForm.getChildren().add(loader.load());
			} catch (IOException e) {
				e.printStackTrace();
			}
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
			
			redraw();
			budgetPane.setMinHeight(130);
			JFXDepthManager.setDepth(budgetPane, 1);
			budgetPane.setOnMouseClicked(event -> openDetail(outgo));
			
		}
		
		/**
		 * update the fields information
		 */
		public void redraw() {
			
			outgo = totalAmount(budget);
			lbltitre.setText(budget.getName());
			lbltitre.setStyle("-fx-font-size: 24");
			lblcurrentExpense.setText("Dépenses actuelles :" + (outgo * (-1))
					+ " CHF"); // TODO calculer les dépenses totales du budget
			lblmaxExpense.setText(
					"Plafond : " + Double.toString(budget.getAmount()) + "CHF");
			
			double pourcentage = Math.abs(outgo / budget.getAmount());
			expenseProgress.setProgress(pourcentage);
		}
	}
	
	/**
	 * compute the total outgo amount for a budget
	 *
	 * @param budget budget that we want the total outgo
	 *
	 * @return sum of the outgo
	 */
	public static double totalAmount(BudgetLogic budget) {
		
		double outgo = 0;
		LocalDate begin = budget.getStartingDate().toLocalDate().minusDays(1);
		LocalDate end = budget.getEndingDate().toLocalDate().plusDays(1);
		
		if (!budget.isShared()) {
			if (!budget.getCategoriesBudget().isEmpty()) {
				for (CategoryLogic cl : budget.getCategoriesBudget()) {
					
					if (IOTransactionLogic.getTransactionsByCategory()
							.containsKey(cl)) {
						
						for (IOTransactionLogic tr : IOTransactionLogic
								.getTransactionsByCategory().get(cl)) {
							
							LocalDate currentDate = tr.getDate().toLocalDate();
							if (currentDate.isAfter(begin) && currentDate
									.isBefore(end) && !tr.isIncome()) {
								
								outgo += tr.getAmount();
							}
						}
					}
				}
			} else {
				for (BankAccountLogic bal : ClientLogic.getInstance()
						.getBankAccounts()) {
					for (Object year : IOTransactionLogic
							.getYearsWithTransactions().toArray()) {
						for (int i = 0; i < 12; ++i) {
							if (bal.getTransactions().containsKey(year)) {
								for (IOTransactionLogic tr : bal
										.getTransactions()
										.get((Integer) year)[i]) {
									LocalDate currentDate = tr.getDate()
											.toLocalDate();
									if (currentDate.isAfter(begin)
											&& currentDate.isBefore(end) && !tr
											.isIncome()) {
										
										outgo += tr.getAmount();
									}
								}
							}
						}
					}
				}
			}
		} else {
			for (IOTransactionModel tr : IOTransactionLogic
					.getIOTransactionByBudget(budget.getId())) {
				LocalDate currentDate = tr.getDate().toLocalDate();
				if (currentDate.isAfter(begin) && currentDate.isBefore(end)
						&& !tr.isIncome()) {
					
					outgo += tr.getAmount();
				}
			}
		}
		
		return outgo;
	}
	
	/**
	 * load the form to create a new Budget
	 */
	public void callform(BudgetLogic budget) {
		
		paneForm.setVisible(true);
		paneForm.setMouseTransparent(false);
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("/gui/view/formBudget.fxml"));
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
	 *
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
	 *
	 * @param updated
	 */
	@Override
	public void modifyItem(Object updated) {
		
		unloadform();
		BudgetLogic b = (BudgetLogic) updated;
		displayerList.get(b.getId()).redraw();
	}
	
	@Override
	public void createItem(Object result) {
		
		unloadform();
		
		if (result != null) {
			BudgetLogic b = (BudgetLogic) result;
			add(b);
		}
	}
	
	/**
	 * clear the PaneForm
	 */
	private void unloadform() {
		
		paneForm.getChildren().clear();
		paneForm.setMouseTransparent(true);
		paneForm.setVisible(false);
	}
	
	/**
	 * create and display a new displayer
	 *
	 * @param b budget to use
	 */
	private void add(BudgetLogic b) {
		
		budgetDisplayer db = new budgetDisplayer(b);
		paneList.getChildren().add(db);
		displayerList.put(b.getId(), db);
	}
	
	/**
	 * Called to initialize a controller after its root element has been
	 * completely processed.
	 * list every client's budget and set the button's events
	 *
	 * @param location The location used to resolve relative paths for the
	 * 		root object, or null if the location is not
	 * 		known.
	 * @param resources The resources used to localize the root object, or
	 * 		null if the root object was not localized.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		btnAdd.setOnAction(event -> callform(null));
		btnAdd.setText("");
		JFXDepthManager.setDepth(btnAdd, 1);
		ImageView image = new ImageView(new Image(
				getClass().getResourceAsStream("/gui/Image/add.png")));
		image.setFitWidth(20);
		image.setFitHeight(20);
		btnAdd.setGraphic(image);
		paneForm.setVisible(false);
		paneForm.setMouseTransparent(true);
		
		scrollPane.setStyle("-fx-background-color:transparent;");
		
		displayerList = new HashMap<>();
		// we set the basics data
		for (BudgetLogic b : ClientLogic.getInstance().getBudgets()) {
			add(b);
		}
	}
}
