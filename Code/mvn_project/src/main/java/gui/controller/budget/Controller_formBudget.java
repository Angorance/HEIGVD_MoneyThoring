package gui.controller.budget;

import bll.logic.BudgetLogic;
import bll.logic.CategoryLogic;
import bll.logic.ClientLogic;
import bll.model.ClientModel;
import com.jfoenix.controls.*;
import gui.Utility;
import gui.controller.IController;
import gui.controller.IForm;
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

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * budget form controller
 * Set up the fields in a modification scenario.
 * Create/Edit a budget
 *
 * @author Bryan Curchod
 * @version 1.3
 */
public class Controller_formBudget implements IForm, Initializable {
	
	@FXML
	private JFXTextField txtName;
	@FXML
	private JFXTextField txtCeiling;
	@FXML
	private JFXComboBox<CategoryLogic> cmbCategorySelect;
	@FXML
	private JFXCheckBox chbIsRegular;
	@FXML
	private JFXComboBox<Periode> cmbPeriode;
	@FXML
	private JFXComboBox<ClientModel> cbbUser;
	@FXML
	private FlowPane paneCategoryList;
	@FXML
	private FlowPane paneUserList;
	@FXML
	private JFXButton btnCancel;
	@FXML
	private JFXButton btnValidation;
	@FXML
	private JFXButton btnDelete;
	@FXML
	private Label label_beginDate;
	@FXML
	private Label label_endDate;
	@FXML
	private JFXDatePicker beginDate;
	@FXML
	private JFXDatePicker lastDate;
	@FXML
	private JFXCheckBox checkShare;
	
	private BudgetLogic budget;
	private IController parent;
	private ArrayList<CategoryLogic> listCategorie;
	private ArrayList<ClientModel> listUser;
	
	private static final int DISPLAYER_HEIGHT = 15;
	
	/**
	 * Inner class used to define a repetition.
	 * Option disabled
	 */
	private class Periode {
		
		private String nom;
		private int periode;
		
		Periode(String s, int p) {
			
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
	
	/**
	 * Inner class used to display a category
	 *
	 * @author Bryan Curchod
	 * @version 1.2
	 */
	private class CategoryDisplayer extends HBox {
		
		
		CategoryLogic cat;
		Label lblCategorie = new Label();
		JFXButton btnDelete = new JFXButton("X");
		
		/**
		 * set the label and the style of the HBox according to a CategoryLogic
		 * Object
		 *
		 * @param c CategoryObject to display
		 */
		CategoryDisplayer(CategoryLogic c) {
			
			cat = c;
			
			String catColor = toRGBCode(Color.valueOf(cat.getColor()));
			// formatting the graphic elements
			lblCategorie.setStyle("-fx-text-fill: " + catColor);
			lblCategorie.setPrefHeight(DISPLAYER_HEIGHT);
			//lblCategorie.setPadding(new Insets(0,10,2,0));
			lblCategorie.setAlignment(Pos.CENTER_LEFT);
			//lblCategorie.setTextAlignment(TextAlignment.LEFT);
			
			btnDelete.setOnAction(event -> {
				listCategorie.remove(cat);
				paneCategoryList.getChildren().remove(this);
			});
			btnDelete.setStyle("-fx-background-radius: 20");
			
			this.setPadding(new Insets(0, 0, 0, 10));
			this.getChildren().add(lblCategorie);
			this.getChildren().add(btnDelete);
			this.setHeight(DISPLAYER_HEIGHT);
			this.setAlignment(Pos.CENTER);
			//JFXDepthManager.setDepth(this, 1); // shadow manager
			lblCategorie.setText(cat.getName());
			this.setStyle(
					"-fx-background-color: white; -fx-background-radius: 20; -fx-border-width: 2px; -fx-border-radius: 20; -fx-border-color: "
							+ catColor);
		}
		
		/**
		 * convert a Color object into a HEX string
		 *
		 * @param color Color that we want the HEX code
		 *
		 * @return HEX code (web format)
		 */
		public String toRGBCode(Color color) {
			
			return String.format("#%02X%02X%02X", (int) (color.getRed() * 255),
					(int) (color.getGreen() * 255),
					(int) (color.getBlue() * 255));
		}
	}
	
	/**
	 * Inner class used to display a user
	 *
	 * @author Bryan Curchod
	 * @version 1.1
	 */
	private class UserDisplayer extends HBox {
		
		ClientModel user;
		JFXButton btnRemove = new JFXButton("X");
		Label lblUsername;
		
		UserDisplayer(ClientModel c) {
			
			user = c;
			
			lblUsername = new Label(user.getUsername());
			lblUsername.setAlignment(Pos.CENTER_LEFT);
			lblUsername.setPrefHeight(DISPLAYER_HEIGHT);
			// styling
			this.getChildren().addAll(lblUsername, btnRemove);
			this.setPadding(new Insets(5));
			this.setStyle(
					"-fx-background-color: #e2e2e2; -fx-background-radius: 20");
			this.setAlignment(Pos.CENTER);
			setMargin(btnRemove, new Insets(0, 0, 0, 15));
			
			btnRemove.setOnAction(event -> {
				paneUserList.getChildren().remove(this);
				listUser.remove(user);
			});
		}
	}
	
	
	public Controller_formBudget(IController parent, BudgetLogic budget) {
		
		this.parent = parent;
		this.budget = budget;
		listCategorie = new ArrayList<>();
		listUser = new ArrayList<>();
	}
	
	/**
	 * Validation of the form (creation or edition)
	 *
	 * @param event called event property
	 */
	@Override
	public void formValidation(ActionEvent event) {
		
		
		if (checkValidInput()) {
			
			// we gather the user input
			String name = txtName.getText();
			double amount = Utility
					.truncateDouble(Double.parseDouble(txtCeiling.getText()),
							2);
			LocalDate begin;
			LocalDate last;
			boolean rec = chbIsRegular.isSelected();
			boolean shared = checkShare.isSelected();
			
			// clearing the userlist in case of non-shared budget
			if (!shared) {
				listUser.clear();
			}
			
			// we define the period, and the eventual repetition
			int gap = 0;
			if (chbIsRegular.isSelected()) {
				begin = LocalDate.now();
				last = LocalDate.now()
						.plusDays(cmbPeriode.getValue().getPeriode());
				gap = cmbPeriode.getValue().getPeriode();
				
			} else {
				begin = beginDate.getValue();
				last = lastDate.getValue();
			}
			
			//
			if (budget == null) {
				
				budget = new BudgetLogic(name, amount, shared, rec,
						java.sql.Date.valueOf(begin),
						java.sql.Date.valueOf(last), gap, listCategorie,
						listUser);
				parent.createItem(budget);
			} else {
				
				budget.update(name, amount, shared, rec,
						java.sql.Date.valueOf(begin),
						java.sql.Date.valueOf(last), gap, listCategorie,
						listUser);
				parent.modifyItem(budget);
			}
		}
		
	}
	
	/**
	 * check if the user's input are acceptable
	 *
	 * @return true if conditions are fulfilled
	 */
	private boolean checkValidInput() {
		
		String name = txtName.getText();
		String amount = txtCeiling.getText();
		LocalDate begin = beginDate.getValue();
		LocalDate last = lastDate.getValue();
		boolean check = true;
		if (name.isEmpty()) {
			check = false;
			txtName.setStyle("-jfx-unfocus-color: red;");
		}
		
		if (amount.isEmpty() && !Utility.isDouble(amount)) {
			check = false;
			txtCeiling.setStyle("-jfx-unfocus-color: red;");
			
		}
		
		if (begin == null) {
			check = false;
			beginDate.setStyle("-jfx-default-color: red;");
		}
		
		if (last == null) {
			check = false;
			lastDate.setStyle("-jfx-default-color: red;");
		}
		
		if ((begin != null && begin.equals(last)) || (last != null && last
				.isBefore(begin))) {
			check = false;
			beginDate.setStyle("-jfx-default-color: red;");
			lastDate.setStyle("-jfx-default-color: red;");
		}
		
		if (checkShare.isSelected() && listUser.isEmpty()) {
			check = false;
			paneUserList.setStyle("-fx-border-color: red");
		}
		
		return check;
	}
	
	/**
	 * cancel the operation and return to the caller
	 *
	 * @param event called event property
	 */
	@Override
	public void formCancel(ActionEvent event) {
		
		parent.createItem(null);
	}
	
	/**
	 * Called to initialize a controller after its root element has been
	 * completely processed.
	 * set the form behavior
	 *
	 * @param location The location used to resolve relative paths for the
	 * 		root object, or null if the location is not
	 * 		known.
	 * @param resources The resources used to localize the root object, or
	 * 		null if the root object was not localized.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		LocalDate begin = beginDate.getValue();
		LocalDate last = lastDate.getValue();
		
		// check if we have to fill the fields
		if (budget != null) {
			listCategorie.clear();
			txtName.setText(budget.getName());
			txtCeiling.setText(String.valueOf(budget.getAmount()));
			chbIsRegular.setSelected(budget.isRecurrent());
			if (budget.isRecurrent()) {
				int index = budget.getGap() == 30 ? 0 : 1;
				cmbPeriode.getSelectionModel().select(index);
			} else {
				beginDate.setValue(budget.getStartingDate().toLocalDate());
				lastDate.setValue(budget.getEndingDate().toLocalDate());
			}
			
			checkShare.setSelected(budget.isShared());
			cmbCategorySelect.setDisable(budget.isShared());
			
			if (budget.isShared()) {
				checkShare.setDisable(true);
				for (ClientModel c : budget.getClientsBudget()) {
					listUser.add(c);
					paneUserList.getChildren().add(new UserDisplayer(c));
				}
			}
			for (CategoryLogic cl : budget.getCategoriesBudget()) {
				paneCategoryList.getChildren().add(new CategoryDisplayer(cl));
				listCategorie.add(cl);
			}
		}
		
		
		// gather every client except the current user for the combo box
		ObservableList<ClientModel> UserItem = FXCollections
				.observableArrayList();
		for (ClientModel u : ClientLogic.getInstance().getAllUsers()) {
			if (u.getId() != ClientLogic.getInstance().getId()) {
				UserItem.add(u);
			}
		}
		
		cbbUser.setItems(UserItem);
		cbbUser.setOnAction(event -> {
			ClientModel selected = cbbUser.getValue();
			if (selected != null && !listUser.contains(selected)) {
				listUser.add(selected);
				paneUserList.getChildren().add(new UserDisplayer(selected));
			}
			paneUserList.setStyle("-fx-border-color: white");
		});
		
		
		// set the available category in the comboBox
		ObservableList<CategoryLogic> CatItems = FXCollections
				.observableArrayList();
		CatItems.addAll(ClientLogic.getInstance().getCategories());
		
		cmbCategorySelect.setItems(CatItems);
		cmbCategorySelect.setOnAction(event -> {
			CategoryLogic selected = cmbCategorySelect.getValue();
			if (selected != null && !listCategorie.contains(selected)) {
				paneCategoryList.getChildren()
						.add(new CategoryDisplayer(selected));
				listCategorie.add(selected);
			}
		});
		
		checkShare.setOnAction(event -> {
			cbbUser.setDisable(!checkShare.isSelected());
			paneCategoryList.getChildren().clear();
			cmbCategorySelect.setDisable(checkShare.isSelected());
			listCategorie.clear();
		});
		
		cbbUser.setDisable(!checkShare.isSelected());
		
		
		// Period selection management
		cmbPeriode.getItems().add(new Periode("Mensuelle", 30));
		cmbPeriode.getItems().add(new Periode("Hebdomadaire", 7));
		chbIsRegular.setSelected(false);
		cmbPeriode.setDisable(true);
		chbIsRegular.setOnAction(event -> {
			cmbPeriode.setDisable(!chbIsRegular.isSelected());
			label_beginDate.setVisible(!chbIsRegular.isSelected());
			label_endDate.setVisible(!chbIsRegular.isSelected());
			beginDate.setVisible(!chbIsRegular.isSelected());
			lastDate.setVisible(!chbIsRegular.isSelected());
		});
		
		
		// button event
		btnCancel.setOnAction(this::formCancel);
		btnValidation.setOnAction(this::formValidation);
		btnDelete.setOnAction(event -> parent.deleteItem(budget));
		
		txtName.setOnMouseClicked(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				
				txtName.setStyle("-jfx-unfocus-color: black;");
			}
		});
		
		txtCeiling.setOnMouseClicked(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				
				txtCeiling.setStyle("-jfx-unfocus-color: black;");
			}
		});
		
		
		beginDate.setOnMouseClicked(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				
				beginDate.setStyle("-jfx-default-color: green;");
			}
		});
		
		lastDate.setOnMouseClicked(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				
				lastDate.setStyle("-jfx-default-color: green;");
			}
		});
		
		// disable the shared budget in offline mode
		if (!ClientLogic.getInstance().isOnline()) {
			checkShare.setDisable(true);
			checkShare.setSelected(false);
			cbbUser.setDisable(true);
			chbIsRegular.setDisable(true);
			chbIsRegular.setSelected(false);
		}
	}
}
