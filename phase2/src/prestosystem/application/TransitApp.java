package prestosystem.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class TransitApp extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("../controller/login.fxml"));
        primaryStage.setTitle("Presto System App");
        primaryStage.setScene(new Scene(root, 800, 500 ));
        primaryStage.show();
    }

  public static void main(String[] args) {
    launch(args);
  }
}
