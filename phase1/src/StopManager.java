import java.util.ArrayList;

public class StopManager {
    private ArrayList<Stop> stopSet = new ArrayList<>();


    void addStop(String stationID){
        Stop stop = new Stop(stationID);
        stopSet.add(stop);
    }
}
