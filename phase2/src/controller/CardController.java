package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CardController extends Controller{

    public void goBackPage(javafx.event.ActionEvent event) throws Exception {
        switchScene(event, "dashboard.fxml");
    }

    public void takeSubway(javafx.event.ActionEvent event) throws IOException {
        Parent subway = FXMLLoader.load(getClass().getResource("subwayController.fxml"));
        Scene subwayScene = new Scene(subway);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(subwayScene);
        window.show();
    }

    public void takeBus(javafx.event.ActionEvent event) throws IOException {
        Parent bus = FXMLLoader.load(getClass().getResource("busController.fxml"));
        Scene busScene = new Scene(bus);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(busScene);
        window.show();
    }
}
