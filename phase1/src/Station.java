import java.util.*;

class Station {
    /**
     * this class is used to record stations for subway.
     */
    private String name;
    private Integer distance;
    private ArrayList<Station> neighbours = new ArrayList<>();
    private String vehicle;

    Station(String name, String vehicle){
        this.name = name;
        if (vehicle.equals("subway")){
            StationManager.addStations(this);
        }
        else{
            StationManager.addStops(this);
        }

        this.vehicle = vehicle;
    }

    void setDistance(Integer distance){
        this.distance = distance;
    }

    Integer getDistance(){
        return distance;
    }

    ArrayList<Station> getNeighbours(){
        return neighbours;
    }

    String getName(){
        return name;
    }

    void addNeighbours(Station neighbour){
        this.neighbours.add(neighbour);
    }

    String getVehicle() {
        return vehicle;
    }

}
