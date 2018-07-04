import java.util.ArrayList;

class Station {
    /**
     * this class is used to record stations for subway.
     */
    private String name;

    private static ArrayList<String> stations = new ArrayList<>();

    Station(String name){
        this.name = name;
        Station.stations.add(name);
    }

    static ArrayList<String> getStations() {
        return stations;
    }
}
