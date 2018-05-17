package gui.controller.dashboard;

import bll.logic.BankAccountLogic;
import bll.logic.CategoryLogic;
import bll.logic.ClientLogic;
import bll.logic.IOTransactionLogic;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXNodesList;
import com.jfoenix.effects.JFXDepthManager;
import gui.controller.category.Controller_listCategory;
import gui.controller.transaction.Controller_formTransaction;
import gui.controller.IController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.beans.binding.Bindings;
import javafx.scene.paint.Color;

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
	
	private BankAccountLogic bal;
	
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
			double width = paneList.getWidth() / 3;
			lblDate = new Label(transaction.getDate().toString());
			lblCaption = new Label(transaction.getName());
			lblPrix = new Label(Double.toString(transaction.getAmount()) + " CHF");
			paneDisplay = new GridPane();
			
			paneDisplay.getChildren().add(lblDate);
			paneDisplay.getChildren().add(lblCaption);
			paneDisplay.getChildren().add(lblPrix);
			paneDisplay.setPadding(new Insets(10));
			
			paneDisplay.setConstraints(lblDate, 0, 0, 1, 1, HPos.LEFT, VPos.CENTER, Priority.SOMETIMES,Priority.ALWAYS);
			paneDisplay.setConstraints(lblCaption, 1, 0, 1, 1, HPos.LEFT, VPos.CENTER, Priority.SOMETIMES,Priority.ALWAYS);
			paneDisplay.setConstraints(lblPrix, 2, 0, 1, 1, HPos.RIGHT, VPos.CENTER, Priority.SOMETIMES,Priority.ALWAYS);
			
			/*if (transaction.isIncome()) {
				paneDisplay.setStyle("-fx-background-radius: 10px; -fx-background-color: " + incomeColor + ";");
			} else {
				paneDisplay.setStyle("-fx-background-radius: 10px; -fx-background-color: " + outgoColor + ";");
			}*/
			
			paneDisplay.setStyle("-fx-background-radius: 10px; -fx-background-color: " +
					Controller_listCategory.toRGBCode(Color.valueOf(t.getCategory().getColor())) + ";");
			
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
		if (result != null) {
			IOTransactionLogic tr = (IOTransactionLogic) result;
			int year = Calendar.getInstance().get(Calendar.YEAR);
			int month = Calendar.getInstance().get(Calendar.MONTH);
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(tr.getDate());
			int trYear = cal.get(Calendar.YEAR);
			int trMonth = cal.get(Calendar.MONTH);
			
			if((year == trYear) && (month == trMonth) && tr.getBankAccountID() == bal.getId()) {
				transactionDisplayer trDisplayer = new transactionDisplayer(tr);
				setDataLineChart();
				setDataPieChart();
			}
			
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
		
		for (BankAccountLogic b : ClientLogic.getInstance().getBankAccounts()) {
			if (b.isDefault()) {
				bal = b;
				break;
			}
		}
		
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
		
		if(bal != null) {
			setDataLineChart();
			
			setDataPieChart();
			
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			
			if(bal.getTransactions().containsKey(year)) {
				for (IOTransactionLogic tr : bal.getTransactions().get(year)[month]) {
					
					transactionDisplayer trD = new transactionDisplayer(tr);
				}
			}
		}
		
/*		JFXDepthManager.setDepth(paneGraph2, 1);
		JFXDepthManager.setDepth(paneGraphe1, 1);
		JFXDepthManager.setDepth(paneList, 1);*/
		
	}
	
	private void setDataLineChart() {
		
		chartLine.setTitle("Evolution du Solde");
		chartLine.getData().clear();
		
		XYChart.Series series = new XYChart.Series();
		series.getData().clear();
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
					java.sql.Date dat = transaction.getDate();
					Calendar cal = Calendar.getInstance();
					cal.setTime(dat);
					if (cal.get(Calendar.DAY_OF_MONTH) - 1 == i) {
						solde += transaction.getAmount();
					}
				}
			}
			
			series.getData().add(new XYChart.Data(String.valueOf(i + 1), solde));
		}
		
		chartLine.getData().addAll(series);
		chartLine.setLegendVisible(false);
	}
	
	private void setDataPieChart() {
		chartPie.setTitle("Dépense par catégorie");
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
		
		for(CategoryLogic cl : ClientLogic.getInstance().getCategories()) {
			int outgo = 0;
			if(IOTransactionLogic.getTransactionsByCategory().containsKey(cl)) {
				for (IOTransactionLogic tr : IOTransactionLogic.getTransactionsByCategory().get(cl)) {
					if (!tr.isIncome()) {
						outgo += tr.getAmount() * (-1);
					}
				}
				pieChartData.add(new PieChart.Data(cl.getName(), outgo));
			}
		}
		
		pieChartData.forEach(data ->
				data.nameProperty().bind(
						Bindings.concat(
								data.getName(), " ", data.pieValueProperty(), " CHF"
						)
				)
		);
		
		chartPie.setData(pieChartData);
		chartPie.setLegendVisible(false);
		
	}
	
	private void initNodeListe() {
		
		btnAddTransaction = new JFXButton();
		btnAddTransaction.setButtonType(JFXButton.ButtonType.RAISED);
		btnAddTransaction.getStyleClass().addAll("RoundButton", "NeutralButton");
		btnOutgo = new JFXButton("D");
		btnOutgo.setButtonType(JFXButton.ButtonType.RAISED);
		btnOutgo.getStyleClass().addAll("RoundButton", "RedButton");
		btnIncome = new JFXButton("R");
		btnIncome.setButtonType(JFXButton.ButtonType.RAISED);
		btnIncome.getStyleClass().addAll("RoundButton", "GreenButton");
		
		nodeAjout.addAnimatedNode(btnAddTransaction);
		nodeAjout.addAnimatedNode(btnOutgo);
		nodeAjout.addAnimatedNode(btnIncome);
		nodeAjout.setSpacing(5d);
		nodeAjout.setRotate(180);
		
		ImageView image = new ImageView(new Image(getClass().getResourceAsStream("/gui/Image/add.png")));
		image.setFitWidth(20);
		image.setFitHeight(20);
		btnAddTransaction.setGraphic(image);
		
	}
}
