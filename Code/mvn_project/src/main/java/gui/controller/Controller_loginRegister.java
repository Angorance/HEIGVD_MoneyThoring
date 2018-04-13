package gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import mt.gui.model.mainFrame;

import java.io.IOException;

public class Controller_loginRegister {

    @FXML private TextField login_email;
    @FXML private PasswordField login_password;
    @FXML private Label login_incorrect;

    @FXML
    public void clickLoginButton(ActionEvent actionEvent) throws IOException {

        /*Retrieving text input*/
        String email = login_email.getText();
        String password = login_password.getText();

        /*Retrieving the status of the login method*/
        /*boolean status = login(email,password);

        /*if the status is false, it means that one of the fields is incorrect (email or password)
        if(!status){
            /*Error message
            login_incorrect.setText("Identifiant ou mot de passe incorrect");
            login_incorrect.setTextFill(Color.RED);
        //otherwise we load the main frame
        }else{
            /*Load dashboard
            loadMainFrame();
        }*/
    }

    @FXML private TextField register_username;
    @FXML private TextField register_email;
    @FXML private PasswordField register_password;
    @FXML private PasswordField register_confirmPassword;

    @FXML
    public void clickRegisterButton(ActionEvent actionEvent) {

        /*Retrieving text input*/
        String lastname = register_username.getText();
        String email = register_email.getText();
        String password = register_password.getText();
        String confirmPasswordText = register_confirmPassword.getText();

        /*Verify password and confirmation
        if(!password.equals(confirmPasswordText)) {
            register_password.setStyle("    -fx-text-box-border: red ;");
            register_confirmPassword.setStyle("    -fx-text-box-border: red ;");
        }else{
            bool status = register(lastname,firstname,email,password)
            if the status is false, it means that the email is already in the database
            if(!status){
                /*Error message
                register_email.setStyle("    -fx-text-box-border: red ;");
             //otherwise we load the main frame
            }else{
                /*Load dashboard
                loadMainFrame();
            }
        }*/
        loadMainFrame();

    }

    public void closeStage(){
        ((Stage)login_email.getScene().getWindow()).close();
    }

    public void loadMainFrame(){
        closeStage();
        mainFrame mf = new mainFrame();
    }
}
