package mt.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import mt.gui.model.mainFrame;

import java.io.IOException;

public class Controller_loginRegister {

    @FXML private TextField login_email;
    @FXML private PasswordField login_password;
    @FXML private Label login_incorrect;

    @FXML
    public void clickLoginButton(ActionEvent actionEvent) throws IOException {

        /*Retrieving the status of the login method*/
        /*boolean status = login(login_email.getText(),login_password.getText());

        /*if the status is false, it means that one of the fields is incorrect (email or password)
        if(!status){
            /*Error message
            login_incorrect.setText("Identifiant ou mot de passe incorrect");
            login_incorrect.setTextFill(Color.RED);
        //otherwise we load the dashboard
        }else{
            /*Load dashboard
        }*/
        new mainFrame();
    }

    @FXML private TextField register_lastname;
    @FXML private TextField register_firstname;
    @FXML private TextField register_email;
    @FXML private PasswordField register_password;
    @FXML private PasswordField register_confirmPassword;

    @FXML
    public void clickRegisterButton(ActionEvent actionEvent) {

        /*Verify password and confirmation
        if(!register_password.getText().equals(register_confirmPassword.getText())) {
            register_password.setStyle("    -fx-text-box-border: red ;");
            register_confirmPassword.setStyle("    -fx-text-box-border: red ;");
        }else{
            bool status = register(register_lastname,register_firstname,register_email,register_password)
            if the status is false, it means that the email is already in the database
            if(!status){
                /*Error message
                register_email.setStyle("    -fx-text-box-border: red ;");
             //otherwise we load the dashboard
            }else{
                /*Load dashboard
            }
        }*/
    }
}
