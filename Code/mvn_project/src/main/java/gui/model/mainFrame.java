package gui.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class mainFrame /*extends Application*/ {

    public mainFrame(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../view/mainFrame.fxml"));

            Stage primaryStage = new Stage();
            primaryStage.setTitle("Main Frame");
            primaryStage.setScene(new Scene(root, 750, 500));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*@Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/mainFrame.fxml"));
        primaryStage.setTitle("Main Frame");
        primaryStage.setScene(new Scene(root, 750, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }*/
}
