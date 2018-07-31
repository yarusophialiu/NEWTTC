package prestosystem.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BuyCardController implements Initializable{
    @FXML
    private TextField signupemail;

    @FXML
    private TextField signuppassword;

    @FXML
    void buycard(javafx.event.ActionEvent event) throws IOException{
        String file = "User.txt";
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            String email = signupemail.getText();
            String password = signuppassword.getText();
            bw.write(email);
            bw.write(" " +password);
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
