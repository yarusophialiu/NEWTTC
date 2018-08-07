package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Card;
import model.RegularUser;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class Dashboard extends Controller implements Initializable{
    private User user;
    private ArrayList<Card> cards;

    @FXML
    private TextField newUsername;

    @FXML
    private Label userName = new Label();

    @FXML
    private HBox hBox;

    @FXML
    private Label averageMonthlyCost;

    @FXML
    private Label preferredVehicle;

    @FXML
    private Label timeOnTransit;

    @FXML
    private PasswordField newPassword;

    @FXML
    private PasswordField currentPassword;

    private HelpSerialize helpSerialize = new HelpSerialize();

    /**
     * Go back to previous page.
     *
     * @param event the action that happens when click a button.
     */
    public void goBackPage(javafx.event.ActionEvent event) throws Exception {
        switchScene(event, "login.fxml");
    }


    /**
     * Set related information of user.
     *
     * @param newUser a user.
     */
    void setUser(User newUser){
        this.user = newUser;
        this.userName.setText(user.getUserName());
        this.cards = ((RegularUser) user).getMyCard();
        this.averageMonthlyCost.setText (String.valueOf(((RegularUser) user).getAverageMonthlyFare()));
        this.preferredVehicle.setText("None");
        long millis = user.getTimeSpendOnTransitToday();
        String time = String.format("%d min, %d sec",
                TimeUnit.MILLISECONDS.toMinutes(millis),
                TimeUnit.MILLISECONDS.toSeconds(millis) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
        );
        timeOnTransit.setText(String.valueOf(time));
    }


    /**
     * Add a new card to the user when click the button.
     *
     * @param event an action that happens when click a button.
     */
    @FXML
    void addCard(javafx.event.ActionEvent event) throws IOException {
        ConfirmBox confirmBox = new ConfirmBox();
        if (confirmBox.confirm("Do you want to buy a new card?")){
            Button button = new Button();
            Card newCard = ((RegularUser)user).buyCard();
            int id = newCard.getId();
            button.setText(Integer.toString(id));
            hBox.getChildren().add(button);
            button.setOnAction(e -> {
                try {
                    cardButtonAction(newCard, e);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });
            helpSerialize.serializeUser(User.getUsers());
        }

    }

    /**
     * Change user name of the user
     *
     * @param event an action that happens when click a button.
     */
    @FXML
    void changeUsername(javafx.event.ActionEvent event) throws IOException {
        //find user
        HashMap<String, User> users = User.getUsers();
        for (User passenger: users.values()) {
            if (user.getUserName().equals(passenger.getUserName())) {
                //change name
                user.changeName(newUsername.getText());
                break;
            }
        }
        alert("Successfully change username");
        helpSerialize.serializeUser(User.getUsers());
    }

    /** Load all the card that the user has. */
    void loadCard() {
        if (cards.isEmpty()){
            hBox.getChildren().clear();
        }
        else{
            for (Card card : cards) {
                Button button = new Button(Integer.toString(card.getId()));
                button.setText(Integer.toString(card.getId()));
                hBox.getChildren().clear();
                hBox.getChildren().add(button);
                button.setOnAction(event -> {
                    try {
                        cardButtonAction(card, event);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }

    /**
     * Load the card if not suspended when click the card
     *
     * @param card the card we click on.
     * @param event an action that happens when click a button.
     */
    private void cardButtonAction(Card card, javafx.event.ActionEvent event) throws IOException {
        ConfirmBox confirmBox = new ConfirmBox();
        if (card.getSuspended()){
            boolean answer = confirmBox.confirm("This card is suspended, do you still want to access?");
            if (answer){
                helpLoadCard(card, event);
            }
        }
        else{
            helpLoadCard(card, event);
        }
    }

    /**
     * Load the card
     *
     * @param card the card we want to suspend or unlock
     * @param event an action that happens when click a button.
     */
    private void helpLoadCard(Card card, javafx.event.ActionEvent event) throws IOException {
        Stage dashboard = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("cardController.fxml"));
        Parent root = loader.load();
        CardController cardController = loader.getController();
        cardController.setCard(card);
        cardController.setPrevious(dashboard, this);
        newStage(root);
        dashboard.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * Change the password
     *
     * @param event the card we want to suspend or unlock
     */
    @FXML
    public void changePassword(ActionEvent event) throws IOException {
        if (user.correctPassword(currentPassword.getText())) {
            String newPass = newPassword.getText();
      System.out.println(newPass.trim().equals(""));
            if (newPass.trim().equals("")) {
                alert("new password needed");
            } else {
                user.setPassword(newPassword.getText());
                alert("successfully change password");
                helpSerialize.serializeUser(User.getUsers());
            }
        } else {
            alert("current password doesn't match");
        }
    }

    /** update average monthly cost */
    void helpUpdateInfo(){
        averageMonthlyCost.setText(String.valueOf(((RegularUser)user).getAverageMonthlyFare()));
        long millis = user.getTimeSpendOnTransitToday();
        String time = String.format("%d min, %d sec",
                TimeUnit.MILLISECONDS.toMinutes(millis),
                TimeUnit.MILLISECONDS.toSeconds(millis) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
        );
        timeOnTransit.setText(String.valueOf(time));
    }
}
