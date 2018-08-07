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

    /**
     * Switch the current scene with new scene
     *
     * @param actionEvent the action which happens when click button.
     * @param newScene the new scene that will be switched to
     */
    void switchScene(javafx.event.ActionEvent actionEvent, String newScene) throws Exception {
        Parent root = FXMLLoader.load(Controller.class.getResource(newScene));
        Scene scene = new Scene(root, 800, 500);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    /**
     * Pop out alert message
     *
     * @param messgae the message that we want to alert user.
     */
    void alert(String messgae) {
        AlertBox alertBox= new AlertBox();
        alertBox.alertMessage(messgae);
    }

    /**
     * Build a new stage
     *
     * @param root the base for all nodes that have children in the scene graph.
     */
    void newStage(Parent root) {
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 800, 500));
        stage.setResizable(false);
        stage.show();
    }


    /**
     * Load a file
     *
     * @param fileName the file that will be loaded
     */
    FXMLLoader loading(String fileName) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fileName));
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
