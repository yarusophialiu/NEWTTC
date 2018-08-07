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
import java.util.logging.Level;

public class LoginController extends Controller implements Initializable{
    @FXML
    private PasswordField password;

    @FXML
    private TextField email;


    void loginAdminUser() throws IOException{
        FXMLLoader loader = loading("adminuser.fxml");
        AdminUserController adminUserController = loader.getController();
        adminUserController.loadInfo();
    }

    void loginRegularUser(String email, String password, HashMap<String, User> users, Stage loginWindow) throws IOException{
        LogWriter logWriter = new LogWriter();
        for (User user : users.values()){
            if (user.getEmailAddress().equals(email)){
                if (user.correctPassword(password)){
                    logWriter.helpLog(Level.INFO, "Regular User " + user.getEmailAddress() + " login in.");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
                    Parent root = loader.load();
                    Dashboard dashboardControl = loader.getController();
                    dashboardControl.setUser(user);
                    dashboardControl.loadCard();
                    newStage(root);
                    loginWindow.close();
                }
                else {
                    alert("Trying to login with wrong password.");
                    logWriter.helpLog(Level.WARNING, "user " + user.getEmailAddress() + " trying to login but failed: wrong password.");
                }
            }
        }
    }


    @FXML
    void login(javafx.event.ActionEvent event) throws Exception {
        LogWriter logWriter = new LogWriter();
        if (email.getText().matches("[\\S]+") && password.getText().matches("[\\S]+")) {
            HashMap<String, User> users = User.getUsers();
            String emailInput = email.getText();
            String passwordInput = password.getText();
            Stage loginWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

            if (emailInput.equals("adminuser@mail.com") && passwordInput.equals("admin123")) {
                loginAdminUser();
                logWriter.helpLog(Level.INFO, "Admin User login in.");
                loginWindow.close();

            } else if (!users.keySet().contains(emailInput)){
                alert("User with that email does not exists, try sign up.");
                logWriter.helpLog(Level.WARNING, "Trying to login with email that does not exist.");

            }
            else{
                loginRegularUser(emailInput, passwordInput, users, loginWindow);


            }
        } else{
            alert("At least one of the information input is illegal : empty or contain space. ");
        }
    }

    @FXML
    void signUp(javafx.event.ActionEvent event) throws IOException{
        Parent signUp = FXMLLoader.load(getClass().getResource("signUp.fxml"));
        Scene signUpScene = new Scene(signUp);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(signUpScene);
        window.setResizable(false);
        window.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}