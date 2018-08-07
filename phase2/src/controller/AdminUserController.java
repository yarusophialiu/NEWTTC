package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import model.AdminUser;
import model.User;

public class AdminUserController extends Controller{

    @FXML
    private Text dailyReport;

    @FXML
    public void goBackPage(javafx.event.ActionEvent event) throws Exception {
        switchScene(event, "login.fxml");
    }

    public void loadInfo() {
        for (User user : User.getUsers().values()){
            if (user.getEmailAddress().equals("adminuser@mail.com")){
                dailyReport.setText(((AdminUser)user).report());
            }
        }
    }

    public void yesterdayReport(ActionEvent event) throws Exception{
        switchScene(event, "yesterday.fxml");
    }

    public void montlyReport(ActionEvent event) throws Exception{
        switchScene(event, "monthly.fxml");
    }

    public void yearlyReport(ActionEvent event) throws Exception{
        switchScene(event, "yearly.fxml");
    }
}
