package controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class AdminuserController extends Controller{
    @FXML
    private Text totalStations;

    @FXML
    private Text totalStops;

    @FXML
    private Text totalRev;

    @FXML
    public void goBackPage(javafx.event.ActionEvent event) throws Exception {
        switchScene(event, "login.fxml");
    }
}
