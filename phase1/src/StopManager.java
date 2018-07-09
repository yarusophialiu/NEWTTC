import java.util.ArrayList;

class StopManager {
    private ArrayList<Stop> stopSet = new ArrayList<>();


    void addStop(String stationID){
        Stop stop = new Stop(stationID);
        stopSet.add(stop);
    }
}
