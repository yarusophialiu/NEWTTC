package controller;

import javafx.event.ActionEvent;

public class YearlyController extends Controller{

    public void goBackPage(ActionEvent event) throws Exception{
        switchScene(event, "adminuser.fxml");
    }
}
