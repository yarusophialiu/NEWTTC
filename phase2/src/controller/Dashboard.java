package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import model.Card;
import model.RegularUser;
import model.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Dashboard extends Controller implements Initializable{
    private User user;
    private ArrayList<Card> cards;

    @FXML
    private GridPane buttonGrid;

    @FXML
    public void goBackPage(javafx.event.ActionEvent event) throws Exception {
        switchScene(event, "login.fxml");
    }

    public void goToCard(javafx.event.ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("cardController.fxml"));
        CardController cardController = loader.getController();
        cardController.setCardSet(cards);
        switchScene(event, "cardController.fxml");
    }

    void setUser(User newUser){
        this.user = newUser;
        this.cards = ((RegularUser) user).getMyCard();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int col = 0;
        for (Card card : cards) {
            Button button = new Button(Integer.toString(card.getId()));
            buttonGrid.add(button, col, 0);
            col++;
        }
    }
}
