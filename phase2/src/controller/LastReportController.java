package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import model.AdminUser;
import model.User;

public class LastReportController extends Controller{
    @FXML
    private Text yesterdayReport;

    /**Load information of lastreport.fxml*/
    public void loadInfo() {
        for (User user : User.getUsers().values()){
            if (user.getEmailAddress().equals("adminuser@mail.com")){
                yesterdayReport.setText(((AdminUser)user).yesterdayReport());
            }
        }
    }

    /**
     * Go back to previous page when click the button
     *
     * @param event the action that happens when we click button
     */
    public void goBackPage(ActionEvent event) throws Exception{
        switchScene(event, "adminuser.fxml");
    }
}
