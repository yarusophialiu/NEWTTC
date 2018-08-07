package controller;

import javafx.event.ActionEvent;

public class YesterdayController extends Controller{

    public void goBackPage(ActionEvent event) throws Exception{
        switchScene(event, "adminuser.fxml");
    }
}
