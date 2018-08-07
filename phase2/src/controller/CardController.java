package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Card;
import model.RegularUser;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CardController extends Controller implements Initializable{

    private Card myCard;

    @FXML
    Label balance;

    @FXML
    javafx.scene.control.Label cardNum;

    @FXML
    CheckBox checkbox10;

    @FXML
    CheckBox checkbox20;

    @FXML
    CheckBox checkbox50;

    private Stage previousStage;

    private Dashboard dashboard;

    private HelpSerialize helpSerialize = new HelpSerialize();

    @FXML
    public void goBackPage(javafx.event.ActionEvent event) throws Exception {
        Stage cardController = (Stage) ((Node) event.getSource()).getScene().getWindow();
        previousStage.show();
        dashboard.loadCard();
        cardController.close();
    }

    @FXML
    public void takeSubway(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("subwayController.fxml"));
        Stage cardController = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = loader.load();
        SubwayController subwayController = loader.getController();
        subwayController.setCard(myCard);
        subwayController.setPreviousController(this, dashboard);
        subwayController.setPreviousStage(cardController);
        newStage(root);
        cardController.close();
    }

    @FXML
    public void takeBus(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("busController.fxml"));
        Stage cardController = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = loader.load();
        BusController busController = loader.getController();
        busController.setCard(myCard);
        busController.setPreviousController(this, dashboard);
        busController.setPreviousStage(cardController);
        newStage(root);
        cardController.close();
    }

    @FXML
    public void setCard(Card card){
        myCard = card;
        cardNum.setText(Integer.toString(card.getId()));
        helpShowBalance(card.getBalance());
    }

    @FXML
    void addBalance(javafx.event.ActionEvent event) throws Exception {
        ConfirmBox confirmBox = new ConfirmBox();
        if (checkbox10.isSelected()){
            checkbox10.setSelected(false);
            if (confirmBox.confirm("Add $10 to your balance?")){
                myCard.increaseBalance(10);
            }
        }
        else if (checkbox20.isSelected()){
            checkbox20.setSelected(false);
            myCard.increaseBalance(20);
            if (confirmBox.confirm("Add $20 to your balance?")){
                myCard.increaseBalance(20);
            }
        }
        else if (checkbox50.isSelected()){
            checkbox50.setSelected(false);
            myCard.increaseBalance(50);
            if (confirmBox.confirm("Add $50 to your balance?")){
                myCard.increaseBalance(50);
            }
        }
        helpShowBalance(myCard.getBalance());
        helpSetAble();
        helpSerialize.serializeUser(User.getUsers());
    }

    @FXML
    void deleteCard(javafx.event.ActionEvent event) throws Exception {
        ConfirmBox confirmBox = new ConfirmBox();
        boolean answer = confirmBox.confirm("Are you sure you want to delete this card?");
        if (answer){
            ((RegularUser)myCard.getUser()).removeCard(myCard);
            goBackPage(event);
            helpSerialize.serializeUser(User.getUsers());
        }

    }

    @FXML
    void suspend(javafx.event.ActionEvent event) throws Exception {
        ConfirmBox confirmBox = new ConfirmBox();
        boolean answer = confirmBox.confirm("Are you sure you want to suspend this card?");
        if (answer){
            myCard.reverseSuspended();
            helpSerialize.serializeUser(User.getUsers());
        }
    }

    @FXML
    void selectAmount(){
        if (checkbox10.isSelected()){
            checkbox20.setDisable(true);
            checkbox50.setDisable(true);

        }
        else if (checkbox20.isSelected()){
            checkbox10.setDisable(true);
            checkbox50.setDisable(true);
        }
        else if (checkbox50.isSelected()){
            checkbox10.setDisable(true);
            checkbox20.setDisable(true);
        }
        else{
            helpSetAble();
        }
    }

    void setPrevious(Stage stage, Dashboard dashboard){
        this.previousStage = stage;
        this.dashboard = dashboard;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    void helpShowBalance(double newBalance){
        balance.setText("Balance: " + newBalance);
    }

    private void helpSetAble(){
        checkbox10.setDisable(false);
        checkbox20.setDisable(false);
        checkbox50.setDisable(false);
    }

}
