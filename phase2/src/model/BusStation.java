package model;

import java.io.Serializable;
import java.util.ArrayList;

public class BusStation extends Station implements Serializable {
    /** stores the neighbouring stations of this station */
    private ArrayList<BusStation> neighbours = new ArrayList<>();
    private int lineNumber;

    /**
     * initiate a new Station.
     *
     * @param name: name of the bus station.
     * @param vehicle: types of vehicles that stops at this station.
     */
    BusStation(String name, String vehicle, Integer lineNumber) {
        super(name, vehicle);
        this.lineNumber = lineNumber;
    }

    /** Return an ArrayList of stations that is the neighbour of this station. */
    ArrayList<BusStation> getNeighbours() {
        return neighbours;
    }

    /** Add neighbours to this station. */
    public void addNeighbours(BusStation neighbour) {
        this.neighbours.add(neighbour);
    }

    int getLineNumber(){
        return this.lineNumber;
    }
}
