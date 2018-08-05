package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import model.Card;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class SubwayController extends Controller implements Initializable {

    private Card card;

    private Stage previousStage;

    @FXML
    private CheckBox stop1,stop2,stop3,stop4,stop5,stop6,stop7,stop8,stop9,
            stop10,stop11;

    private ArrayList<CheckBox> line1 = new ArrayList<>();

    private ArrayList<CheckBox> line2 = new ArrayList<>();

    private HashMap<CheckBox, String> boxToString = new HashMap<>();

    public void setCard(Card card){
        this.card = card;
    }

    public void goBackPage(javafx.event.ActionEvent event) throws Exception {
        Stage subwayController = (Stage) ((Node) event.getSource()).getScene().getWindow();
        previousStage.show();
        subwayController.close();
    }

    void setPreviousStage(Stage stage){
        this.previousStage = stage;
    }

    public void initialize(URL location, ResourceBundle resources) {
        line1.add(stop1);
        line1.add(stop2);
        line1.add(stop3);
        line1.add(stop4);
        line1.add(stop5);
        line1.add(stop6);
        line1.add(stop7);

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
    void selectedBoxAmount(){
        ArrayList<CheckBox> selected = new ArrayList<>();
        for (CheckBox cb: boxToString.keySet()){
            if (cb.isSelected()){
                selected.add(cb);
            }
        }
        if (selected.size() == 0 | selected.size() == 1){
            for (CheckBox cb: boxToString.keySet()){
                cb.setDisable(false);
            }
        }
//        if (selected.size() == 1){
//            CheckBox fistSelect = selected.get(0);
//            if (!line1.contains(fistSelect)){
//                for (CheckBox cb: line1){
//                    cb.setDisable(true);
//
//                }
//                for (CheckBox cb: line2){
//                    cb.setDisable(false);
//                }
//            }else if (!line2.contains(fistSelect)){
//                for (CheckBox cb: line2){
//                    cb.setDisable(true);
//                }
//                for (CheckBox cb: line1){
//                    cb.setDisable(false);
//                }
//            }
//        }
        if (selected.size() == 2){
            for (CheckBox cb: boxToString.keySet()){
                if (!cb.isSelected()){
                    cb.setDisable(true);
                }
            }
        }
    }

}
