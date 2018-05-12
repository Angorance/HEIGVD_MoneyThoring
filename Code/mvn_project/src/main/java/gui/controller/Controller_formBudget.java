package gui.controller;

import bll.logic.BudgetLogic;
import bll.logic.CategoryLogic;
import bll.logic.ClientLogic;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import jdk.jfr.Category;

import java.net.URL;
import java.util.*;

/**
 * @author Bryan Curchod
 * @version 1.0
 */
public class Controller_formBudget implements IForm, Initializable {
	
	@FXML private JFXTextField txtName;
	@FXML private JFXTextField txtCeiling;
	@FXML private JFXComboBox<CategoryLogic> cmbCategorySelect;
	@FXML private JFXCheckBox chbIsRegular;
	@FXML private JFXComboBox<Periode> cmbPeriode;
	@FXML private FlowPane paneCategoryList;
	@FXML private JFXButton btnCancel;
	@FXML private JFXButton btnValidation;
	@FXML private JFXButton btnDelete;
	
	private BudgetLogic budget;
	private IController parent;
	private Set<CategoryLogic> listCategorie;

	private class Periode{
		private String nom;
		private int periode;
		Periode(String s, int p){
			nom = s;
			periode = p;
		}

		@Override
		public String toString() {
			return nom;
		}

		public int getPeriode() {
			return periode;
		}
	}

	private class CategoryDisplayer extends HBox {
		private final int HEIGHT = 15;
		CategoryLogic cat;
		Label lblCategorie = new Label();
		JFXButton btnDelete = new JFXButton("X");


		/**
		 * set the label and the style of the HBox according to a CategoryLogic Object
		 * @param c CategoryObject to display
		 */
		CategoryDisplayer(CategoryLogic c){
			cat = c;

			String catColor = toRGBCode(Color.valueOf(cat.getColor()));
			// formatting the graphic elements
			lblCategorie.setStyle("-fx-text-fill: " + catColor);
			lblCategorie.setPrefHeight(HEIGHT);
			//lblCategorie.setPadding(new Insets(0,10,2,0));
			lblCategorie.setAlignment(Pos.CENTER_LEFT);
			//lblCategorie.setTextAlignment(TextAlignment.LEFT);

			btnDelete.setOnAction(event -> {
				listCategorie.remove(cat);
				paneCategoryList.getChildren().remove(this);
			});
			btnDelete.setStyle("-fx-background-radius: 20");

			this.setPadding(new Insets(0,0,0,10));
			this.getChildren().add(lblCategorie);
			this.getChildren().add(btnDelete);
			this.setHeight(HEIGHT);
			this.setAlignment(Pos.CENTER);
			//JFXDepthManager.setDepth(this, 1); // shadow manager
			lblCategorie.setText(cat.getName());
			this.setStyle("-fx-background-color: white; -fx-background-radius: 20; -fx-border-width: 2px; -fx-border-radius: 20; -fx-border-color: " + catColor);
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
	
	public Controller_formBudget(IController parent, BudgetLogic budget) {
		this.parent = parent;
		this.budget = budget;
		listCategorie = new HashSet<>();
	}
	
	@Override public void formValidation(ActionEvent event) {
		if(budget == null){
			// TODO création de budget
			//budget = new BudgetLogic();
			// retour au parent
			parent.createItem(budget);
		} else {
			// TODO édition du budget
			// retour au parent
			parent.modifyItem(budget);
		}
	
	}
	
	@Override public void formCancel(ActionEvent event) {
		parent.createItem(null);
	}
	
	@Override public void initialize(URL location, ResourceBundle resources) {
		if(budget != null){
			btnDelete.setVisible(true);
			//TODO remplir les champs
		}
		
		// set the available category in the comboBox
		ObservableList<CategoryLogic> items =  FXCollections.observableArrayList();
		items.addAll(ClientLogic.getInstance().getCategories());

		cmbCategorySelect.setItems(items);
		cmbCategorySelect.setOnAction(event -> {
			CategoryLogic selected = cmbCategorySelect.getSelectionModel().getSelectedItem();
			if(selected != null && !listCategorie.contains(selected)) {
				paneCategoryList.getChildren().add(new CategoryDisplayer(selected));
				listCategorie.add(selected);
			}
		});

		// Period selection management
		cmbPeriode.getItems().add(new Periode("Mensuelle", 30));
		cmbPeriode.getItems().add(new Periode("Hebdomadaire", 7));
		chbIsRegular.setSelected(false);
		cmbPeriode.setDisable(true);
		chbIsRegular.setOnAction(event -> {
			cmbPeriode.setDisable(!chbIsRegular.isSelected());
		});

		btnCancel.setOnAction(this::formCancel);
		btnValidation.setOnAction(this::formValidation);
		btnDelete.setOnAction(event -> parent.deleteItem(budget));
	
	}
}
