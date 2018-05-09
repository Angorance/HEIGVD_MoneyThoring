package gui.controller;

import bll.logic.IOTransactionLogic;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXNodesList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.net.URL;
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
		
		transactionDisplayer(IOTransactionLogic t){
			transaction = t;
			
			lblDate = new Label(transaction.getDate().toString());
			lblCaption = new Label(transaction.getName());
			lblPrix = new Label(Double.toString(transaction.getAmount()));
			paneDisplay = new GridPane();
			
			paneDisplay.getChildren().add(lblDate);
			paneDisplay.getChildren().add(lblCaption);
			paneDisplay.getChildren().add(lblPrix);
			
			
			paneDisplay.setConstraints(lblDate, 0,0,0,0,HPos.LEFT, VPos.CENTER);
			paneDisplay.setConstraints(lblCaption, 1,0,0,0,HPos.CENTER, VPos.CENTER);
			paneDisplay.setConstraints(lblPrix, 2,0,0,0,HPos.RIGHT, VPos.CENTER);
			
			if(transaction.isIncome()) {
				paneDisplay.setStyle("-fx-background-radius: 10px; -fx-background-color: " + incomeColor + ";");
			} else {
				paneDisplay.setStyle("-fx-background-radius: 10px; -fx-background-color: " + outgoColor + ";");
			}
			
			Controller_dashboard.this.paneList.getChildren().add(paneDisplay);
		}
	}
	
	@Override public void createItem(Object result) {
		// TODO création de la transaction
	}
	
	private void callForm(IOTransactionLogic t){
		 // TODO loader le formulaire dans paneForm (s'inspirer des autrees vues)
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
		btnAddTransaction.setOnAction(event -> callForm(null));
		
		//TODO CREATION DE TRANSACTION (set les onAction de btnIncome et btnOutgo) (françois, peut-être ?)
		initNodeListe();
		
		// TODO line chart : progression du/des comptes bancaire pour les 30 derniers jours
		
		// TODO pie chart : répartition des dépenses des 30 derniers jours par catégories
		
		// TODO lister les 30 dernière transaction dans le paneList (juste créer les displayer)
		
		
	
	}
	
	private void initNodeListe() {
		
		btnAddTransaction = new JFXButton("+");
		btnAddTransaction.setButtonType(JFXButton.ButtonType.RAISED);
		btnAddTransaction.getStyleClass().addAll("transaction-animate-button", "transaction-animate-button-sub");
		btnOutgo = new JFXButton("Dep");
		btnOutgo.setButtonType(JFXButton.ButtonType.RAISED);
		btnOutgo.getStyleClass().addAll("transaction-animate-button", "transaction-animate-button-sub-2");
		btnIncome = new JFXButton("Rev");
		btnIncome.setButtonType(JFXButton.ButtonType.RAISED);
		btnIncome.getStyleClass().addAll("transaction-animate-button", "transaction-animate-button-sub-2");
		
		nodeAjout.addAnimatedNode(btnAddTransaction);
		nodeAjout.addAnimatedNode(btnOutgo);
		nodeAjout.addAnimatedNode(btnIncome);
		nodeAjout.setSpacing(5d);
		nodeAjout.setRotate(180);
		
		
	}
}
