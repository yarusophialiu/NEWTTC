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

}
