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

    void loginAdminUser() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("adminuser.fxml"));
        Parent root = loader.load();
        AdminUserController adminUserController = loader.getController();
        adminUserController.loadInfo();
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 800, 500));
        stage.setResizable(false);
        stage.show();
    }

    void loginRegularUser(String email, String password, HashMap<String, User> users) throws IOException{
        for (User user : users.values()){
            if (user.getEmailAddress().equals(email)){
                if (user.correctPassword(password)){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
                    Parent root = loader.load();
                    Dashboard dashboardControl = loader.getController();
                    dashboardControl.setUser(user);
                    dashboardControl.loadCard();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root, 800, 500));
                    stage.show();
                }
            }
        }
    }


    @FXML
    void login(javafx.event.ActionEvent event) throws Exception {
        if (email.getText().matches("[\\S]+") && password.getText().matches("[\\S]+")) {
            HashMap<String, User> users = User.getUsers();
            String emailInput = email.getText();
            String passwordInput = password.getText();
            Stage loginWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

            if (emailInput.equals("adminuser@mail.com") && passwordInput.equals("admin123")) {
                loginAdminUser();
                loginWindow.close();
            } else if (!users.keySet().contains(emailInput)){
                alert("User with that email does not exists, try sign up.");
            }
            else{
                loginRegularUser(emailInput, passwordInput, users);
                loginWindow.close();
            }
        } else{
            alert("At least one of the information input is illegal : empty or contain space. ");
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