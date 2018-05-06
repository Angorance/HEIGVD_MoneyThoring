package gui.controller;

import bll.logic.CategoryLogic;
import bll.logic.ClientLogic;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.effects.JFXDepthManager;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the category list view
 * @author Bryan Curchod
 * @version 1.0
 */
public class Controller_categoryList implements Initializable, IController {
    @FXML private FlowPane listContainer;
    @FXML private JFXButton btnAdd;
    @FXML private AnchorPane formPane;

    /**
     * Class that wrap a category into a graphic element
     */
    private class CategoryDisplayer extends HBox {
        private final int HEIGHT = 30;
        CategoryLogic cat;
        Label lblCategorie = new Label();


        /**
         * set the label and the style of the HBox according to a CategoryLogic Object
         * @param c CategoryObject to display
         */
        CategoryDisplayer(CategoryLogic c){
            cat = c;

            // setting the data and behavior
            lblCategorie.setText(cat.getName());
            this.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    // open the form for modification
                }
            });

            // formatting the graphic elements
            lblCategorie.setStyle("-fx-background-color: white");
            lblCategorie.setPrefHeight(HEIGHT);
            lblCategorie.setPadding(new Insets(0,10,0,10));
            lblCategorie.setAlignment(Pos.CENTER);
            this.setPadding(new Insets(0,10,0,10));
            this.setStyle("-fx-background-color: " + toRGBCode(Color.valueOf(cat.getColor())) + "; -fx-background-radius: 10");
            this.getChildren().add(lblCategorie);
            this.setHeight(HEIGHT);
            this.setAlignment(Pos.CENTER);
            JFXDepthManager.setDepth(this, 1); // shadow manager

        }

        /**
         * convert a Color object into a HEX string
         * @param color Color that we want the HEX code
         * @return HEX code (web format)
         */
        public String toRGBCode( Color color )
        {
            return String.format( "#%02X%02X%02X",
                    (int)( color.getRed() * 255 ),
                    (int)( color.getGreen() * 255 ),
                    (int)( color.getBlue() * 255 ) );
        }
    }

    /**
     * Call the form to create or modify a category
     */
    public void callform() {
        formPane.setVisible(true);
        formPane.setMouseTransparent(false);

        // we load the category form
        FXMLLoader l = new FXMLLoader(getClass().getResource("/gui/view/formCategory.fxml"));
        l.setController(new Controller_formCategory(this));
        formPane.getChildren().clear();
        try {
            formPane.getChildren().add(l.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Called by the form controller. Close the form, and retrieve the created object if existing
     * @param result created objet, null if the operation was cancelled
     */
    @Override
    public void formReturn(Object result) {
        formPane.setVisible(false);
        formPane.setMouseTransparent(true);
        if(result != null){
            listContainer.getChildren().add(new CategoryDisplayer((CategoryLogic)result));
        }
    }
    
    @Override public void deleteItem(Object toDelete) {
    
    }
    
    @Override public void modifyItem(Object toUpdated) {
    
    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // #5ACCF2
        formPane.setMouseTransparent(true);
        formPane.setVisible(false);

        btnAdd.setOnAction(event -> callform());

        // TODO formReturn the existing categories

        // sample category
        listContainer.getChildren().add(new CategoryDisplayer(new CategoryLogic("une première catégorie", "#20B4E6", false)));
        listContainer.getChildren().add(new CategoryDisplayer(new CategoryLogic("Technologie", "#8D18D6", false)));
    }
}
