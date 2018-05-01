package gui.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static java.lang.System.exit;

public class mainFrame {

    public mainFrame(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/gui/view/mainFrame.fxml"));

            Stage primaryStage = new Stage();
            primaryStage.setTitle("Main Frame");
            primaryStage.setScene(new Scene(root, 750, 500));
            primaryStage.setMinHeight(400);
            primaryStage.setMinWidth(500);
            primaryStage.show();
            primaryStage.setOnCloseRequest(event -> exit(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
