package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        this.userName.setText(user.getUserName());
        this.cards = ((RegularUser) user).getMyCard();
    }


    @FXML
    void addCard(javafx.event.ActionEvent event) throws IOException {
        Button button = new Button();
        int id = ((RegularUser)user).buyCard();
        button.setText(Integer.toString(id));
        hBox.getChildren().add(button);
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
        for (Card card : cards) {
            Button button = new Button(Integer.toString(card.getId()));
            button.setText(Integer.toString(card.getId()));
            hBox.getChildren().add(button);
            button.setOnAction(event -> {
                try {
                    helper(card, event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private void helper(Card card, javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CardController.fxml"));
        Stage dashboard = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = loader.load();
        CardController cardController = loader.getController();
        cardController.setCard(card);
        cardController.setPreviousStage(dashboard);
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 800, 500));
        stage.show();
        dashboard.close();
    }
}
