package controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/** A class that helps other class to display confirm messages. */
class ConfirmBox {

    /** A boolean variable that stores the user answer. */
    private boolean answer;

    /** A method that takes in an String of message and display them in an confirm box where the user cannot do anything
     * else before clicking yes or no and return it as a boolean value. */
    boolean confirm(String message){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Confirm Box");
        VBox layer = new VBox(25);
        layer.setPadding(new Insets(10, 30, 10, 30));
        layer.setAlignment(Pos.CENTER);

        HBox hBox = new HBox();
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");
        yesButton.setOnAction(e -> {
            e.consume();
            answer = true;
            window.close();
        });
        noButton.setOnAction(e -> {
            e.consume();
            answer = false;
            window.close();
        });

        Label label = new Label(message);

        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(30);
        hBox.getChildren().addAll(yesButton, noButton);
        layer.getChildren().addAll(label, hBox);

        Scene scene = new Scene(layer);
        window.setScene(scene);
        window.setMaxHeight(500);
        window.showAndWait();
        return answer;
    }

}
