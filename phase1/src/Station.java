import java.util.ArrayList;

class Station {
    /**
     * this class is used to record stations for subway.
     */
    private String name;
    private ArrayList<Station> neighbour = new ArrayList<>();
    private Station pathVia;
    private int distance;

    Station(String name, ArrayList<Station> neighbour){
        this.name = name;
        StationManager.addStations(this);
        this.neighbour = neighbour;
    }

    Station(String name){
        this.name = name;
        StationManager.addStations(this);
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

    void setDistance(int distance){
        this.distance = distance;
    }

    int getDistance(){
        return distance;
    }
}
