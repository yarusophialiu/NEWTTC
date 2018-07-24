import java.util.ArrayList;

class SubwayStation extends Station{
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

    /** add neighbours to this station. */
    void addNeighbours(SubwayStation neighbour) {
        this.neighbours.add(neighbour);
    }

}
