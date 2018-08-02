package model;

import java.util.ArrayList;

class BusStation extends Station {
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

    /** return an ArrayList of stations that is the neighbour of this station. */
    ArrayList<BusStation> getNeighbours() {
        return neighbours;
    }

    /** add neighbours to this station. */
    void addNeighbours(BusStation neighbour) {
        this.neighbours.add(neighbour);
    }

    int getLineNumber(){
        return this.lineNumber;
    }
}
