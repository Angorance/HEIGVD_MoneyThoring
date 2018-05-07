package gui.controller;

import bll.logic.ClientLogic;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
	@FXML private GridPane login_Code;
	
	
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
			/*if (isActivated) {
				/*Load dashboard*/
				loadMainFrame();
				
			/*} else {
				login_GridPane.setVisible(false);
				login_Code.setVisible(true);
			}*/
			
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
		
		/*Verify if username is already used*/
		if (usernameExists(username)) {
			check = false;
			register_username.setStyle("-fx-text-fill: red;");
		}
		
		/*Verify if password is not equale to confirm password*/
		if (!checkPasswords(password, confirmPassword)) {
			check = false;
			register_password.setStyle("-fx-text-fill: red;");
			register_confirmPassword.setStyle("-fx-text-fill: red;");
		}
		
		/*Verify if email is already used*/
		if (!checkEmail(email)) {
			check = false;
			register_email.setStyle("-fx-text-fill: red;");
		}
		
		/*If all is correcte we create a new client and load the main frame*/
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
		/*if(ClientLogic.getInstance().isCorrectKey(confirm_textField.getText())){
			loadMainFrame();
		}else{
			confirm_incorrect.setText("Le code est incorrecte");
			confirm_incorrect.setTextFill(Color.RED);
		}*/
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
		
		confirm_Button.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override public void handle(ActionEvent event) {
				
				confirmButton();
			}
		});
	}
}
