package gui.controller;

import bll.logic.ClientLogic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import gui.model.mainFrame;

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
    
    
    /**
     * Event on the button Login
     * @param actionEvent
     * @throws IOException
     */
    @FXML public void clickLoginButton(ActionEvent actionEvent) {
        
        /*Retrieving text input*/
        String email = login_email.getText();
        String password = login_password.getText();

        // TODO lancer le connect dans un thread, faire apparaitre le paneSpinner en attendant, un fois le thread terminé on fait disparaire paneSpinner
        /*Retrieving the status of the login method*/
        boolean status = connect(email,password);

        /*if the status is false, it means that one of the fields is incorrect (email or password)*/
        if(!status){
            /*Error message*/
            login_incorrect.setText("Identifiant ou mot de passe incorrect");
            login_incorrect.setTextFill(Color.RED);
        //otherwise we load the main frame*/
        }else{
            /*Load dashboard*/
            loadMainFrame();
        }
    }
    
    @FXML private TextField register_username;
    @FXML private TextField register_email;
    @FXML private PasswordField register_password;
    @FXML private PasswordField register_confirmPassword;
    
    /**
     * Event on the button register
     * @param actionEvent
     */
    @FXML public void clickRegisterButton(ActionEvent actionEvent) {
        
        /*Retrieving text input*/
        String username = register_username.getText();
        String email = register_email.getText();
        String password = register_password.getText();
        String confirmPassword = register_confirmPassword.getText();
        
        //Check if passord, email and username is correct
        boolean check = true;
        
        /*Verify if username is already used*/
        if(usernameExists(username)){
            check = false;
            register_username.setStyle("-fx-text-inner-color: red;");
        }
        
        /*Verify if password is not equale to confirm password*/
        if(!checkPasswords(password,confirmPassword)){
            check = false;
            register_password.setStyle("-fx-text-inner-color: red;");
            register_confirmPassword.setStyle("-fx-text-inner-color: red;");
        }
        
        /*Verify if email is already used*/
        if(!checkEmail(email)){
            check = false;
            register_email.setStyle("-fx-text-inner-color: red;");
        }
        
        /*If all is correcte we create a new client and load the main frame*/
        if(check){
            ClientLogic.getInstance().setClient(email,username,password);
            loadMainFrame();
        }
        
    }
    
    /**
     * Closing the window
     */
    public void closeStage(){
        ((Stage)login_email.getScene().getWindow()).close();
    }
    
    /**
     * Loading the main window
     */
    public void loadMainFrame(){
        closeStage();
        mainFrame mf = new mainFrame();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        paneSpinner.setVisible(false);
        paneSpinner.setMouseTransparent(true);
        login_password.setOnAction(this::clickLoginButton);
        register_confirmPassword.setOnAction(this::clickRegisterButton);
    }
}
