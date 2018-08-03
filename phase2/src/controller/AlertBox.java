package controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox{

    void alertMessage(String message){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Alert Box");
        VBox layer = new VBox(25);
        layer.setPadding(new Insets(10, 30, 10, 30));
        layer.setAlignment(Pos.CENTER);
        Button closeButton = new Button("OK");
        closeButton.setOnAction(e -> window.close());
        Label label = new Label(message);
        layer.getChildren().addAll(label, closeButton);

        Scene scene = new Scene(layer);
        window.setScene(scene);
        window.setMaxHeight(500);
        window.showAndWait();
    }
}
