package controller;

import javafx.event.ActionEvent;

public class MonthlyController extends Controller{

    public void goBackPage(ActionEvent event) throws Exception{
        switchScene(event, "adminuser.fxml");
    }
}
