package controller;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
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

    void newStage(Parent root) {
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 800, 500));
        stage.setResizable(false);
        stage.show();
    }

    FXMLLoader loader(String fileName) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("adminuser.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 800, 500));
        stage.setResizable(false);
        stage.show();
        return loader;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
