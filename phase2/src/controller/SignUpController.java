package controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import model.RegularUser;
import model.User;

import java.io.IOException;
import java.net.URL;
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
    void signUp(javafx.event.ActionEvent event) throws IOException {
        String emailInput = signUpEmail.getText();
        String passwordInput = signUpPassword.getText();
        String userNameInput = signUpUserName.getText();

        if (emailInput.matches("[\\S]+") && userNameInput.matches("[\\S]+")
                && passwordInput.matches("[\\S]+")) {
            HashMap<String, User> users = User.getUsers();
            if (users.keySet().contains(emailInput)){
                alert("User with that email already exists, try login.");
            }
            else{
                RegularUser user = new RegularUser(userNameInput, emailInput, passwordInput);
                user.buyCard();
                alert("User successfully created");
                HelpSerialize helpSerialize = new HelpSerialize();
                helpSerialize.serializeUser(User.getUsers());
            }
        } else{
            alert("At least one of the information input is illegal : empty or contain space. ");
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
