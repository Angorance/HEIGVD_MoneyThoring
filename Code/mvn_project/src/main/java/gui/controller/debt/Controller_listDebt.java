package gui.controller.debt;

import bll.logic.ClientLogic;
import bll.logic.DebtLogic;
import bll.model.ClientModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXNodesList;
import com.jfoenix.effects.JFXDepthManager;
import gui.controller.IController;
import gui.controller.debt.Controller_formDebt;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * @author Bryan Curchod
 * @version 1.0
 */
public class Controller_listDebt implements IController, Initializable {
	
	@FXML private BorderPane paneDebt;
	@FXML private VBox paneDebtList; // list the debts
	@FXML private BorderPane paneClaim;
	@FXML private VBox paneClaimList; // list the claims
	@FXML private JFXNodesList ndlAdd;
	@FXML private AnchorPane paneForm;
	
	private HashMap<Integer, debtDisplayer> debtList;
	/**
	 * Display a debt's information
	 * @author Bryan Curchod
	 * @version 1.0
	 */
	private class debtDisplayer extends VBox {
		Label lblPerson = new Label(); // username of the debitor/creditor
		Label lblAmount = new Label();
		Label lblExpirationDate = new Label();
		Label lblNom = new Label();
		Label lblDescription = new Label();
		JFXButton btnValidation = new JFXButton("Valider"); // Button to confirm the payment
		
		DebtLogic debt;
		boolean isClaim;
		
		debtDisplayer(DebtLogic d){
			debt = d;
			
			if(debt.getCreatorID() == ClientLogic.getInstance().getId() && debt!= null) {
				isClaim = debt.isIncome();
			}
			else {
				isClaim = !debt.isIncome();
			}
			
			if(ClientLogic.getInstance().getId() == debt.getCreatorID()){
				btnValidation.setOnAction(event -> {
					debt.confirmPayment();
					remove();
				});
				this.setOnMouseClicked(event -> callForm(debt, isClaim));
			} else {
				btnValidation.setDisable(true);
			}
			btnValidation.getStyleClass().add("NeutralButton");
			lblDescription.setStyle("-fx-text-alignment: left");
			lblNom.setStyle("-fx-font-size: 15");
			lblPerson.setStyle("-fx-font-size: 15");
			lblAmount.setStyle("-fx-font-size: 24");
			
			GridPane top = new GridPane();
			
			this.setPadding(new Insets(15));
			
			top.getChildren().add(lblPerson);
			top.getChildren().add(lblNom);
			GridPane.setConstraints(lblNom,0,0,1,1,HPos.LEFT, VPos.CENTER,Priority.ALWAYS, Priority.NEVER);
			GridPane.setConstraints(lblPerson,1,0,1,1,HPos.RIGHT, VPos.CENTER,Priority.ALWAYS, Priority.NEVER);
			this.getChildren().add(top);
			this.getChildren().add(lblDescription);
			this.getChildren().add(lblAmount);
			this.getChildren().add(lblExpirationDate);
			this.getChildren().add(btnValidation);
			this.setSpacing(10);
			this.setAlignment(Pos.CENTER);
			this.setFillWidth(true);
			this.setMinHeight(170);
			this.setPrefHeight(170);
			this.setCursor(Cursor.HAND);
			
			this.setOnMouseClicked(event -> callForm(debt, isClaim));
			this.setStyle("-fx-background-color: #f0f0f0; -fx-background-radius: 10");
			JFXDepthManager.setDepth(this, 1);
			
			redraw();
			if(isClaim){
				paneClaimList.getChildren().add(this);
			} else {
				paneDebtList.getChildren().add(this);
				
			}
		}
		
		public void redraw(){
			
			lblExpirationDate.setText("Limite : " + new SimpleDateFormat("dd.MM.yyyy").format(debt.getExpirationDate()));
			lblAmount.setText(Double.toString(debt.getAmount()));
			if(debt.getCreatorID() == ClientLogic.getInstance().getId() && debt.getContributor() != null){
				lblPerson.setText(debt.getContributor().getUsername());
				
			} else {
				lblPerson.setText(debt.getCreator().getUsername());
			}
			lblNom.setText(debt.getName());
			lblDescription.setText(debt.getDescription());
		}
		
		public void remove(){
			
			if(isClaim){
				paneClaimList.getChildren().remove(this);
			} else {
				paneDebtList.getChildren().remove(this);
			}
		}
		
	}
	
	
	@Override public void createItem(Object result) {
		unloadForm();
		DebtLogic d = (DebtLogic) result;
		
		if(d != null){
			debtDisplayer dd = new debtDisplayer(d);
			debtList.put(d.getId(), dd);
		}
	}
	
	@Override public void deleteItem(Object toDelete) {
		DebtLogic debt = (DebtLogic) toDelete;
		unloadForm();
		if(debt != null) {
			debtList.get(debt.getId()).remove();
			debtList.remove(debt.getId());
			debt.supp();
		}
		
	}
	
	@Override public void modifyItem(Object toUpdated) {
		DebtLogic debt = (DebtLogic) toUpdated;
		unloadForm();
		if(debt != null) {
			debtList.get(debt.getId()).redraw();
		}
	}
	
	private void unloadForm(){
		paneForm.getChildren().clear();
		paneForm.setVisible(false);
		paneForm.setMouseTransparent(true);
	}
	
	private void callForm(DebtLogic d,boolean isClaim){
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/formDebt.fxml"));
		loader.setController(new Controller_formDebt(this, d, isClaim));
		
		try {
			paneForm.getChildren().add(loader.load());
			paneForm.setVisible(true);
			paneForm.setMouseTransparent(false);
		} catch (IOException e) {
			e.printStackTrace();
			unloadForm();
		}
		
	}
	
	@Override public void initialize(URL location, ResourceBundle resources) {
		
		JFXDepthManager.setDepth(paneDebt, 2);
		JFXDepthManager.setDepth(paneClaim, 2);
		//btnAdd.setOnAction(event -> callForm());
		unloadForm();

		JFXButton btnMenu = new JFXButton("");
		btnMenu.getStyleClass().addAll("RoundButton", "NeutralButton");
		ImageView image = new ImageView(new Image(getClass().getResourceAsStream("/gui/Image/add.png")));
		image.setFitWidth(20);
		image.setFitHeight(20);
		btnMenu.setGraphic(image);

		JFXButton btnClaim= new JFXButton("C");
		btnClaim.getStyleClass().addAll("RoundButton", "GreenButton");
		btnClaim.setOnAction(event -> callForm(null, true));

		JFXButton btnDebt = new JFXButton("D");
		btnDebt.getStyleClass().addAll("RoundButton", "RedButton");
		btnDebt.setOnAction(event -> callForm(null, false));

		ndlAdd.addAnimatedNode(btnMenu);
		ndlAdd.addAnimatedNode(btnClaim);
		ndlAdd.addAnimatedNode(btnDebt);
		ndlAdd.setSpacing(5);
		ndlAdd.setRotate(180);
		JFXDepthManager.setDepth(ndlAdd,1);
		
		debtList = new HashMap<>();
		
		for(DebtLogic d : ClientLogic.getInstance().getDebts()){
			debtList.put(d.getId(), new debtDisplayer(d));
		}
	}
}
