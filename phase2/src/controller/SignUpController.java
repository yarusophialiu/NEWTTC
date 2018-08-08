package controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import model.RegularUser;
import model.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/** SignUpController is to control signUp.fxml, the purpose of this class is to handle user events in signUp stage. */
public class SignUpController extends Controller implements Initializable{

    /** This TextField is to store the user signUpEmail input. */
    @FXML
    private TextField signUpEmail;

    /** This TextField is to store the user signUpUserName input. */
    @FXML
    private  TextField signUpUserName;

    /** This TextField is to store the user signUpPassword input. */
    @FXML
    private TextField signUpPassword;


    /** This method is to handle events for the button signUp. */
    @FXML
    void signUp(javafx.event.ActionEvent event) {
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
                    File f = new File("/Users/yaruliu/Desktop/group_0165/phase2/serializedUser.ser");
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

    /** This method is to handle events for the button login. */
    @FXML
    void login(javafx.event.ActionEvent event) throws Exception {
        switchScene(event, "login.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
