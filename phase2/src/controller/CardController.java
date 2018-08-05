package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import model.Card;
import model.RegularUser;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CardController extends Controller implements Initializable{
    private Card myCard;

    @FXML
    javafx.scene.control.Label cardNum;

    @FXML
    CheckBox checkbox10;

    @FXML
    CheckBox checkbox20;

    @FXML
    CheckBox checkbox30;

    private Stage previousStage;

    @FXML
    public void goBackPage(javafx.event.ActionEvent event) throws Exception {
        Stage cardController = (Stage) ((Node) event.getSource()).getScene().getWindow();
        previousStage.show();
        cardController.close();
    }

    @FXML
    public void takeSubway(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("subwayController.fxml"));
        Stage cardController = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = loader.load();
        SubwayController subwayController = loader.getController();
        subwayController.setCard(myCard);
        subwayController.setPreviousStage(cardController);
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 800, 500));
        stage.show();
        cardController.close();
    }

    @FXML
    public void takeBus(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("busController.fxml"));
        Stage cardController = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = loader.load();
        BusController busController = loader.getController();
        busController.setCard(myCard);
        busController.setPreviousStage(cardController);
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 800, 500));
        stage.show();
        cardController.close();
    }

    @FXML
    public void setCard(Card card){
        myCard = card;
        cardNum.setText(Integer.toString(card.getId()));
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
    void selectAmount(){
        if (checkbox10.isSelected()){
            checkbox20.setDisable(true);
            checkbox30.setDisable(true);

        }
        else if (checkbox20.isSelected()){
            checkbox10.setDisable(true);
            checkbox30.setDisable(true);
        }
        else if (checkbox30.isSelected()){
            checkbox10.setDisable(true);
            checkbox20.setDisable(true);
        }
        else{
            checkbox10.setDisable(false);
            checkbox20.setDisable(false);
            checkbox30.setDisable(false);
        }
    }

    void setPreviousStage(Stage stage){
        this.previousStage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
