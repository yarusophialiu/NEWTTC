package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import model.AdminUser;
import model.User;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class YearlyController extends Controller implements Initializable{
    @FXML
    private BarChart<?, ?> yearlyChart;


    public void goBackPage(ActionEvent event) throws Exception{
        switchScene(event, "adminuser.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HashMap<Integer, Double> yearlyStats;
        XYChart.Series xyChart = new XYChart.Series<>();

        AdminUser adminUser = (AdminUser) User.getUsers().get("adminuser@mail.com");
        yearlyStats = adminUser.getYearlyStats().getYearlyStats();

        for (int i = 1; i < 11; i++) {
            xyChart.getData().add(new XYChart.Data<>(String.valueOf(i), yearlyStats.get(i)));
        }
        yearlyChart.getData().addAll(xyChart);

    }
}
