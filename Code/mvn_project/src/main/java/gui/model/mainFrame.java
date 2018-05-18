package gui.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

import static java.lang.System.exit;

public class mainFrame {

    private final int MIN_HEIGHT = 750;
    private final int MIN_WIDTH = 1200;
    public mainFrame(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/gui/view/mainFrame.fxml"));

            Stage primaryStage = new Stage();
            primaryStage.setTitle("MoneyThoring");
            primaryStage.setScene(new Scene(root, MIN_WIDTH, MIN_HEIGHT));
            primaryStage.setMinHeight(MIN_HEIGHT);
            primaryStage.setMinWidth(MIN_WIDTH);
            primaryStage.show();
            primaryStage.setOnCloseRequest(event -> exit(0));
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/gui/Image/Logo.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
