package gui.controller;

import bll.logic.BankAccountLogic;
import bll.logic.ClientLogic;
import bll.logic.IOTransactionLogic;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXNodesList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

/**
 * @author Bryan Curchod
 * @version 1.0
 */
public class Controller_dashboard implements IController, Initializable {
	
	@FXML private AnchorPane paneGraphe1;
	@FXML private AnchorPane paneGraph2;
	@FXML private VBox paneList;
	@FXML private JFXNodesList nodeAjout;
	@FXML private AnchorPane paneForm;
	@FXML private PieChart chartPie;
	@FXML private LineChart<?, ?> chartLine;
	
	private JFXButton btnAddTransaction = new JFXButton("+");
	private JFXButton btnOutgo = new JFXButton("D");
	private JFXButton btnIncome = new JFXButton("R");
	
	private class transactionDisplayer {
		
		private Label lblDate;
		private Label lblCaption;
		private Label lblPrix;
		private GridPane paneDisplay;
		
		private IOTransactionLogic transaction;
		private final String incomeColor = "#8ce589";
		private final String outgoColor = "#f2a7a8";
		
		transactionDisplayer(IOTransactionLogic t) {
			
			transaction = t;
			
			lblDate = new Label(transaction.getDate().toString());
			lblCaption = new Label(transaction.getName());
			lblPrix = new Label(Double.toString(transaction.getAmount()));
			paneDisplay = new GridPane();
			
			paneDisplay.getChildren().add(lblDate);
			paneDisplay.getChildren().add(lblCaption);
			paneDisplay.getChildren().add(lblPrix);
			
			
			paneDisplay.setConstraints(lblDate, 0, 0, 0, 0, HPos.LEFT, VPos.CENTER);
			paneDisplay.setConstraints(lblCaption, 1, 0, 0, 0, HPos.CENTER, VPos.CENTER);
			paneDisplay.setConstraints(lblPrix, 2, 0, 0, 0, HPos.RIGHT, VPos.CENTER);
			
			if (transaction.isIncome()) {
				paneDisplay.setStyle("-fx-background-radius: 10px; -fx-background-color: " + incomeColor + ";");
			} else {
				paneDisplay.setStyle("-fx-background-radius: 10px; -fx-background-color: " + outgoColor + ";");
			}
			
			Controller_dashboard.this.paneList.getChildren().add(paneDisplay);
		}
	}
	
	private void callForm(boolean isIncome) {
		
		/* we load the form fxml*/
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/formTransaction.fxml"));
		
		/*Create a instance of the controller of bank account form*/
		Controller_formTransaction controller = new Controller_formTransaction(this, isIncome, null);
		
		/*Sets the controller associated with the root object*/
		loader.setController(controller);
		
		paneForm.setVisible(true);
		paneForm.setMouseTransparent(false);
		
		try {
			
			AnchorPane pane = loader.load();
			paneForm.getChildren().add(pane);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	private void unloadform() {
		
		paneForm.getChildren().clear();
		paneForm.setMouseTransparent(true);
		paneForm.setVisible(false);
	}
	@Override public void createItem(Object result) {
		unloadform();
		if(result != null){
			// TODO création du transactionDisplayer
		}
	}
	
	@Override public void deleteItem(Object toDelete) {
		// PAS UTILISE
	}
	
	@Override public void modifyItem(Object toUpdated) {
		// PAS UTILISE
	}
	
	@Override public void initialize(URL location, ResourceBundle resources) {
		
		paneForm.setVisible(false);
		paneForm.setMouseTransparent(true);
		
		initNodeListe();
		
		btnOutgo.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override public void handle(ActionEvent event) {
				
				callForm(false);
			}
		});
		
		btnIncome.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override public void handle(ActionEvent event) {
				callForm(true);
			}
		});
		
		// TODO line chart : progression du/des comptes bancaire pour les 30 derniers jours
		setDataLineChart();
		
		// TODO pie chart : répartition des dépenses des 30 derniers jours par catégories
		
		// TODO lister les 30 dernière transaction dans le paneList (juste créer les displayer)
		
		
	}
	
	private void setDataLineChart() {
		
		BankAccountLogic bal = new BankAccountLogic();
		
		for (BankAccountLogic b : ClientLogic.getInstance().getBankAccounts()) {
			if (b.isDefault()) {
				bal = b;
				break;
			}
		}
		XYChart.Series series = new XYChart.Series();
		series.setName("Evolution du solde");
		
		double solde = bal.getAmount();
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
		int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		
		// Create a calendar object and set year and month
		Calendar mycal = new GregorianCalendar(currentYear, currentMonth, currentDay);
		
		// Get the number of days in that month
		int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		if (!bal.getTransactions().isEmpty()) {
			for (IOTransactionLogic transaction : bal.getTransactions().get(currentYear)[currentMonth]) {
				solde -= transaction.getAmount();
			}
		}
		
		for (int i = 0; i < currentDay; ++i) {
			if (!bal.getTransactions().isEmpty()) {
				for (IOTransactionLogic transaction : bal.getTransactions().get(currentYear)[currentMonth]) {
					if (transaction.getDate().getDay() - 1 == i) {
						solde += transaction.getAmount();
					}
				}
			}
			
			series.getData().add(new XYChart.Data(String.valueOf(i + 1), solde));
		}
		
		chartLine.getData().addAll(series);
	}
	
	private void initNodeListe() {
		
		btnAddTransaction = new JFXButton("+");
		btnAddTransaction.setButtonType(JFXButton.ButtonType.RAISED);
		btnAddTransaction.getStyleClass().addAll("dashboard-animate-button", "dashboard-animate-button-sub");
		btnOutgo = new JFXButton("Dep");
		btnOutgo.setButtonType(JFXButton.ButtonType.RAISED);
		btnOutgo.getStyleClass().addAll("dashboard-animate-button", "dashboard-animate-button-sub-2");
		btnIncome = new JFXButton("Rev");
		btnIncome.setButtonType(JFXButton.ButtonType.RAISED);
		btnIncome.getStyleClass().addAll("dashboard-animate-button", "dashboard-animate-button-sub-2");
		
		nodeAjout.addAnimatedNode(btnAddTransaction);
		nodeAjout.addAnimatedNode(btnOutgo);
		nodeAjout.addAnimatedNode(btnIncome);
		nodeAjout.setSpacing(5d);
		nodeAjout.setRotate(180);
		
	}
}
