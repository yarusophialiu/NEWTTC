package prestosystem.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Dashboard {
    public void goBackPage(javafx.event.ActionEvent event) throws IOException{
        Parent loginSystem = FXMLLoader.load(getClass().getResource("loginsystem.fxml"));
        Scene loginSystemScene = new Scene(loginSystem);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginSystemScene);
        window.show();
    }

    public void goToCard(javafx.event.ActionEvent event) throws IOException{
        Parent card = FXMLLoader.load(getClass().getResource("card.fxml"));
        Scene cardScene = new Scene(card);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(cardScene);
        window.show();
    }
}
