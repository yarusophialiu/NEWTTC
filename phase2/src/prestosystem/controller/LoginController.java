package prestosystem.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController extends Controller implements Initializable{
    @FXML
    private PasswordField password;

    @FXML
    static TextField email;

    @FXML
    void login(javafx.event.ActionEvent event) throws Exception{
//        String file = "User.txt";
//        try {
//            FileReader fileReader = new FileReader(file);
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
//            String line = bufferedReader.readLine();
//      while (line != null) {
//        if (!email.getText().isEmpty() && !password.getText().isEmpty()) {
//          if (line.contains(password.getText()) && line.contains(email.getText())) {
//              nextScene(event);
//              return;
//          }
//        }
//        line = bufferedReader.readLine();
//        fileReader.close();
//      }
//    } catch (IOException e) {
//            e.printStackTrace();
//        }
//        nextScene(event);
        switchScene(event, "card.fxml");
    }

    @FXML
    void buycard(javafx.event.ActionEvent event) throws IOException{
        Parent dashboard = FXMLLoader.load(getClass().getResource("buycard.fxml"));
        Scene dashboardScene = new Scene(dashboard);

        //get stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(dashboardScene);
        window.show();
    }

    // when this method is called, it will change the scene
    public void nextScene(javafx.event.ActionEvent event) throws IOException{
        Parent dashboard = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
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
