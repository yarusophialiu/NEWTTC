package controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class SignUpController extends Controller implements Initializable{
    @FXML
    private TextField signUpEmail;

    @FXML
    private  TextField signUpUserName;

    @FXML
    private TextField signUpPassword;


    @FXML
    void signUp(javafx.event.ActionEvent event){
        AlertBox alertBox= new AlertBox();
        if (signUpEmail.getText().matches("[\\S]+") && signUpUserName.getText().matches("[\\S]+")
                && signUpPassword.getText().matches("[\\S]+")) {
            HashMap<String, User> users = User.getUsers();
            if (users.keySet().contains(signUpEmail.getText())){
                alertBox.alertMessage("User with that email already exists, try login.");
            }
            else{
                new User(signUpUserName.getText(), signUpEmail.getText(), signUpPassword.getText());
                alertBox.alertMessage("User successfully created");
            }
        } else{
            alertBox.alertMessage("At least one of the information input is illegal : empty or contain space. ");
        }
    }

    @FXML
    void login(javafx.event.ActionEvent event) throws Exception {
        switchScene(event, "login.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
