package gui.controller;

import bll.logic.ClientLogic;
import bll.logic.DebtLogic;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXNodesList;
import com.jfoenix.effects.JFXDepthManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Bryan Curchod
 * @version 1.0
 */
public class Controller_debtList implements IController, Initializable {
	
	@FXML private BorderPane paneDebt;
	@FXML private FlowPane paneDebtList; // list the debts
	@FXML private BorderPane paneClaim;
	@FXML private FlowPane paneClaimList; // list the claims
	@FXML private JFXNodesList ndlAdd;
	@FXML private AnchorPane paneForm;
	
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
		
		/**
		 * default constructor, once the tests are finished ==> delete and create a constructor with a debt
		 */
		debtDisplayer(){
			btnValidation = new JFXButton("Payer");
			lblState = new Label("un état");
			lblPerson = new Label("Jean-Henri");
			lblAmount = new Label("1234.56 CHF");
			lblExpirationDate = new Label("01.01.2019");
			contentPane = new Pane();
			
			contentPane.setPadding(new Insets(10));
			contentPane.getChildren().add(lblExpirationDate);
			contentPane.getChildren().add(lblPerson);
			contentPane.getChildren().add(lblAmount);
			contentPane.getChildren().add(lblState);
			contentPane.getChildren().add(btnValidation);
			contentPane.setMinWidth(250);
			contentPane.setMinHeight(250);

			contentPane.setOnMouseClicked(event -> callForm(debt, debt.isIncome()));
			
			JFXDepthManager.setDepth(contentPane, 1);
			this.getChildren().add(contentPane);
		}
		
		public void redraw(){
			// TODO mettre à jour les informations
		}
		
		
	}
	
	
	@Override public void createItem(Object result) {
		unloadForm();
		DebtLogic d = (DebtLogic) result;
		
		if(d != null){
		
		} else { // TODO supprimer le else une fois les tests terminés
			paneDebtList.getChildren().add(new debtDisplayer());
		}
	}
	
	@Override public void deleteItem(Object toDelete) {
		unloadForm();
		// TODO créer une ou deux hashmap (dette et créance) pour les displayer, et les retirer de leur conteneur
	}
	
	@Override public void modifyItem(Object toUpdated) {
		unloadForm();
		// TODO mettre à jour le bon displayer (hashmap)
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

		// TODO lister les dettes et les mettres dans le bon conteneur
	}
}
