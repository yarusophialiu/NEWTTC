package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;

public class Dashboard extends Controller{
    private User user;

    @FXML


    public void goBackPage(javafx.event.ActionEvent event) throws Exception {
        switchScene(event, "login.fxml");
    }

    public void goToCard(javafx.event.ActionEvent event) throws Exception {
        switchScene(event, "cardController.fxml");
    }

    void setUser(User newUser){
        this.user = newUser;
    }
}
