package gui.controller;

import bll.logic.Authentication;
import bll.logic.ClientLogic;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import gui.controller.bankAccount.Controller_listBankAccount;
import gui.controller.budget.Controller_listBudget;
import gui.controller.category.Controller_listCategory;
import gui.controller.dashboard.Controller_dashboard;
import gui.controller.debt.Controller_listDebt;
import gui.controller.transaction.Controller_listTransaction;
import gui.model.windowManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Implements the behavior of the main frame. Basically, it simply changes the view when we select
 */
public class Controller_mainFrame implements Initializable, IWindow {
	
	@FXML private Label header_mainFrame;
	@FXML private AnchorPane mainContent;
	@FXML private AnchorPane paneHeader;
	@FXML private JFXHamburger burgerBtn;
	@FXML private JFXDrawer drawer;
	@FXML private Label lblInfo;
	@FXML private JFXButton disconnect_button;
	@FXML private AnchorPane parameterPane;
	
	private Stage thisStage = null;
	private Button btnDashboard;
	private Controller_lateralMenu menuController;
	private static final String[] tabViewName = { "Dashboard", "Budget", "Transaction", "Dettes", "Compte Bancaire",
			"Catégories" };
	private static final String[] tabViewFile = { "/gui/view/dashboard.fxml", "/gui/view/budgetList.fxml",
			"/gui/view/transactionList.fxml", "/gui/view/debtList.fxml", "/gui/view/bankAccount.fxml",
			"/gui/view/categoryList.fxml", "/gui/view/userParam.fxml" };
	
	
	@Override public void initialize(URL location, ResourceBundle resources) {
		
		drawer.setMouseTransparent(true);
		drawer.setVisible(true);
		parameterPane.setVisible(false);
		parameterPane.setMouseTransparent(true);
		VBox box = null;
		JFXDepthManager.setDepth(paneHeader, 3);
		lblInfo.setText(ClientLogic.getInstance().toString());
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/lateralMenu.fxml"));
			menuController = new Controller_lateralMenu();
			loader.setController(menuController);
			box = loader.load();
			drawer.setSidePane(box);

			// setting the lateral menu's button behavior
			for (Node node : box.getChildren()) {
				String at = node.getAccessibleText();
				if(!at.equals("6")) {
					if(at.equals("0")){
						btnDashboard = (Button) node;
					}
					node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
						try {
							loadContent(Integer.valueOf(node.getAccessibleText()));
							drawer.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					});
				} else {
					node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
							drawer.close();
							loadParameter();
							
					});
				}
			}
			
			//HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(burgerBtn);
			//transition.setRate(-1);
			drawer.setOnDrawerClosing(event -> {drawer.setMouseTransparent(true);});
			drawer.setOnDrawerOpening(event -> drawer.setMouseTransparent(false));
			burgerBtn.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
				//transition.setRate(transition.getRate() * -1);
				//transition.play();
				
				if (drawer.isShown()) {
					drawer.close();
					
				} else {
					drawer.open();
				}
			});
			
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		ImageView image = new ImageView(new Image(getClass().getResourceAsStream("/gui/Image/logout.png")));
		image.setFitWidth(30);
		image.setFitHeight(30);
		disconnect_button.setGraphic(image);
		disconnect_button.setAlignment(Pos.CENTER);
		disconnect_button.getStyleClass().add("RoundButton");
		
		disconnect_button.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override public void handle(ActionEvent event) {
				disconnect();
			}
		});
		windowManager.getInstance().setMainFrame(this);
		resetDisplay();
	}
	
	private void loadParameter() {
		
		burgerBtn.setMouseTransparent(true);
		parameterPane.setVisible(true);
		parameterPane.setMouseTransparent(false);
		parameterPane.getChildren().clear();
		FXMLLoader loader = new FXMLLoader(getClass().getResource(tabViewFile[6]));
		loader.setController(new ControllerParam());
		try {
			parameterPane.getChildren().add(loader.load());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void unloadParameter(){
		burgerBtn.setMouseTransparent(false);
		parameterPane.getChildren().clear();
		parameterPane.setMouseTransparent(true);
		parameterPane.setVisible(false);
	}
	
	private void disconnect() {
		
		windowManager.getInstance().displayConnectionFrame();
		Authentication.disconnect();
	}
	
	public void resetDisplay(){
		
		try {
			loadContent(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		menuController.setSelected(btnDashboard);
	}
	
	/**
	 * Load the content requested in the mainContent pane
	 *
	 * @param id id of the view to display
	 *
	 * @throws IOException
	 */
	private void loadContent(int id) throws IOException {
		
		header_mainFrame.setText(tabViewName[id]);
		mainContent.getChildren().clear();
		FXMLLoader loader = new FXMLLoader(getClass().getResource(tabViewFile[id]));
		switch (id) {
			case 0: // Dashboard
				loader.setController(new Controller_dashboard());
				break;
			case 1: // Budget
				loader.setController(new Controller_listBudget());
				break;
			case 2: // Transactions
				loader.setController(new Controller_listTransaction());
				break;
			case 3: // Dettes
				loader.setController(new Controller_listDebt());
				break;
			case 4: // compte bancaire
				loader.setController(new Controller_listBankAccount());
				break;
			case 5: // catégories
				loader.setController(new Controller_listCategory());
				break;
		}
		AnchorPane pane = loader.load();
		
		mainContent.getChildren().add(pane);
		
	}
	
	@Override public void hide() {
		if(thisStage == null){
			thisStage = (Stage)paneHeader.getScene().getWindow();
		}
		thisStage.hide();
	}
	
	@Override public void show() {
		if(thisStage == null){
			thisStage = (Stage)paneHeader.getScene().getWindow();
		}
		resetDisplay();
		thisStage.show();
	}
	
	private class ControllerParam implements Initializable {
		@FXML JFXButton btnDeleteAccount;
		@FXML JFXTextField txtMail;
		@FXML JFXTextField txtUsername;
		@FXML JFXButton btnRetour;
		
		@Override public void initialize(URL location, ResourceBundle resources) {
			btnDeleteAccount.setOnAction(event -> {
				unloadParameter();
				ClientLogic.getInstance().supp();
				disconnect();
			});
			
			btnRetour.setOnAction(event -> unloadParameter());
			
			txtMail.setText(ClientLogic.getInstance().getEmail());
			txtUsername.setText(ClientLogic.getInstance().getUsername());
		}
	}
}
