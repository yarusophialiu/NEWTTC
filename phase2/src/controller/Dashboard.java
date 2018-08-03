package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import model.RegularUser;
import model.User;

public class Dashboard extends Controller{
    private User user;

    @FXML


    public void goBackPage(javafx.event.ActionEvent event) throws Exception {
        switchScene(event, "login.fxml");
    }

    public void goToCard(javafx.event.ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("cardController.fxml"));
        CardController cardController = loader.getController();
        cardController.setCardSet(((RegularUser)user).getMyCard());
        switchScene(event, "cardController.fxml");
    }

    void setUser(User newUser){
        this.user = newUser;
    }
}
