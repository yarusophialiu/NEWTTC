package model;

import java.util.ArrayList;
import java.util.Collection;

/** Calculate the minimum distance in a bus trip.*/
public class BusMinDistance implements MinDistance {
    /** StationFactory collecting of all existing station information.*/
    private StationFactory stationFactory;

    /** A setter of variable stationFactory. */
    BusMinDistance(StationFactory stationFactory){
        this.stationFactory = stationFactory;
    }

    /** Add undiscoverred bus stations
     *  @param busStations a collection of bus stations.
     *  @param undiscovered a collection of undiscoverred bus stations.
     *  @param source the start of bus trip.
     */
    private void addUndiscovered(Collection<BusStation> busStations, ArrayList<BusStation> undiscovered, Station source) {
        for (BusStation stop : busStations) {
            if (stop.getLineNumber() == ((BusStation)source).getLineNumber()) {
                stop.setDistance(Integer.MAX_VALUE);
                undiscovered.add(stop);
            }
        }
    }

    /** Update distance of all neighbours of minstation
     *  @param minStation a bus station that has minimum distance to source station so far
     */
    private void updateDistance(BusStation minStation) {
        for (Station neighbour : minStation.getNeighbours()) {
            // update the distance variable for all neighbours of that station.
            int newDistance = minStation.getDistance() + 1;
            if (newDistance < neighbour.getDistance()) {
                neighbour.setDistance(newDistance);
            }
        }
    }

    /** Calculate the distance between source and destination. The distance between two adjacent stations is 1
     *  @param source start of the bus trip
     *  @param destination end of the bus trip
     */
    @Override
    public int minDistance (Station source, Station destination){
        Collection<BusStation> busStations = stationFactory.getBusStationHashMap().values();
        ArrayList<BusStation> undiscovered = new ArrayList<>();
        addUndiscovered(busStations, undiscovered, source);
        source.setDistance(0);

        while (!undiscovered.isEmpty()) {
            BusStation minStation = undiscovered.get(0);
            Integer min = minStation.getDistance();
            for (Station station : undiscovered) {
                if (station.getDistance() < min) {
                    min = station.getDistance();
                    minStation = (BusStation) station;
                }
            }
            undiscovered.remove(minStation);
            updateDistance(minStation);
        }
        return destination.getDistance();
    }
}
