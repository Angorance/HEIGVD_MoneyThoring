package gui.controller.debt;

import bll.logic.ClientLogic;
import bll.logic.DebtLogic;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXNodesList;
import com.jfoenix.effects.JFXDepthManager;
import gui.controller.IController;
import gui.controller.debt.Controller_formDebt;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * @author Bryan Curchod
 * @version 1.0
 */
public class Controller_listDebt implements IController, Initializable {
	
	@FXML private BorderPane paneDebt;
	@FXML private FlowPane paneDebtList; // list the debts
	@FXML private BorderPane paneClaim;
	@FXML private FlowPane paneClaimList; // list the claims
	@FXML private JFXNodesList ndlAdd;
	@FXML private AnchorPane paneForm;
	
	private HashMap<Integer, debtDisplayer> debtList;
	/**
	 * Display a debt's information
	 * TODO mettre en forme
	 * @author Bryan Curchod
	 * @version 1.0
	 */
	private class debtDisplayer extends VBox {
		Pane contentPane;
		Label lblState; // state of the debt (to pay, waiting for confirmation, waiting for payment ...)
		Label lblPerson; // username of the debitor/creditor
		Label lblAmount;
		Label lblExpirationDate;
		JFXButton btnValidation; // Button to confirm the payment
		
		DebtLogic debt;
		boolean isClaim;
		
		/**
		 * default constructor, once the tests are finished ==> delete and create a constructor with a debt
		 */
		debtDisplayer(DebtLogic d){
			debt = d;
			isClaim = (debt != null && debt.getCreatorID() == ClientLogic.getInstance().getId() && debt.isIncome());
			btnValidation = new JFXButton("Payer");
			lblState = new Label("un état");
			lblPerson = new Label("Jean-Henri");
			lblAmount = new Label("1234.56 CHF");
			lblAmount.setStyle("-fx-font-size: 24");
			lblExpirationDate = new Label("01.01.2019");
			contentPane = new Pane();
			
			this.setPadding(new Insets(10));
			this.getChildren().add(lblExpirationDate);
			this.getChildren().add(lblPerson);
			this.getChildren().add(lblAmount);
			this.getChildren().add(lblState);
			this.getChildren().add(btnValidation);
			this.setSpacing(10);
			this.setAlignment(Pos.CENTER_LEFT);
			this.setMinWidth(80);
			this.setMinHeight(80);
			
			this.setOnMouseClicked(event -> callForm(debt, isClaim));
			this.setStyle("-fx-background-color: #f0f0f0; -fx-background-radius: 10");
			JFXDepthManager.setDepth(contentPane, 1);
			
			if(isClaim){
				paneClaimList.getChildren().add(this);
			} else {
				paneDebtList.getChildren().add(this);
				
			}
		}
		
		public void redraw(){
			// TODO mettre à jour les informations
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
		
		} else { // TODO supprimer le else une fois les tests terminés
			paneDebtList.getChildren().add(new debtDisplayer(null));
		}
	}
	
	@Override public void deleteItem(Object toDelete) {
		DebtLogic debt = (DebtLogic) toDelete;
		unloadForm();
		if(debt != null) {
			debtList.get(debt.getId()).remove();
			debtList.remove(debt.getId());
			//TODO debt.supp()
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

		JFXButton btnMenu = new JFXButton("V");
		btnMenu.setStyle("-fx-background-radius: 50; -fx-background-color: #e8e8e8");
		btnMenu.setPrefHeight(50);
		btnMenu.setPrefWidth(50);
		btnMenu.setOnAction(event -> {
			btnMenu.setRotate((btnMenu.getRotate() + 180)%360);
		});

		JFXButton btnClaim= new JFXButton("C");
		btnClaim.setPrefHeight(50);
		btnClaim.setPrefWidth(50);
		btnClaim.setStyle("-fx-background-radius: 50; -fx-background-color: lightgreen");
		btnClaim.setOnAction(event -> callForm(null, true));

		JFXButton btnDebt = new JFXButton("D");
		btnDebt.setPrefHeight(50);
		btnDebt.setPrefWidth(50);
		btnDebt.setStyle("-fx-background-radius: 50; -fx-background-color: #E38F8F");
		btnDebt.setOnAction(event -> callForm(null, false));

		ndlAdd.addAnimatedNode(btnMenu);
		ndlAdd.addAnimatedNode(btnClaim);
		ndlAdd.addAnimatedNode(btnDebt);
		ndlAdd.setSpacing(5);
		ndlAdd.setRotate(180);
		
		debtList = new HashMap<>();
		/*
		for(DebtLogic d : ClientLogic.getInstance().getDebts()){
			debtList.put(d.getId(), new debtDisplayer(d));
		}*/
	}
}
