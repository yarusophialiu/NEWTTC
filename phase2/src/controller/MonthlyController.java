package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import model.AdminUser;
import model.User;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class MonthlyController extends Controller implements Initializable {

    public BarChart monthlyBarChart;

    public void goBackPage(ActionEvent event) throws Exception{
        switchScene(event, "adminuser.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AdminUser adminUser = (AdminUser) User.getUsers().get("adminuser@mail.com");
        HashMap<Integer, Double> hashMap = adminUser.getMonthlyStats().getMonthlyStats();
        XYChart.Series xyChart = new XYChart.Series<>();
        for (int i = 1; i < 13; i++){
            xyChart.getData().add(new XYChart.Data<>(String.valueOf(i), hashMap.get(i)));
        }
        monthlyBarChart.getData().addAll(xyChart);
    }

}
