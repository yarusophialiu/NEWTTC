package controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import model.RegularUser;
import model.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
                try{
                    File f = new File("/Users/ShellyWu/Desktop/group_0165/phase2/serializedUser.ser");
                    FileOutputStream fos =new FileOutputStream(f);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(users);
                }
                catch (Exception e){
                    e.printStackTrace();
                }

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
