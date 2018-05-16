package gui.controller.bankAccount;

import bll.logic.BankAccountLogic;
import bll.logic.IOTransactionLogic;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXNodesList;
import com.jfoenix.effects.JFXDepthManager;
import gui.controller.IController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Controller_detailBankAccount implements Initializable, IController {
	
	
	@FXML private Label name;
	@FXML private Label nameBankAccount;
	@FXML private Label typeBankAccount;
	@FXML private Label dateLastTransaction;
	@FXML private Label amountBankAccount;
	@FXML private LineChart<?, ?> lineChart;
	@FXML private CategoryAxis axeX;
	@FXML private NumberAxis axeY;
	@FXML private JFXButton returnButton;
	@FXML private JFXNodesList nodelist;
	@FXML private AnchorPane paneform;
	@FXML private CheckBox chkDefaultAccount;
	@FXML private GridPane paneTop;
	
	private JFXButton preferenceButton;
	private JFXButton modifyButton;
	private JFXButton removeButton;
	
	private Controller_listBankAccount cba;
	private BankAccountLogic bal;
	
	/**
	 * Constructor of our controller
	 *
	 * @param cba The controller bank account
	 * @param bal The bank account
	 */
	public Controller_detailBankAccount(Controller_listBankAccount cba, BankAccountLogic bal) {
		
		this.bal = bal;
		this.cba = cba;
	}
	
	/**
	 * Method that returns us to the main bank accounts page
	 */
	private void returnFrame() {
		
		cba.modifyItem(bal);
	}
	
	/**
	 * Generate the the node list with our button
	 */
	private void generateNodeList() {
		
		preferenceButton = new JFXButton();
		preferenceButton.setButtonType(JFXButton.ButtonType.RAISED);
		preferenceButton.getStyleClass().addAll("RoundButton", "NeutralButton");
		modifyButton = new JFXButton();
		modifyButton.setButtonType(JFXButton.ButtonType.RAISED);
		modifyButton.getStyleClass().addAll("RoundButton", "GreenButton");
		removeButton = new JFXButton();
		removeButton.setButtonType(JFXButton.ButtonType.RAISED);
		removeButton.getStyleClass().addAll("RoundButton", "RedButton");
		
		nodelist.addAnimatedNode(preferenceButton);
		nodelist.addAnimatedNode(modifyButton);
		nodelist.addAnimatedNode(removeButton);
		nodelist.setSpacing(5d);
		
		ImageView image = new ImageView(new Image(getClass().getResourceAsStream("/gui/Image/preference.png")));
		image.setFitWidth(20);
		image.setFitHeight(20);
		preferenceButton.setGraphic(image);
		
		image = new ImageView(new Image(getClass().getResourceAsStream("/gui/Image/edit.png")));
		image.setFitWidth(20);
		image.setFitHeight(20);
		modifyButton.setGraphic(image);
		
		image = new ImageView(new Image(getClass().getResourceAsStream("/gui/Image/delete.png")));
		image.setFitWidth(20);
		image.setFitHeight(20);
		removeButton.setGraphic(image);
		
		JFXDepthManager.setDepth(nodelist, 1);
	}
	
	/**
	 * Load the form to modify the bank account
	 */
	private void modifyBankAccount() {
		
		/* we load the form fxml*/
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/formBankAccount.fxml"));
		
		/*Create a instance of the controller of bank account form*/
		Controller_formBankAccount cba = new Controller_formBankAccount(this, bal);
		
		/*Sets the controller associated with the root object*/
		loader.setController(cba);
		paneform.setVisible(true);
		paneform.setMouseTransparent(false);
		
		try {
			AnchorPane pane = loader.load();
			paneform.getChildren().add(pane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Unload the form bank account
	 */
	private void unloadform() {
		
		paneform.getChildren().clear();
		paneform.setMouseTransparent(true);
		paneform.setVisible(false);
	}
	
	/**
	 * Do nothing
	 *
	 * @param result the bank account
	 */
	@Override public void createItem(Object result) {
		
		unloadform();
	}
	
	@Override public void deleteItem(Object toDelete) {
		
		cba.deleteItem(bal);
	}
	
	@Override public void modifyItem(Object toUpdated) {
		
		unloadform();
		BankAccountLogic bal = (BankAccountLogic) toUpdated;
		this.name.setText(bal.getName());
		this.nameBankAccount.setText(bal.getBankName());
		this.typeBankAccount.setText(bal.getType());
		this.amountBankAccount.setText(String.valueOf(bal.getAmount()));
		chkDefaultAccount.setSelected(bal.isDefault());
	}
	
	private void setDataToChart() {
		
		lineChart.setTitle("Evolution du Solde");
		XYChart.Series series = new XYChart.Series();
		
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
		
		lineChart.getData().addAll(series);
		lineChart.setLegendVisible(false);
		
	}
	
	/**
	 * Called to initialize a controller after its root element has been completely processed.
	 *
	 * @param location The location used to resolve relative paths for the root object, or null if the location is not
	 * 		known.
	 * @param resources The resources used to localize the root object, or null if the root object was not localized.
	 */
	@Override public void initialize(URL location, ResourceBundle resources) {
		
		paneform.setVisible(false);
		paneform.setMouseTransparent(true);
		
		ImageView image = new ImageView(new Image(getClass().getResourceAsStream("/gui/Image/return.png")));
		image.setFitWidth(30);
		image.setFitHeight(30);
		returnButton.setGraphic(image);
		returnButton.getStyleClass().add("RoundButton");
		
		generateNodeList();
		/*Set the name of the account*/
		name.setText(bal.getName());
		
		/*Set the name of the bank account*/
		nameBankAccount.setText(bal.getBankName());
		
		/*Set the type of the account*/
		typeBankAccount.setText(bal.getType());
		
		dateLastTransaction.setText(new SimpleDateFormat("dd.MM.yyyy").format(bal.getMostRecentTransaction().getDate()));
		
		/*Change the color if the amount is bigger or lesser than 0*/
		if (bal.getAmount() >= 0) {
			amountBankAccount.setTextFill(Color.GREEN);
		} else {
			amountBankAccount.setTextFill(Color.RED);
		}
		
		/*Set the amount of the bank account*/
		amountBankAccount.setText(String.valueOf(bal.getAmount()) + " CHF");
		chkDefaultAccount.setSelected(bal.isDefault());
		chkDefaultAccount.setMouseTransparent(true);
		
		setDataToChart();
		
		modifyButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override public void handle(ActionEvent event) {
				
				modifyBankAccount();
			}
		});
		
		removeButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override public void handle(ActionEvent event) {
				
				deleteItem(bal);
			}
		});
		
		returnButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override public void handle(ActionEvent event) {
				
				returnFrame();
			}
		});
		
		JFXDepthManager.setDepth(paneTop, 1);
		
	}
}
