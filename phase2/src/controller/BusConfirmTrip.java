package controller;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import model.Card;
import model.Station;
import model.StationFactory;
import model.User;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;

class BusConfirmTrip {
    /** An ArrayList containing different bus operating line.*/
    private ArrayList<ArrayList<CheckBox>> lines;

    /** Confirm special case selected by user and start a Trip.*/
    void confirm (ArrayList<CheckBox> selected, HashMap<CheckBox, String> boxToString , Card card,
                  TextField startTime, TextField endTime, CardController cardController)
                    throws ParseException, IOException {
        HelpSerialize helpSerialize = new HelpSerialize();
        StationFactory stationFactory = new StationFactory();
        LogWriter logWriter = new LogWriter();
        if (selected.size() == 1){
            if (startTime.getText().isEmpty()){
                String end = boxToString.get(selected.get(0));
                String line = "";
                for (int i = 0; i < lines.size(); i++){
                    if (lines.get(i).contains(selected.get(0))){
                        Integer lineNum = 1 + i;
                        line = lineNum.toString();
                    }
                }
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Station endStation = stationFactory.newStation(end, "bus", line);

                Date endDate = df.parse(endTime.getText() + ":00");
                card.updateOnTap("exits", endStation, endDate,
                        "bus", stationFactory);
            }
        }else {
            normalConfirm(selected, boxToString, card, startTime, endTime);
            }

        for (ArrayList<CheckBox> checkBoxes : lines){
            for(CheckBox item : checkBoxes){
                item.setDisable(false);
            }
        }
        for (CheckBox item : selected){
            item.setSelected(false);
        }
        selected.clear();
        startTime.clear();
        endTime.clear();
        cardController.helpShowBalance(card.getBalance());
        helpSerialize.serializeUser(User.getUsers());
        logWriter.helpLog(Level.INFO, "Valid Trip. Fare deducted accordingly."); // 改一下这个地方的String
        cardController.alert("Trip Completed! Thanks for using our system!");
    }

    /** Confirm the stations and time selected by user and start a trip.*/
    private void normalConfirm(ArrayList<CheckBox> selected, HashMap<CheckBox, String> boxToString , Card card,
                       TextField startTime, TextField endTime)
            throws ParseException{
        StationFactory stationFactory = new StationFactory();
            String start = boxToString.get(selected.get(0));
            String end = boxToString.get(selected.get(1));
            String line = "";
            for (int i = 0; i < lines.size(); i++){
                if (lines.get(i).contains(selected.get(0)) & lines.get(i).contains(selected.get(1))){
                    Integer lineNum = 1 + i;
                    line = lineNum.toString();
                }
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
    }

    /** A helper method for selected Box.
     * @ param box: the CheckBox which is selected.
     * @ param selected: an ArrayList of selected CheckBox.
     */
    void disable(CheckBox box, ArrayList<CheckBox> selected){
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

    /** A helper method for disable.
     * @ param: box, list
     */
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

    /** A helper method for selectedBox.
     * @ param box: the box which is selected.
     * @ param selected: an ArrayList containing selected CheckBox.*/
    void enable(CheckBox box, ArrayList<CheckBox> selected){
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

    /** Setter of variable lines.*/
    void setLines (ArrayList<ArrayList<CheckBox>> lines){
        this.lines = lines;
    }
}
