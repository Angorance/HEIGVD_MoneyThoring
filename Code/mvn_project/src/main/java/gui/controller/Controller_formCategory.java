package gui.controller;

import bll.logic.CategoryLogic;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller_formCategory implements Initializable, IForm {

    @FXML private JFXColorPicker colorPicker;
    @FXML private JFXTextField txtLabel;
    @FXML private JFXButton btnCancel;
    @FXML private JFXButton btnConfirm;
    @FXML private JFXButton btnDelete;

    private IController parent = null;
    private CategoryLogic item = null;


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
            parent.modifyItem(oldname, item);
        }
    }

    @Override
    public void formCancel(ActionEvent event) {
        parent.createItem(null);
    }

    private void formDelete(ActionEvent event){
        parent.deleteItem(item);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnCancel.setOnAction(this::formCancel);
        btnConfirm.setOnAction(this::formValidation);
        btnDelete.setOnAction(this::formDelete);
        if(item != null){
            txtLabel.setText(item.getName());
            colorPicker.setValue(Color.valueOf(item.getColor()));
        }
    }

    Controller_formCategory(IController p, boolean isModif, CategoryLogic cat){
        parent = p;
        item = cat;
    }
}
