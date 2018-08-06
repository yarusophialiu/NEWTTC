package controller;


import javafx.stage.Stage;
import model.Card;

import java.text.ParseException;

public interface SelectStation {
    void selectBox(javafx.event.ActionEvent event);

    void confirmTrip() throws ParseException;

    void setPreviousStage(Stage stage);

    void goBackPage(javafx.event.ActionEvent event);

    void setCard(Card card);
}
