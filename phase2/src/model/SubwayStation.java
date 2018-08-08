package model;

import java.io.Serializable;
import java.util.ArrayList;

/** class Subway station is an instance of station that represent subway station.*/
public class SubwayStation extends Station implements Serializable {
    /** stores the neighbouring stations of this station */
    private ArrayList<SubwayStation> neighbours = new ArrayList<>();

    /**
     * initiate a new Station.
     *
     * @param name: name of the subway station
     * @param vehicle: types of vehicles that stops at this station.
     */
    SubwayStation(String name, String vehicle) {
        super(name, vehicle);
    }


    /** return an ArrayList of stations that is the neighbour of this station. */
    ArrayList<SubwayStation> getNeighbours() {
        return neighbours;
    }

    /** add neighbours to this station.
     * @param neighbour neighbouring station of this station.*/
     public void addNeighbours(SubwayStation neighbour) {
        this.neighbours.add(neighbour);
    }

}
