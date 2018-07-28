package prestosystem.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Subway {

    public void goBackPage(javafx.event.ActionEvent event) throws IOException {
        Parent card = FXMLLoader.load(getClass().getResource("card.fxml"));
        Scene cardScene = new Scene(card);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(cardScene);
        window.show();
    }
}
