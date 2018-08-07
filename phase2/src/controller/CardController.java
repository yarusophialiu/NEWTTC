package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Card;
import model.RegularUser;
import model.User;

import java.io.IOException;

/** Class CardController is to control the cardController.fxml, the purpose of this class is to handle events that need
 * to make communication with each individual card. */
public class CardController extends Controller{

    /** Used to store card information that this stage is representing. */
    private Card myCard;

    /** A label in showing the balance of the card. */
    @FXML
    Label balance;

    /** A label in showing the ID of the card. */
    @FXML
    javafx.scene.control.Label cardNum;

    /** A checkbox that allow user to select adding 10 dollars to the card balance. */
    @FXML
    CheckBox checkbox10;

    /** A checkbox that allow user to select adding 20 dollars to the card balance. */
    @FXML
    CheckBox checkbox20;

    /** A checkbox that allow user to select adding 50 dollars to the card balance. */
    @FXML
    CheckBox checkbox50;

    /** A stage that store the stage before this one to allow user to go back page into the same instance of the last
     * stage.*/
    private Stage previousStage;

    /** this variable is to help update the information showing in the dashboard stage. */
    private Dashboard dashboard;

    /** Instantiate a helpSerialize instance to decrease duplicated code and to serialize user information. */
    private HelpSerialize helpSerialize = new HelpSerialize();


    /** Corresponding to the go back page button on this page to allow user to go back to dashboard. */
    @FXML
    public void goBackPage(javafx.event.ActionEvent event) throws Exception {
        Stage cardController = (Stage) ((Node) event.getSource()).getScene().getWindow();
        previousStage.show();
        dashboard.loadCard();
        cardController.close();
    }

    /** Corresponding to the take subway button on this page to allow user to go to the subway control page to take the
     *  subway. */
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

    /** Corresponding to the take bus button on this page to allow user to go to the bus control page to take the
     *  bus. */
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

    /** A setter to set the card that this controller is controlling. */
    public void setCard(Card card){
        myCard = card;
        cardNum.setText(Integer.toString(card.getId()));
        helpShowBalance(card.getBalance());
    }

    /** Corresponding to the addBalance button on this page to allow user to add balance to their account. */
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
            if (confirmBox.confirm("Add $20 to your balance?")){
                myCard.increaseBalance(20);
            }
        }
        else if (checkbox50.isSelected()){
            checkbox50.setSelected(false);
            if (confirmBox.confirm("Add $50 to your balance?")){
                myCard.increaseBalance(50);
            }
        }
        helpShowBalance(myCard.getBalance());
        helpSetAble();
        helpSerialize.serializeUser(User.getUsers());
    }


    /** Corresponding to the delete card button on this page to allow user to delete this card. */
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

    /** Corresponding to the suspend card button on this page to allow user to suspend and retrieve this card. */
    @FXML
    void suspend(javafx.event.ActionEvent event) throws Exception {
        ConfirmBox confirmBox = new ConfirmBox();
        boolean answer = confirmBox.confirm("Are you sure you want to suspend this card?");
        if (answer){
            myCard.reverseSuspended();
            helpSerialize.serializeUser(User.getUsers());
        }
    }

    /** this is the on click action of the check box that represent 10, 20, 50 dollars of add balance. */
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

    /** A setter help to set the previous stage and controller to help display information. */
    void setPrevious(Stage stage, Dashboard dashboard){
        this.previousStage = stage;
        this.dashboard = dashboard;
    }

    /** A helper method to update the balance information when user enters the subway and bus controller page and
     * actually took a bus or subway.*/
    void helpShowBalance(double newBalance){
        balance.setText("Balance: " + newBalance);
    }

    /** A helper method to able all the checkbox after clicked the add balance button. */
    private void helpSetAble(){
        checkbox10.setDisable(false);
        checkbox20.setDisable(false);
        checkbox50.setDisable(false);
    }

}
