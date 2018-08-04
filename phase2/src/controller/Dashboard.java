package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
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
    private GridPane buttonGrid;

    @FXML
    private TextField newUsername;

    @FXML
    private Label userName = new Label();


    public void goBackPage(javafx.event.ActionEvent event) throws Exception {
        switchScene(event, "login.fxml");
    }

    public void goToCard(javafx.event.ActionEvent event) throws Exception {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("cardController.fxml"));
//        CardController cardController = loader.getController();
//        cardController.setCardSet(cards);
//        switchScene(event, "cardController.fxml");
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
        buttonGrid.add(button, cards.size(), 0);

        //set on action
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
        AlertBox alertBox= new AlertBox();
        alertBox.alertMessage("Successfully change username");

    }


    public void loadCard() {
        int col = 0;
        for (Card card : cards) {
            Button button = new Button(Integer.toString(card.getId()));
            button.setText(Integer.toString(card.getId()));
            buttonGrid.add(button, col, 0);
            button.setOnAction(event -> {
                try {
                    helper(card);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            col++;
        }
    }

    private void helper(Card card) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CardController.fxml"));
        Parent root = loader.load();
        CardController cardController = loader.getController();
        cardController.setCard(card);
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 800, 500));
        stage.show();
    }
}
