package controller;

import application.TransitApp;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class AdminuserController extends Controller{

    @FXML
    private Text dailyReport;

    @FXML
    public void goBackPage(javafx.event.ActionEvent event) throws Exception {
        switchScene(event, "login.fxml");
    }

    public void loadInfo() {
        dailyReport.setText(TransitApp.getAdmin().report());
    }

}
