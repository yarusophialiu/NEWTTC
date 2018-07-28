package prestosystem.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Card {

    public void goBackPage(javafx.event.ActionEvent event) throws IOException {
        Parent dashboard = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        Scene dashboardScene = new Scene(dashboard);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show();
    }

    public void takeSubway(javafx.event.ActionEvent event) throws IOException {
        Parent subway = FXMLLoader.load(getClass().getResource("subway.fxml"));
        Scene subwayScene = new Scene(subway);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(subwayScene);
        window.show();
    }

    public void takeBus(javafx.event.ActionEvent event) throws IOException {
        Parent bus = FXMLLoader.load(getClass().getResource("bus.fxml"));
        Scene busScene = new Scene(bus);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(busScene);
        window.show();
    }
}
