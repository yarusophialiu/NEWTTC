package controller;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    void switchScene(javafx.event.ActionEvent actionEvent, String newScene) throws Exception {
        Parent root = FXMLLoader.load(Controller.class.getResource(newScene));
        Scene scene = new Scene(root, 800, 500);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    void alert(String messgae) {
        AlertBox alertBox= new AlertBox();
        alertBox.alertMessage(messgae);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
