package mt.gui.model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class mainFrame extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/mainFrame.fxml"));
        primaryStage.setTitle("Main Frame");
        primaryStage.setScene(new Scene(root, 750, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
