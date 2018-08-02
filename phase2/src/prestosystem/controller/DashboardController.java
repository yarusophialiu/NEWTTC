package prestosystem.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable{
    @FXML
    private GridPane buttongrid;

    public void goBackPage(javafx.event.ActionEvent event) throws IOException{
        Parent loginSystem = FXMLLoader.load(getClass().getResource("login.fxml"));
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

    public void addButton() {
        Button button = new Button();
        buttongrid.add(button, 0, 0);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
