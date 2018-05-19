package gui.controller.category;

import bll.logic.CategoryLogic;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXTextField;
import gui.controller.IController;
import gui.controller.IForm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Form to create/edit/delete a category
 *
 * @author Bryan Curchod
 * @version 1.4
 */
public class Controller_formCategory implements Initializable, IForm {

    @FXML private JFXColorPicker colorPicker;
    @FXML private JFXTextField txtLabel;
    @FXML private JFXButton btnCancel;
    @FXML private JFXButton btnConfirm;
    @FXML private JFXButton btnDelete;

    private IController parent = null;
    private CategoryLogic item = null;
    
    
    /**
     * Validation of the input. Call the edit or creation method
     * @param event calling event information
     */
    @Override
    public void formValidation(ActionEvent event) {
        if(item == null){
            item = new CategoryLogic(txtLabel.getText(), colorPicker.getValue().toString(), false);
            parent.createItem(item);
        }
        else{
            String oldname = item.getName();
            item.setColor(colorPicker.getValue().toString());
            item.setName(txtLabel.getText());
            parent.modifyItem(item);
        }
    }
    
    /**
     * cancel the form
     * @param event
     */
    @Override
    public void formCancel(ActionEvent event) {
        parent.createItem(null);
    }

    private void formDelete(ActionEvent event){
     
    	parent.deleteItem(item);
    }
    
    /**
     * Called to initialize a controller after its root element has been completely processed.
     * set up the fields
     *
     * @param location The location used to resolve relative paths for the root object, or null if the location is not
     * 		known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnCancel.setOnAction(this::formCancel);
        btnConfirm.setOnAction(this::formValidation);
        btnDelete.setOnAction(this::formDelete);
        if(item != null){
        	btnDelete.setVisible(true);
            txtLabel.setText(item.getName());
            colorPicker.setValue(Color.valueOf(item.getColor()));
            if(item.isDefault()){
            	txtLabel.setDisable(true);
            	btnDelete.setDisable(true);
            } else {
            	txtLabel.setDisable(false);
            }
        } else {
        	btnDelete.setVisible(false);
        }
    }
    
    /**
     * constructor, define the parent controller and the category handled
     * @param p parent controller
     * @param cat category to handle
     */
    Controller_formCategory(IController p, CategoryLogic cat){
        parent = p;
        item = cat;
    }
}
