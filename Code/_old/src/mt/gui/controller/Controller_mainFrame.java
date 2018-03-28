package mt.gui.controller;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller_mainFrame implements Initializable {
    @FXML
    private AnchorPane mainContent;

    @FXML
    private JFXHamburger burgerBtn;

    @FXML
    private JFXDrawer drawer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        VBox box = null;
        try {
            box = FXMLLoader.load(getClass().getResource("../view/lateralMenu.fxml"));
            drawer.setSidePane(box);

            HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(burgerBtn);
            transition.setRate(-1);
            burgerBtn.addEventHandler(MouseEvent.MOUSE_PRESSED,(e)->{
                transition.setRate(transition.getRate()*-1);
                transition.play();

                if(drawer.isShown()){
                    drawer.close();

                } else {
                    drawer.open();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Button selected;
    @FXML
    public void select(ActionEvent event) {
        selected.getStyleClass().remove("Selected");
        selected = (Button)event.getSource();
        selected.getStyleClass().add("Selected");
    }


}
