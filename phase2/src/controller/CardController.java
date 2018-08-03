package controller;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.*;

import java.awt.*;
import java.io.IOException;

public class CardController extends Controller{

    private Card myCard;

    @FXML
    javafx.scene.control.Label cardNum;

    @FXML
    CheckBox checkbox10;

    @FXML
    CheckBox checkbox20;

    @FXML
    CheckBox checkbox30;

    @FXML
    public void goBackPage(javafx.event.ActionEvent event) throws Exception {
        switchScene(event, "dashboard.fxml");
    }

    @FXML
    public void takeSubway(javafx.event.ActionEvent event) throws IOException {
        Parent subway = FXMLLoader.load(getClass().getResource("subwayController.fxml"));
        Scene subwayScene = new Scene(subway);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(subwayScene);
        window.show();
    }

    @FXML
    public void takeBus(javafx.event.ActionEvent event) throws IOException {
        Parent bus = FXMLLoader.load(getClass().getResource("busController.fxml"));
        Scene busScene = new Scene(bus);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(busScene);
        window.show();
    }

    @FXML
    void setCard(Card card){
        myCard = card;
        cardNum.setText("1002");

    }

    @FXML
    void addBalance(javafx.event.ActionEvent event) throws Exception {
        if (checkbox10.isSelected()){
            checkbox10.setSelected(false);
            myCard.increaseBalance(10);
        }
        else if (checkbox20.isSelected()){
            checkbox20.setSelected(false);
            myCard.increaseBalance(20);
        }
        else if (checkbox30.isSelected()){
            checkbox30.setSelected(false);
            myCard.increaseBalance(30);
        }
    }

    @FXML
    void deleteCard(javafx.event.ActionEvent event){
        ((RegularUser)myCard.getUser()).removeCard(myCard);
    }

    @FXML
    void suspend(javafx.event.ActionEvent event){
        myCard.reverseSuspended();
    }

    @FXML
    void selectAmount(javafx.event.ActionEvent event){
        if (checkbox10.isSelected()){
            checkbox20.setAllowIndeterminate(true);
            checkbox30.setAllowIndeterminate(true);
        }
        else if (checkbox20.isSelected()){
            checkbox10.setAllowIndeterminate(true);
            checkbox30.setAllowIndeterminate(true);
        }
        else if (checkbox30.isSelected()){
            checkbox10.setAllowIndeterminate(true);
            checkbox20.setAllowIndeterminate(true);
        }
    }

}
