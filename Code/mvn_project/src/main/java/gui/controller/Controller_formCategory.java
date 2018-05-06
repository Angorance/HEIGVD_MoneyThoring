package gui.controller;

import bll.logic.CategoryLogic;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller_formCategory implements Initializable, IForm {

    @FXML private JFXColorPicker colorPicker;
    @FXML private JFXTextField txtLabel;
    @FXML private JFXButton btnCancel;
    @FXML private JFXButton btnConfirm;

    private IController parent;
    private CategoryLogic item;


    @Override
    public void formValidation(ActionEvent event) {
        CategoryLogic c = new CategoryLogic(txtLabel.getText(), colorPicker.getValue().toString(), false);
        parent.formReturn(c);
    }

    @Override
    public void formCancel(ActionEvent event) {
        parent.formReturn(null);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnCancel.setOnAction(this::formCancel);
        btnConfirm.setOnAction(this::formValidation);
    }

    Controller_formCategory(IController p, boolean isModif, CategoryLogic cat){
        parent = p;
        if(isModif){
            item = cat;
        }
    }
}
