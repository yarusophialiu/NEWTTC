package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Card;

import java.io.IOException;

public class SubwayController extends Controller {

    private Card card;
    private Stage previousStage;

    public void setCard(Card card){
        this.card = card;
    }

    public void goBackPage(javafx.event.ActionEvent event) throws Exception {
        Stage subwayController = (Stage) ((Node) event.getSource()).getScene().getWindow();
        previousStage.show();
        subwayController.close();
    }

    void setPreviousStage(Stage stage){
        this.previousStage = stage;
    }
}
