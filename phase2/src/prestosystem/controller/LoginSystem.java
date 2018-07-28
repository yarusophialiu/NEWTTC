package prestosystem.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class LoginSystem {
    private ArrayList users = new ArrayList();
    public TextField email;
    public PasswordField password;



    // when this method is called, it will change the scene
    public void login(javafx.event.ActionEvent event) throws IOException{
        for (User item: users){
            if (item.getUserName().equals(email.toString()) & item.password.equals(password.toString())) {
                Parent dashboard =
                        FXMLLoader.load(
                                getClass()
                                        .getResource(
                                                "C:\\Users\\User\\group_0165\\phase2\\src\\prestosystem\\controller\\dashboard.fxml"));
                Scene dashboardScene = new Scene(dashboard);

                //get stage information
                Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
                window.setScene(dashboardScene);
                window.show();
            }
            else{
                System.out.println("Email or passord is not valid.");
                }
            }
        }

    }
}
