package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Card;
import model.RegularUser;
import model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Dashboard extends Controller implements Initializable{
    private User user;
    private ArrayList<Card> cards;

    @FXML
    private TextField newUsername;

    @FXML
    private Label userName = new Label();

    @FXML
    private HBox hBox;


    public void goBackPage(javafx.event.ActionEvent event) throws Exception {
        switchScene(event, "login.fxml");
    }

    void setUser(User newUser){
        this.user = newUser;
        this.userName.setText(user  .getUserName());
        this.cards = ((RegularUser) user).getMyCard();
    }


    @FXML
    void addCard(javafx.event.ActionEvent event) throws IOException {
        ConfirmBox confirmBox = new ConfirmBox();
        if (confirmBox.confirm("Do you want to buy a new card?")){
            Button button = new Button();
            Card newCard = ((RegularUser)user).buyCard();
            int id = newCard.getId();
            button.setText(Integer.toString(id));
            hBox.getChildren().add(button);
            button.setOnAction(e -> {
                try {
                    helpSetUp(newCard, e);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });
        }

    }

    @FXML
    void changeUsername(javafx.event.ActionEvent event) throws IOException {
        //find user
        HashMap<String, User> users = User.getUsers();
        for (User passenger: users.values()) {
            if (user.getUserName().equals(passenger.getUserName())) {
                //change name
                user.changeName(newUsername.getText());
                break;
            }
        }
        alert("Successfully change username");

    }

    void loadCard() {
        if (cards.isEmpty()){
            hBox.getChildren().clear();
        }
        else{
            for (Card card : cards) {
                Button button = new Button(Integer.toString(card.getId()));
                button.setText(Integer.toString(card.getId()));
                hBox.getChildren().clear();
                hBox.getChildren().add(button);
                button.setOnAction(event -> {
                    try {
                        helpSetUp(card, event);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }

    private void helpSetUp(Card card, javafx.event.ActionEvent event) throws IOException {
        ConfirmBox confirmBox = new ConfirmBox();
        if (card.getSuspended()){
            boolean answer = confirmBox.confirm("This card is suspended, do you still want to access?");
            if (answer){
                helpLoadCard(card, event);
            }
        }
        else{
            helpLoadCard(card, event);
        }
    }

    void helpLoadCard(Card card, javafx.event.ActionEvent event) throws IOException {
        Stage dashboard = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("cardController.fxml"));
        Parent root = loader.load();
        CardController cardController = loader.getController();
        cardController.setCard(card);
        cardController.setPrevious(dashboard, this);
        newStage(root);
        dashboard.close();
    }

}
