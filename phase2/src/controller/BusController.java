package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class BusController extends Controller implements Initializable, SelectStation{

    @FXML
    private CheckBox stop1,stop2,stop3,stop4,stop5,stop6,stop7,stop8,stop9,
            stop10,stop11,stop12,stop13,stop14;

    private ArrayList<CheckBox> line1 = new ArrayList<>();

    private ArrayList<CheckBox> line2 = new ArrayList<>();

    private HashMap<CheckBox, String> boxToString = new HashMap<>();

    private Card card;

    private ArrayList<CheckBox> selected = new ArrayList<>();

    @FXML
    private TextField startTime;

    @FXML
    private TextField endTime;

    private Stage previousStage;

    private ArrayList<ArrayList<CheckBox>> lines = new ArrayList<>();
    private CardController cardController;

    public void setCard(Card card){
        this.card = card;
    }

    public void selectBox (javafx.event.ActionEvent event){
        CheckBox newSelect = ((CheckBox) event.getSource());
        if (newSelect.isSelected()){
            disable(newSelect);
        }
        else{
            enable(newSelect);

        }
    }

    private void disable(CheckBox box){
        selected.add(box);
        if (selected.size() == 2){
            for (ArrayList<CheckBox> line : lines){
                for (CheckBox item: line){
                    if (! item.equals(box) & ! item.equals(selected.get(0))){
                        item.setDisable(true);
                    }
                }
            }
    } else {
      ArrayList<CheckBox> list = new ArrayList<>();
      for (ArrayList<CheckBox> line : lines) {
        if (line.contains(box)) {
          list.addAll(line);
        }
      }
            helpSetDisable(box, list);
        }
    }

    private void helpSetDisable(CheckBox box, ArrayList<CheckBox> list) {
        for (ArrayList<CheckBox> line : lines) {
          if (!line.contains(box)) {
            for (CheckBox item : line) {
              if (!list.contains(item)) {
                item.setDisable(true);
              }
            }
          }
        }
    }

    private void enable(CheckBox box){
        selected.remove(box);
        if (selected.size() == 0){
            for (ArrayList<CheckBox> line : lines){
                for (CheckBox item : line){
                    item.setDisable(false);
                }
            }
        }
        else{
            CheckBox start = selected.get(0);
            for (ArrayList<CheckBox> line : lines){
                for (CheckBox item : line){
                    item.setDisable(false);
                }
            }
            ArrayList<CheckBox> list = new ArrayList<>();
            for (ArrayList<CheckBox> line : lines) {
                if (line.contains(start)) {
                    list.addAll(line);
                }
            }
            helpSetDisable(start, list);
        }
    }


    @FXML
    public void goBackPage(javafx.event.ActionEvent event) throws Exception {
        Stage busController = (Stage) ((Node) event.getSource()).getScene().getWindow();
        previousStage.show();
        busController.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        line1.add(stop1);
        line1.add(stop2);
        line1.add(stop3);
        line1.add(stop4);
        line1.add(stop5);
        line1.add(stop6);
        line1.add(stop7);

        line2.add(stop8);
        line2.add(stop9);
        line2.add(stop3);
        line2.add(stop10);
        line2.add(stop11);
        line2.add(stop12);
        line2.add(stop13);
        line2.add(stop6);
        line2.add(stop14);

        lines.add(line1);
        lines.add(line2);

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

    void setPreviousStage(Stage stage){
        this.previousStage = stage;
    }

    public void confirmTrip() throws ParseException {
        StationFactory stationFactory = new StationFactory();
        String start = boxToString.get(selected.get(0));
        String end = boxToString.get(selected.get(1));
        String line;
        if (line1.contains(selected.get(0)) & line1.contains(selected.get(1))){
            line = "1";
        }else{
            line = "2";
        }

        Station startStation = stationFactory.newStation(start, "bus", line);
        Station endStation = stationFactory.newStation(end, "bus", line);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = df.parse(startTime.getText() + ":00");
        Date endDate = df.parse(endTime.getText() + ":00");

        card.updateOnTap("enters", startStation, startDate,
                "bus", stationFactory);
        card.updateOnTap("exits", endStation, endDate,
                "bus", stationFactory);
        for (ArrayList<CheckBox> checkBoxes : lines){
            for(CheckBox item : checkBoxes){
                item.setDisable(false);
            }
        }
        selected.get(0).setSelected(false);
        selected.get(1).setSelected(false);
        selected.clear();
        startTime.clear();
        endTime.clear();
        cardController.helpShowBalance(card.getBalance());
    }

    void setPreviousController(CardController cardController){
        this.cardController = cardController;
    }


}
