package gui.controller;

import bll.logic.CategoryLogic;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.effects.JFXDepthManager;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
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

    private HashMap<String, CategoryDisplayer> displayerList;

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
                    callform(cat);
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

        public void redraw(){
            lblCategorie.setText(cat.getName());
            this.setStyle("-fx-background-color: " + toRGBCode(Color.valueOf(cat.getColor())) + "; -fx-background-radius: 10");
        }
    }

    /**
     * Call the form to create or modify a category
     * @param cat category to eventually modify. if null we open the form in order to create a category
     */
    public void callform(CategoryLogic cat) {
        formPane.setVisible(true);
        formPane.setMouseTransparent(false);

        // we load the category form
        FXMLLoader l = new FXMLLoader(getClass().getResource("/gui/view/formCategory.fxml"));
        l.setController(new Controller_formCategory(this, (cat != null), cat));
        try {
            formPane.getChildren().add(l.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete the displayer and the data in the DB
     * @param toDelete
     */
    @Override
    public void deleteItem(Object toDelete) {
        unloadform();
        if(toDelete != null) {
            CategoryLogic c = (CategoryLogic) toDelete;
            CategoryDisplayer d = displayerList.get(c.getName());
            listContainer.getChildren().removeAll(d);
            // TODO delete in DB
        }
    }

    /**
     * update the datas in the DB and refresh
     * @param updated
     */
    @Override
    public void modifyItem(String oldKey, Object updated) {
        unloadform();
        CategoryLogic c = (CategoryLogic) updated;
        displayerList.get(c.getName()).redraw();
        // TODO update in the DB
    }

    /**
     * Called by the form controller. Close the form, and retrieve the created object if existing
     * @param result created objet, null if the operation was cancelled
     */
    @Override
    public void createItem(Object result) {
        unloadform();
        CategoryLogic c = (CategoryLogic)result;
        if(result != null){
            CategoryDisplayer d = new CategoryDisplayer(c);
            displayerList.put(c.getName(), d);
            listContainer.getChildren().add(d);
        }

        // TODO create in DB
    }

    private void unloadform() {
        formPane.getChildren().clear();
        formPane.setVisible(false);
        formPane.setMouseTransparent(true);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // #5ACCF2
        formPane.setMouseTransparent(true);
        formPane.setVisible(false);
        btnAdd.setOnAction(event -> callform(null));
        displayerList = new HashMap<>();

        // TODO list the existing categories

        // sample category
        CategoryDisplayer d = new CategoryDisplayer(new CategoryLogic("Nourriture", "#20B4E6", false));
        displayerList.put("Nourriture", d);
        listContainer.getChildren().add(d);
        d = new CategoryDisplayer(new CategoryLogic("Technologie", "#8D18D6", false));
        displayerList.put("Technologie", d);
        listContainer.getChildren().add(d);
    }
}
