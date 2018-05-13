package gui.controller;

import bll.logic.Authentication;
import bll.logic.ClientLogic;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.effects.JFXDepthManager;
import gui.model.windowManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.awt.*;
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
	
	private Stage thisStage = null;
	private static final String[] tabViewName = { "Dashboard", "Budget", "Transaction", "Dettes", "Compte Bancaire",
			"Catégories" };
	private static final String[] tabViewFile = { "/gui/view/dashboard.fxml", "/gui/view/budgetList.fxml",
			"/gui/view/transactionList.fxml", "/gui/view/debtList.fxml", "/gui/view/bankAccount.fxml",
			"/gui/view/categoryList.fxml" };
	
	
	@Override public void initialize(URL location, ResourceBundle resources) {
		
		drawer.setMouseTransparent(true);
		drawer.setVisible(true);
		VBox box = null;
		JFXDepthManager.setDepth(paneHeader, 3);
		lblInfo.setText(ClientLogic.getInstance().toString());
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/lateralMenu.fxml"));
			gui.controller.Controller_lateralMenu controller_lateralMenu = new Controller_lateralMenu();
			loader.setController(controller_lateralMenu);
			box = loader.load();
			drawer.setSidePane(box);

			// setting the lateral menu's button behavior
			for (Node node : box.getChildren()) {
				node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
					try {
						loadContent(Integer.valueOf(node.getAccessibleText()));
						drawer.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				});
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
		
		
		disconnect_button.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override public void handle(ActionEvent event) {
				disconnect();
			}
		});
		windowManager.getInstance().setMainFrame(this);
		resetDisplay();
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
        /*pane.prefWidthProperty().bind(mainContent.widthProperty());
        pane.prefHeightProperty().bind(mainContent.heightProperty());*/
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
}
