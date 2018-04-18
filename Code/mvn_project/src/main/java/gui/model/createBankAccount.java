package gui.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class createBankAccount {


    public createBankAccount(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/gui/view/formBankAccount.fxml"));

            Stage primaryStage = new Stage();
            primaryStage.setTitle("Main Frame");
            primaryStage.setScene(new Scene(root, 500, 215));
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
