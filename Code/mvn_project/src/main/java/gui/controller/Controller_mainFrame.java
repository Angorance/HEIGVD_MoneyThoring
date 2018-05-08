package gui.controller;

import bll.logic.ClientLogic;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Implements the behavior of the main frame. Basically, it simply changes the view when we select
 */
public class Controller_mainFrame implements Initializable {
	
	@FXML private Label header_mainFrame;
	@FXML private AnchorPane mainContent;
	@FXML private AnchorPane paneHeader;
	@FXML private JFXHamburger burgerBtn;
	@FXML private JFXDrawer drawer;
	@FXML private Label lblInfo;
	@FXML private JFXButton disconnect_button;
	
	
	private static final String[] tabViewName = { "Dashboard", "Budget", "Transaction", "Dettes", "Compte Bancaire",
			"Catégories" };
	private static final String[] tabViewFile = { "/gui/view/Dashboard.fxml", "/gui/view/budgetList.fxml",
			"/gui/view/transactionList.fxml", "/gui/view/debtList.fxml", "/gui/view/bankAccount.fxml",
			"/gui/view/categoryList.fxml" };
	
	
	@Override public void initialize(URL location, ResourceBundle resources) {
		
		drawer.setMouseTransparent(true);
		drawer.setVisible(true);
		VBox box = null;
		JFXDepthManager.setDepth(paneHeader, 3);
		lblInfo.setText(ClientLogic.getInstance().toString());
		mainContent.setPadding(new Insets(15,5,5,5));
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/lateralMenu.fxml"));
			gui.controller.Controller_lateralMenu controller_lateralMenu = new Controller_lateralMenu();
			loader.setController(controller_lateralMenu);
			box = loader.load();
			drawer.setSidePane(box);
			
			initLateralButton(box);
			
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
		image.setFitWidth(43);
		image.setFitHeight(43);
		disconnect_button.setGraphic(image);
		disconnect_button.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override public void handle(ActionEvent event) {
				disconnect();
			}
		});
	}
	
	private void disconnect() {
		//TODO
	}
	
	
	/**
	 * TODO
	 *
	 * @param box TODO
	 */
	public void initLateralButton(VBox box) {
		
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
	}
	
	/**
	 * TODO
	 *
	 * @param id TODO
	 *
	 * @throws IOException
	 */
	private void loadContent(int id) throws IOException {
		
		header_mainFrame.setText(tabViewName[id]);
		mainContent.getChildren().clear();
		FXMLLoader loader = new FXMLLoader(getClass().getResource(tabViewFile[id]));
		switch (id) {
			case 0: // Dashboard
				break;
			case 1: // Budget
				loader.setController(new Controller_budgetList());
				break;
			case 2: // Transactions
				loader.setController(new Controller_transactionList());
				break;
			case 3: // Dettes
				break;
			case 4: // compte bancaire
				loader.setController(new Controller_bankAccount());
				break;
			case 5: // catégories
				loader.setController(new Controller_categoryList());
				break;
		}
		AnchorPane pane = loader.load();
        /*pane.prefWidthProperty().bind(mainContent.widthProperty());
        pane.prefHeightProperty().bind(mainContent.heightProperty());*/
		mainContent.getChildren().add(pane);
		
	}
	
}
