package gui.controller;

import bll.logic.Authentication;
import bll.logic.ClientLogic;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import gui.model.windowManager;
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
import gui.model.mainFrame;
import javafx.stage.Stage;
import smtp.Mail;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static bll.logic.Authentication.*;

/**
 * Controller of the view loginRegister
 */
public class Controller_loginRegister implements Initializable, IWindow {
	
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
	@FXML private JFXCheckBox offline_checkLogin;
	@FXML private JFXCheckBox offline_checkRegister;
	
	private Stage thisStage;
	
	
	/**
	 * Event on the Login button
	 * try to log the user in
	 *
	 * @param actionEvent
	 *
	 * @throws IOException
	 */
	@FXML public void clickLoginButton(ActionEvent actionEvent) {
		
		/*Retrieving text input*/
		String email = login_email.getText();
		String password = login_password.getText();
		boolean online = !offline_checkLogin.isSelected();

		/*Retrieving the status of the login method*/
		boolean status = connect(email, password, online);
		
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
	 * Event on the register button
	 * try to register a new user
	 *
	 * @param actionEvent
	 */
	@FXML public void clickRegisterButton(ActionEvent actionEvent) {
		
		/*Retrieving text input*/
		String username = register_username.getText();
		String email = register_email.getText();
		String password = register_password.getText();
		String confirmPassword = register_confirmPassword.getText();
		boolean online = !offline_checkRegister.isSelected();
		
		register_username.setStyle("-fx-text-fill: black;");
		register_email.setStyle("-fx-text-fill: black;");
		register_password.setStyle("-fx-text-fill: black;");
		register_confirmPassword.setStyle("-fx-text-fill: black;");
		
		//Check if passord, email and username is correct
		boolean check = true;
		boolean[] checkRegistration = checkRegistration(username, email, password, confirmPassword, online);
		
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
		}
	}
	
	/**
	 * check the confirmation code provided by the user to
	 * validate his registration
	 */
	@FXML public void confirmButton() {
		
		if (Authentication.checkActivationCode(confirm_textField.getText())) {
			loadMainFrame();
		} else {
			confirm_incorrect.setText("Code invalide\nVeuillez réessayer");
			confirm_incorrect.setStyle("-fx-text-fill: red;-fx-border-color: red;-fx-border-width: 2px");
			confirm_incorrect.setVisible(true);
		}
	}
	
	/**
	 * Loading the main window
	 */
	private void loadMainFrame() {
		
		windowManager wm = windowManager.getInstance();
		
		if (!wm.hasMainframe()) {
			new mainFrame();
		}
		
		wm.displayMainFrame();
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
		confirm_textField.setOnMouseClicked(event -> resetErrorMessage());
		btnConfirmRetour.setOnAction(event -> {
			confirmation_GridPane.setVisible(false);
			confirmation_GridPane.setMouseTransparent(true);
			login_GridPane.setMouseTransparent(false);
			login_GridPane.setVisible(true);
		});
		
		hplSendCode.setOnMouseClicked(event -> {
			Mail.sendMail(ClientLogic.getInstance().getUsername(), ClientLogic.getInstance().getEmail(),
					ClientLogic.getInstance().getKey());
			confirm_incorrect.setVisible(true);
			confirm_incorrect.setText("Le code a été ré-envoyé");
			confirm_incorrect.setStyle("-fx-text-fill: green;-fx-border-color: green;-fx-border-width: 2px");
		});
		
		//		thisStage = (Stage)hplSendCode.getScene().getWindow();
		windowManager.getInstance().setConnectionFrame(this);
		
		/*
		btnConfirmRetour.getScene().getWindow().setOnShowing(event -> {
			clearFields();
		});*/
	}
	
	/**
	 * clear the inputs fields
	 */
	private void clearFields() {
		
		login_password.setText("");
		login_email.setText("");
		confirm_textField.setText("");
		register_email.setText("");
		register_password.setText("");
		register_confirmPassword.setText("");
		register_username.setText("");
		resetErrorMessage();
		
	}
	
	/**
	 * hide the error messages
	 */
	@FXML public void resetErrorMessage() {
		
		register_message.setVisible(false);
		confirm_incorrect.setVisible(false);
	}
	
	/**
	 * hide the window
	 */
	@Override public void hide() {
		
		if (thisStage == null) {
			thisStage = (Stage) hplSendCode.getScene().getWindow();
		}
		thisStage.hide();
	}
	
	/**
	 * show the window after clearing the fields
	 */
	@Override public void show() {
		
		clearFields();
		
		if (thisStage == null) {
			thisStage = (Stage) hplSendCode.getScene().getWindow();
		}
		thisStage.show();
		confirmation_GridPane.setVisible(false);
		login_GridPane.setVisible(true);
	}
}
