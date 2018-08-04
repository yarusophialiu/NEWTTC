package controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class LoginController extends Controller implements Initializable{
    @FXML
    private PasswordField password;

    @FXML
    private TextField email;



    @FXML
    void login(javafx.event.ActionEvent event) throws Exception {
        AlertBox alertBox= new AlertBox();
        if (email.getText().matches("[\\S]+") && password.getText().matches("[\\S]+")) {
            HashMap<String, User> users = User.getUsers();
            if (!users.keySet().contains(email.getText())){
                alertBox.alertMessage("User with that email does not exists, try sign up.");
            }
            else{
                for (User user : users.values()){
                    if (user.getEmailAddress().equals(email.getText())){
                        if (user.correctPassword(password.getText())){
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
                            Parent root = loader.load();
                            Dashboard dashboardControl = loader.getController();
                            dashboardControl.setUser(user);
                            dashboardControl.loadCard();
//                            switchScene(event, "dashboard.fxml");
                            Stage stage = new Stage();
                            stage.setScene(new Scene(root, 800, 500));
                            stage.show();
                        }
                    }
                }
            }
        } else{
            alertBox.alertMessage("At least one of the information input is illegal : empty or contain space. ");
        }
    }

    @FXML
    void signUp(javafx.event.ActionEvent event) throws IOException{
        Parent dashboard = FXMLLoader.load(getClass().getResource("signUp.fxml"));
        Scene dashboardScene = new Scene(dashboard);

        //get stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
