package gui.controller;

import bll.logic.Authentication;
import bll.logic.ClientLogic;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import gui.model.mainFrame;
import smtp.Mail;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

import static bll.logic.Authentication.*;

/**
 * Controller of the view loginRegister
 */
public class Controller_loginRegister implements Initializable {
	
	@FXML private TextField login_email;
	@FXML private PasswordField login_password;
	@FXML private Label login_incorrect;
	@FXML private AnchorPane paneSpinner;
	@FXML private Label confirm_incorrect;
	@FXML private JFXButton confirm_Button;
	@FXML private JFXTextField confirm_textField;
	@FXML private GridPane login_GridPane;
	@FXML private GridPane confirmation_GridPane;
	@FXML private GridPane register_GridPane;
	@FXML private JFXButton btnConfirmRetour;
	@FXML private Hyperlink hplSendCode;
	
	
	/**
	 * Event on the button Login
	 *
	 * @param actionEvent
	 *
	 * @throws IOException
	 */
	@FXML public void clickLoginButton(ActionEvent actionEvent) {
		
		/*Retrieving text input*/
		String email = login_email.getText();
		String password = login_password.getText();
		
		// TODO lancer le connect dans un thread, faire apparaitre le paneSpinner en attendant, un fois le thread terminé on fait disparaire paneSpinner
		/*// WHAT THE FUCK
		AtomicBoolean status = new AtomicBoolean(false);
		Thread connect = new Thread(() -> {status.set(connect(email, password));});
		
		paneSpinner.setVisible(true);
		
		try {
			connect.start();
			connect.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		paneSpinner.setVisible(false);
		/*Retrieving the status of the login method*/
		boolean status = connect(email, password);
		
		/*if the status is false, it means that one of the fields is incorrect (email or password)*/
		if (!status) {
			/*Error message*/
			login_incorrect.setText("Identifiant ou mot de passe incorrect");
			login_incorrect.setTextFill(Color.RED);
			//otherwise we load the main frame*/
		} else {
			boolean isActivated = ClientLogic.getInstance().getIsActivated();
			if (isActivated) {
				/*Load dashboard*/
				loadMainFrame();
				
			} else {
				login_GridPane.setVisible(false);
				login_GridPane.setMouseTransparent(true);
				confirmation_GridPane.setVisible(true);
				confirmation_GridPane.setMouseTransparent(false);
			}
			
		}
	}
	
	@FXML private TextField register_username;
	@FXML private TextField register_email;
	@FXML private PasswordField register_password;
	@FXML private PasswordField register_confirmPassword;
	@FXML private Label register_message;
	
	/**
	 * Event on the button register
	 *
	 * @param actionEvent
	 */
	@FXML public void clickRegisterButton(ActionEvent actionEvent) {
		
		/*Retrieving text input*/
		String username = register_username.getText();
		String email = register_email.getText();
		String password = register_password.getText();
		String confirmPassword = register_confirmPassword.getText();
		
		register_username.setStyle("-fx-text-fill: black;");
		register_email.setStyle("-fx-text-fill: black;");
		register_password.setStyle("-fx-text-fill: black;");
		register_confirmPassword.setStyle("-fx-text-fill: black;");
		
		//Check if passord, email and username is correct
		boolean check = true;
		boolean[] checkRegistration = checkRegistration(username, email, password, confirmPassword);
		
		/*Verify if username is already used*/
		if (!checkRegistration[0]) {
			check = false;
			register_username.setStyle("-fx-text-fill: red;");
		}
		
		/*Verify if email is already used*/
		if (!checkRegistration[1]) {
			check = false;
			register_email.setStyle("-fx-text-fill: red;");
		}
		
		/*Verify if password is not equal to confirm password*/
		if (!checkRegistration[2]) {
			check = false;
			register_password.setStyle("-fx-text-fill: red;");
			register_confirmPassword.setStyle("-fx-text-fill: red;");
		}
		
		/*If all is correct we create a new client and load the main frame*/
		if (check) {
			ClientLogic.getInstance().setClient(email, username, password);
			register_message.setText("Le compte a été enregisté");
			register_message.setTextFill(Color.GREEN);
			register_message.setStyle("-fx-border-color: green");
			
			register_username.clear();
			register_email.clear();
			register_password.clear();
			register_confirmPassword.clear();
			
			/*Send the key*/
			Mail.sendMail(username, email, ClientLogic.getInstance().getKey());
		}
	}
	
	private void confirmButton() {
		if(Authentication.checkActivationCode(confirm_textField.getText())){
			loadMainFrame();
		} else {
			confirm_incorrect.setText("Code invalide\nVeuillezr réessayer");
			confirm_incorrect.setStyle("-fx-text-fill: red;-fx-border-color: red;-fx-border-width: 2px");
			confirm_incorrect.setVisible(true);
		}
	}
	
	/**
	 * Closing the window
	 */
	public void closeStage() {
		
		((Stage) login_email.getScene().getWindow()).close();
	}
	
	/**
	 * Loading the main window
	 */
	public void loadMainFrame() {
		
		closeStage();
		mainFrame mf = new mainFrame();
	}
	
	@Override public void initialize(URL location, ResourceBundle resources) {
		
		paneSpinner.setVisible(false);
		paneSpinner.setMouseTransparent(true);
		login_password.setOnAction(this::clickLoginButton);
		register_confirmPassword.setOnAction(this::clickRegisterButton);
		login_GridPane.setMouseTransparent(false);
		login_GridPane.setVisible(true);
		confirmation_GridPane.setVisible(false);
		confirmation_GridPane.setMouseTransparent(true);
		
		JFXDepthManager.setDepth(confirmation_GridPane, 3);
		JFXDepthManager.setDepth(register_GridPane, 3);
		JFXDepthManager.setDepth(login_GridPane, 3);
		
		confirm_Button.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override public void handle(ActionEvent event) {
				
				confirmButton();
			}
		});
		
		confirm_textField.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override public void handle(ActionEvent event) {
				
				confirmButton();
			}
		});
		confirm_textField.setOnMouseClicked(event -> resetConfirmErrorMessage());
		btnConfirmRetour.setOnAction(event -> {
			confirmation_GridPane.setVisible(false);
			confirmation_GridPane.setMouseTransparent(true);
			login_GridPane.setMouseTransparent(false);
			login_GridPane.setVisible(true);
		});
		
		hplSendCode.setOnMouseClicked(event -> {
			Mail.sendMail(ClientLogic.getInstance().getUsername(), ClientLogic.getInstance().getEmail(), ClientLogic.getInstance().getKey());
			confirm_incorrect.setVisible(true);
			confirm_incorrect.setText("Le code a été ré-envoyé");
			confirm_incorrect.setStyle("-fx-text-fill: green;-fx-border-color: green;-fx-border-width: 2px");
		});
	}
	
	public void resetConfirmErrorMessage() {
		confirm_incorrect.setVisible(false);
	}
}
