import java.util.ArrayList;

class Station {
    /**
     * this class is used to record stations for subway.
     */
    private static ArrayList<String> stations = new ArrayList<>();
    private String name;
    private ArrayList<Station> neighbour = new ArrayList<>();
    private Station pathVia;
    private int distance;

    Station(String name, ArrayList<Station> neighbour){
        this.name = name;
        Station.stations.add(name);
        this.neighbour = neighbour;
    }

    Station(String name){
        this.name = name;
        Station.stations.add(name);
    }


    static ArrayList<String> getStations() {
        return stations;
    }

    Station getPathVia(){
        return pathVia;
    }

    void setPathVia(Station pathVia){
        this.pathVia = pathVia;
    }

    ArrayList<Station> getNeighbour(){
        return this.neighbour;
    }

    void setNeighbour(ArrayList<Station> neighbour){
        this.neighbour = neighbour;
    }
}
