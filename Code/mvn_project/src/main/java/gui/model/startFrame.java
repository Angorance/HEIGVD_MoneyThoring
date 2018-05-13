package gui.model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static java.lang.System.exit;

public class startFrame extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/loginRegister.fxml"));
        primaryStage.setTitle("MoneyThoring");
        primaryStage.setScene(new Scene(root, 650, 450));
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> exit(0));
    }

    public static void launcher(String...args) {
        
        launch(args);
    }
}
