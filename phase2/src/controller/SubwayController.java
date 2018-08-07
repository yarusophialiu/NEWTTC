package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Card;
import model.Station;
import model.StationFactory;
import model.User;

import java.io.*;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;

public class SubwayController extends Controller implements Initializable, SelectStation {

    /** The card you used in this subway trip. */
    private Card card;

    /** Record previous stage to for the goBack button.*/
    private Stage previousStage;

    /** Each checkbox stands for a station in the scene. Select check box to choose
     * start/end stations. */
    @FXML
    private CheckBox stop1,stop2,stop3,stop4,stop5,stop6,stop7,stop8,stop9,
            stop10,stop11;

    /** List contains all the checkboxes representing stations in line1. */
    private ArrayList<CheckBox> line1 = new ArrayList<>();

    /** List contains all the checkboxes representing stations in line2. */
    private ArrayList<CheckBox> line2 = new ArrayList<>();

    /** A hashmap with values checkbox and values strings corresponded to the its key
     * checkbox. */
    private HashMap<CheckBox, String> boxToString = new HashMap<>();

    /** This is the controller of this trip's card to help updating the card's balance. */
    private CardController cardController;

    /** The list contains all the currently selected boxes. Its size should
     *  be no greater than 2. */
    private ArrayList<CheckBox> selected = new ArrayList<>();

    /** The start time of the trip typed by user. */
    @FXML
    private TextField startTime;

    /** The end time of the trip typed  by the user.*/
    @FXML
    private TextField endTime;

    /** This is the dashboard of the card used in this trip. */
    private Dashboard dashboard;

    /** The start station chose by the user. */
    @FXML
    private Label startStation;

    /** The end station chose by the user. */
    @FXML
    private Label endStation;

  /** Set the card to use in this trip.
   * @param card The card user wants to use in the trip.*/
  public void setCard(Card card) {
        this.card = card;
    }

  /**
   *  @param event */
  public void goBackPage(javafx.event.ActionEvent event) {
        Stage subwayController = (Stage) ((Node) event.getSource()).getScene().getWindow();
        previousStage.show();
        subwayController.close();
    }

    public void setPreviousStage(Stage stage){
        this.previousStage = stage;
    }

  /**
   * @param location
   * @param resources
   */
  public void initialize(URL location, ResourceBundle resources) {
        line1.add(stop1);
        line1.add(stop2);
        line1.add(stop3);
        line1.add(stop4);
        line1.add(stop5);
        line1.add(stop6);

        line2.add(stop7);
        line2.add(stop8);
        line2.add(stop9);
        line2.add(stop4);
        line2.add(stop10);
        line2.add(stop11);

        boxToString.put(stop1, "Bloor-Yonge");
        boxToString.put(stop2, "Wellesley");
        boxToString.put(stop3, "College");
        boxToString.put(stop4, "Dundus");
        boxToString.put(stop5, "Queen");
        boxToString.put(stop6, "King");
        boxToString.put(stop7, "Terminal165");
        boxToString.put(stop8, "Yaru");
        boxToString.put(stop9, "Yuxin");
        boxToString.put(stop10, "Yunfan");
        boxToString.put(stop11, "Xiaolei");
    }

    @FXML
    public void selectBox(javafx.event.ActionEvent event){
        ArrayList<CheckBox> newSelected = new ArrayList<>();
        for (CheckBox checkBox: boxToString.keySet()){
            if (checkBox.isSelected()){
                newSelected.add(checkBox);
            }
        }
        for (CheckBox checkBox: newSelected){
            if (!selected.contains(checkBox)){
                selected.add(checkBox);
            }
        }
        if (selected.size() > newSelected.size()){
            selected = newSelected;
        }
        if (selected.size() == 0 | selected.size() == 1){
            for (CheckBox cb: boxToString.keySet()){
                cb.setDisable(false);
            }
            if (selected.size() == 0){
                startStation.setText("");
                endStation.setText("");
            }else{
                startStation.setText(boxToString.get(selected.get(0)));
                endStation.setText("");
            }

        }
        if (selected.size() == 2){
            for (CheckBox cb: boxToString.keySet()){
                if (!cb.isSelected()){
                    cb.setDisable(true);
                }
            }
            startStation.setText(boxToString.get(selected.get(0)));
            endStation.setText(boxToString.get(selected.get(1)));
        }
    }

    public void confirmTrip() throws ParseException, IOException {
        HelpSerialize helpSerialize = new HelpSerialize();
        StationFactory stationFactory = new StationFactory();
        LogWriter logWriter = new LogWriter();
        if (selected.size() == 1){
            String line = "";
            if (line1.contains(selected.get(0))){
                line = "1";
            }else if (line2.contains(selected.get(0))){
                line = "2";
            }
            if (startTime.getText().isEmpty()){
                String end = boxToString.get(selected.get(0));

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Station endStation = stationFactory.newStation(end, "subway", line);

                Date endDate = df.parse(endTime.getText() + ":00");
                card.updateOnTap("exits", endStation, endDate,
                        "subway", stationFactory);
            }else{
                String start = boxToString.get(selected.get(0));

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Station endStation = stationFactory.newStation(start, "subway", line);

                Date startDate = df.parse(startTime.getText() + ":00");
                card.updateOnTap("enters", endStation, startDate,
                        "subway", stationFactory);
            }
        }else {
            String start = boxToString.get(selected.get(0));
            String end = boxToString.get(selected.get(1));
            String line;
            if (line1.contains(selected.get(0)) & line1.contains(selected.get(1))){
                line = "1";
            }else{
                line = "2";
            }

            Station startStation = stationFactory.newStation(start, "subway", line);
            Station endStation = stationFactory.newStation(end, "subway", line);

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date startDate = df.parse(startTime.getText() + ":00");
            Date endDate = df.parse(endTime.getText() + ":00");
            card.updateOnTap("enters", startStation, startDate,
                    "subway", stationFactory);
            card.updateOnTap("exits", endStation, endDate,
                    "subway", stationFactory);
        }
        for (CheckBox checkBox: boxToString.keySet()){
            checkBox.setDisable(false);
            checkBox.setSelected(false);
        }
        selected.clear();
        startTime.clear();
        endTime.clear();
        cardController.helpShowBalance(card.getBalance());
        helpSerialize.serializeUser(User.getUsers());
        logWriter.helpLog(Level.INFO, "Valid Trip. Fare deducted accordingly."); // 改一下这个地方的String
        dashboard.helpUpdateInfo();
        startStation.setText("");
        endStation.setText("");
        alert("Trip Completed! Thanks for using our system!");
    }

    void setPreviousController(CardController cardController, Dashboard dashboard){
        this.cardController = cardController;
        this.dashboard = dashboard;
    }

}
