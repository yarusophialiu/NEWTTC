import java.util.ArrayList;

class StopManager {
    private static ArrayList<Stop> stopSet = new ArrayList<>();


    static void addStop(String stationID){
        Stop stop = new Stop(stationID);
        stopSet.add(stop);
    }

    static ArrayList<Stop> getStopSet(){
        return stopSet;
    }
}
