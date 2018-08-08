package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.AdminUser;
import model.User;

public class AdminUserController extends Controller{

    @FXML
    private Text dailyReport;

    @FXML
    public void goBackPage(javafx.event.ActionEvent event) throws Exception {
        switchScene(event, "login.fxml");
    }

    /**Load information of adminuser.fxml*/
    public void loadInfo() {
        for (User user : User.getUsers().values()){
            if (user.getEmailAddress().equals("adminuser@mail.com")){
                dailyReport.setText(((AdminUser)user).report());
            }
        }
    }

    /**
     * Enter and load information of lastreport.fxml when click the button
     *
     * @param event the action that happens when we click button
     */
    public void lastDayReport(ActionEvent event) throws Exception{
        Stage loginWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = loading("lastreport.fxml");
        LastReportController lastReportController = loader.getController();
        lastReportController.loadInfo();
        loginWindow.close();
    }

    /**
     * Enter and load information of monthly.fxmll when click the button
     *
     * @param event the action that happens when we click button
     */
    public void monthlyReport(ActionEvent event) throws Exception{
        switchScene(event, "monthly.fxml");
    }

    /**
     * Enter and load information of yearly.fxml when click the button
     *
     * @param event the action that happens when we click button
     */
    public void yearlyReport(ActionEvent event) throws Exception{
        switchScene(event, "yearly.fxml");
    }
}
