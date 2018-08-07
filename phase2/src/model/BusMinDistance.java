package model;

import java.util.ArrayList;
import java.util.Collection;

public class BusMinDistance implements MinDistance {
    /** StationFactory collecting of all existing station information.*/
    private StationFactory stationFactory;

    /** A setter of variable stationFactory. */
    BusMinDistance(StationFactory stationFactory){
        this.stationFactory = stationFactory;
    }

    /** A helper method for minDistance.*/
    private void addUndiscovered(Collection<BusStation> busStations, ArrayList<BusStation> undiscovered, Station source) {
        for (BusStation stop : busStations) {
            if (stop.getLineNumber() == ((BusStation)source).getLineNumber()) {
                stop.setDistance(Integer.MAX_VALUE);
                undiscovered.add(stop);
            }
        }
    }

    /** A helper method for minDistance.*/
    private void updateDistance(BusStation minStation) {
        for (Station neighbour : minStation.getNeighbours()) {
            // update the distance variable for all neighbours of that station.
            int newDistance = minStation.getDistance() + 1;
            if (newDistance < neighbour.getDistance()) {
                neighbour.setDistance(newDistance);
            }
        }
    }

    /** Calculate the distance between source and destination.
     * The distance between two adjacent stations is 1.*/
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
