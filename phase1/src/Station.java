import java.util.*;

class Station {
    /**
     * this class is used to record stations for subway.
     */
    private String name;
    private Integer distance;
    private ArrayList<Station> neighbours = new ArrayList<>();

    Station(String name){
        this.name = name;
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


}
