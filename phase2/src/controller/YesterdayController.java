package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import model.AdminUser;
import model.User;

public class YesterdayController extends Controller{
    @FXML
    private Text yesterdayReport;

    public void loadInfo() {
        for (User user : User.getUsers().values()){
            if (user.getEmailAddress().equals("adminuser@mail.com")){
                yesterdayReport.setText(((AdminUser)user).yesterdayReport());
            }
        }
    }

    public void goBackPage(ActionEvent event) throws Exception{
        switchScene(event, "adminuser.fxml");
    }
}
