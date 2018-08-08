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

    private ArrayList<ArrayList<CheckBox>> lines = new ArrayList<>();

    @FXML
    private CheckBox stop1,stop2,stop3,stop4,stop5,stop6,stop7,stop8,stop9,
            stop10,stop11;

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

  /** This is designed for the function of go back button.
   *  @param event Click the button. */
  public void goBackPage(javafx.event.ActionEvent event) {
        Stage subwayController = (Stage) ((Node) event.getSource()).getScene().getWindow();
        previousStage.show();
        subwayController.close();
    }

    public void setPreviousStage(Stage stage){
        this.previousStage = stage;
    }

  /**Initialize the scene.
   * @param location Follow the format.
   * @param resources Follow the format.
   */
  public void initialize(URL location, ResourceBundle resources) {
        //Add all the stops to its line.
        ArrayList<CheckBox> line1 = new ArrayList<>();
        line1.add(stop1);
        line1.add(stop2);
        line1.add(stop3);
        line1.add(stop4);
        line1.add(stop5);
        line1.add(stop6);

        ArrayList<CheckBox> line2 = new ArrayList<>();
        line2.add(stop7);
        line2.add(stop8);
        line2.add(stop9);
        line2.add(stop4);
        line2.add(stop10);
        line2.add(stop11);


        lines.add(line1);
        lines.add(line2);

        //Update the HashMap.
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

  /** Disable some boxes once the user selected one.
   *  @param event Select one box.*/
  @FXML
  public void selectBox(javafx.event.ActionEvent event) {
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
            startStation.setText("");
            endStation.setText("");

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

  /** The function is designed for the Confirm Trip button.
   * @throws ParseException This is to ensure the format of time typed by user.
   * @throws IOException handle failed or interrupted I/O operation.
   */
  public void confirmTrip() throws ParseException, IOException{
        HelpSerialize helpSerialize = new HelpSerialize();
        StationFactory stationFactory = new StationFactory();
        LogWriter logWriter = new LogWriter();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if ((startStation.getText().isEmpty() & endStation.getText().isEmpty()) | selected.isEmpty()) {
        alert("Necessary information required!");
    } else {
      if (selected.size() == 1) {
          if (!startTime.toString().isEmpty() & !endTime.toString().isEmpty()){
              alert("Enter and exit at the same station, no fare deducted.");
        } else {
          String line = "";
          for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).contains(selected.get(0))) {
              Integer lineNum = 1 + i;
              line = lineNum.toString();
            }
          }
          if (startTime.getText().isEmpty()) {
            String end = boxToString.get(selected.get(0));
            Station endStation = stationFactory.newStation(end, "subway", line);
            Date endDate = df.parse(endTime.getText() + ":00");
            card.updateOnTap("exits", endStation, endDate, "subway");
          } else {
            String start = boxToString.get(selected.get(0));
            Station endStation = stationFactory.newStation(start, "subway", line);

            Date startDate = df.parse(startTime.getText() + ":00");
            card.updateOnTap("enters", endStation, startDate, "subway");
          }
      }
      } else {
        normalConfirm(df, stationFactory);
      }
        }
        for (CheckBox checkBox: boxToString.keySet()){
            checkBox.setDisable(false);
            checkBox.setSelected(false);
        }
        clear(helpSerialize, logWriter);
    }

  /**
   *  @param helpSerialize: used to serialize data
   * @param logWriter: used to write log information
   * @throws IOException: handle failed or interrupted I/O operation
   */
  private void clear(HelpSerialize helpSerialize, LogWriter logWriter) throws IOException {
        selected.clear();
        startTime.clear();
        endTime.clear();
        cardController.helpShowBalance(card.getBalance());
        helpSerialize.serializeUser(User.getUsers());
        logWriter.helpLog(Level.INFO, "Valid Trip. Fare deducted accordingly.");
        dashboard.helpUpdateInfo();
        startStation.setText("");
        endStation.setText("");
        alert("Trip Completed! Thanks for using our system!");
    }

    /** A helper method for confirmTrip.
     * @param df: the date format we need
     * @param stationFactory: the class containing station information
     * @throws ParseException: This is to ensure the format of time typed by user
     */
    private void normalConfirm(SimpleDateFormat df, StationFactory stationFactory)throws ParseException{
        String start = boxToString.get(selected.get(0));
        String end = boxToString.get(selected.get(1));
        String line = "";
        for (int i = 0; i < lines.size(); i++){
            if (lines.get(i).contains(selected.get(0))){
                Integer lineNum = 1 + i;
                line = lineNum.toString();
            }
        }
        Station startStation = stationFactory.newStation(start, "subway", line);
        Station endStation = stationFactory.newStation(end, "subway", line);
        Date startDate = df.parse(startTime.getText() + ":00");
        Date endDate = df.parse(endTime.getText() + ":00");
        card.updateOnTap("enters", startStation, startDate,
                "subway");
        card.updateOnTap("exits", endStation, endDate,
                "subway");
    }

    /** The setter of previous page controller.
     *
     * @param cardController: the controller of the card that
     * @param dashboard: the controller of previous stage
     */
    void setPreviousController(CardController cardController, Dashboard dashboard){
        this.cardController = cardController;
        this.dashboard = dashboard;
    }

}
