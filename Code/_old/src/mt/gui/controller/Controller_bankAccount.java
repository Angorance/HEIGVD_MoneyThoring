package mt.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.LinkedList;


class AccountDisplay extends Pane {
    Label nameAccount;
    Label amountAccount;

    public AccountDisplay(String name, String amount,int x, int y,int width, int height){
        nameAccount = new Label(name);
        nameAccount.setPrefSize(80,30);
        nameAccount.setLayoutX(10);
        nameAccount.setLayoutY(10);

        amountAccount = new Label("" + amount);
        amountAccount.setPrefSize(80,30);
        amountAccount.setLayoutX(10);
        amountAccount.setLayoutY(40);

        this.setPrefSize(width,height);
        this.setStyle("-fx-background-color: white");
        this.setLayoutX(x);
        this.setLayoutY(y);
        this.getChildren().add(nameAccount);
        this.getChildren().add(amountAccount);
    }

    public void setAmount(String amount){
        amountAccount.setText(amount);
    }
}

public class Controller_bankAccount {
    private static LinkedList<AccountDisplay> accountDisplays = new LinkedList<AccountDisplay>();
    private static int x = 30;
    private static int y = 30;
    private final int PREFERED_WIDTH = 100;
    private final int PREFERED_HEIGHT = 100;


    @FXML private AnchorPane frame_bankAccount;

    public void createButton(ActionEvent actionEvent) {
        AccountDisplay account = new AccountDisplay("Test","5472.60",x,y,PREFERED_WIDTH,PREFERED_HEIGHT);

        if(x + PREFERED_WIDTH < frame_bankAccount.getWidth() - 30){
            x += PREFERED_WIDTH + 20;
        }else{
            x = 30;
            y += PREFERED_HEIGHT + 20;
        }
        accountDisplays.add(account);
        frame_bankAccount.getChildren().add(account);

    }
}
