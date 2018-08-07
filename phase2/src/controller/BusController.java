package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.*;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.*;

public class BusController extends Controller implements Initializable, SelectStation{

    /** CheBoxes representing bus stations on screen.*/
    @FXML
    private CheckBox stop1,stop2,stop3,stop4,stop5,stop6,stop7,stop8,stop9,
            stop10,stop11,stop12,stop13,stop14;

    /** A helper class used by passengers to take bus.*/
    private BusConfirmTrip helper;

    /** A HashMap converting CheckBoxes to station name.*/
    private HashMap<CheckBox, String> boxToString = new HashMap<>();

    /** Current card user used to take a bus.*/
    private Card card;

    /** The time when user get on a bus.*/
    @FXML
    private TextField startTime;

    /** The time when user get off a bus. */
    @FXML
    private TextField endTime;

    /** A label telling the user the start station.*/
    @FXML
    private Label startStation;

    /** A label telling the user the end station.*/
    @FXML
    private Label endStation;

    /** The previous stage of current stage, used to go to last page.*/
    private Stage previousStage;

    /** The controller of the card user is using.*/
    private CardController cardController;

    /** The controller of last Dashboard page. */
    private Dashboard dashboard;

    BusController(){
        this.helper = new BusConfirmTrip(boxToString);
    }

    /** Setter of variable card.*/
    public void setCard(Card card){
        this.card = card;
    }

    /** User select stations and make other stations disable to chose.*/
    public void selectBox (javafx.event.ActionEvent event){
        CheckBox newSelect = ((CheckBox) event.getSource());
        if (newSelect.isSelected()){
            helper.disable(newSelect);
        }
        else{
            helper.enable(newSelect);

        }
        ArrayList<CheckBox> selected = helper.selected;
        if (selected.size() == 1){
            startStation.setText(boxToString.get(selected.get(0)));
            endStation.setText("");
        }else if (selected.size() == 2){
            startStation.setText(boxToString.get(selected.get(0)));
            endStation.setText(boxToString.get(selected.get(1)));
        }else{
            startStation.setText("");
            endStation.setText("");
        }
    }

    /** Go to the last page.*/
    @FXML
    public void goBackPage(javafx.event.ActionEvent event){
        Stage busController = (Stage) ((Node) event.getSource()).getScene().getWindow();
        previousStage.show();
        busController.close();
    }

    /** Initialize the BusController.*/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<CheckBox> line1 = new ArrayList<>();
        line1.add(stop1);
        line1.add(stop2);
        line1.add(stop3);
        line1.add(stop4);
        line1.add(stop5);
        line1.add(stop6);
        line1.add(stop7);

        ArrayList<CheckBox> line2 = new ArrayList<>();
        line2.add(stop8);
        line2.add(stop9);
        line2.add(stop3);
        line2.add(stop10);
        line2.add(stop11);
        line2.add(stop12);
        line2.add(stop13);
        line2.add(stop6);
        line2.add(stop14);

        ArrayList<ArrayList<CheckBox>> lines = new ArrayList<>();
        lines.add(line1);
        lines.add(line2);
        helper.setLines(lines);

        boxToString.put(stop1, "Bloor-Yonge");
        boxToString.put(stop2, "Queens_Park");
        boxToString.put(stop3, "College");
        boxToString.put(stop4, "Museum");
        boxToString.put(stop5, "Queen");
        boxToString.put(stop6, "King");
        boxToString.put(stop7, "Union_Station");
        boxToString.put(stop8, "Yuxin");
        boxToString.put(stop9, "Yunfan");
        boxToString.put(stop10, "Xiaolei");
        boxToString.put(stop11, "Yaru");
        boxToString.put(stop12, "Frank");
        boxToString.put(stop13, "Sophia");
        boxToString.put(stop14, "Steven");
    }

    /** Setter of the variable previousStage.*/
    public void setPreviousStage(Stage stage){
        this.previousStage = stage;
    }

    /** Confirm the stations and time selected bu user and start a trip.*/
    public void confirmTrip()throws ParseException, IOException{
        startStation.setText("");
        endStation.setText("");
        helper.confirm(card, startTime, endTime, cardController);
        dashboard.helpUpdateInfo();
    }

    /** Setter of previousController.*/
    void setPreviousController(CardController cardController, Dashboard dashboard){
        this.cardController = cardController;
        this.dashboard = dashboard;
    }
}
