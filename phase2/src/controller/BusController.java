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

    @FXML
    private CheckBox stop1,stop2,stop3,stop4,stop5,stop6,stop7,stop8,stop9,
            stop10,stop11,stop12,stop13,stop14;


    private BusConfirmTrip helper = new BusConfirmTrip();

    private HashMap<CheckBox, String> boxToString = new HashMap<>();

    private Card card;

    private ArrayList<CheckBox> selected = new ArrayList<>();

    @FXML
    private TextField startTime;

    @FXML
    private TextField endTime;

    @FXML
    private Label startStation;

    @FXML
    private Label endStation;

    private Stage previousStage;

    private CardController cardController;

    private Dashboard dashboard;

    public void setCard(Card card){
        this.card = card;
    }

    public void selectBox (javafx.event.ActionEvent event){
        CheckBox newSelect = ((CheckBox) event.getSource());
        if (newSelect.isSelected()){
            helper.disable(newSelect, selected);
        }
        else{
            helper.enable(newSelect, selected);

        }
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



    @FXML
    public void goBackPage(javafx.event.ActionEvent event){
        Stage busController = (Stage) ((Node) event.getSource()).getScene().getWindow();
        previousStage.show();
        busController.close();
    }

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

    public void setPreviousStage(Stage stage){
        this.previousStage = stage;
    }

    public void confirmTrip()throws ParseException, IOException{
        startStation.setText("");
        endStation.setText("");
        helper.confirm(selected, boxToString, card, startTime, endTime, cardController);
        dashboard.helpUpdateInfo();
    }

    void setPreviousController(CardController cardController, Dashboard dashboard){
        this.cardController = cardController;
        this.dashboard = dashboard;
    }


}
