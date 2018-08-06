package model;

import java.util.ArrayList;
import java.util.Collection;

public class BusMinDistance implements MinDistance {
    private StationFactory stationFactory;

    BusMinDistance(StationFactory stationFactory){
        this.stationFactory = stationFactory;
    }

    private void addUndiscovered(Collection<BusStation> busStations, ArrayList<BusStation> undiscovered, Station source) {
        for (BusStation stop : busStations) {
            if (stop.getLineNumber() == ((BusStation)source).getLineNumber()) {
                stop.setDistance(Integer.MAX_VALUE);
                undiscovered.add(stop);
            }
        }
    }

    private void updateDistance(BusStation minStation) {
        for (Station neighbour : minStation.getNeighbours()) {
            // update the distance variable for all neighbours of that station.
            int newDistance = minStation.getDistance() + 1;
            if (newDistance < neighbour.getDistance()) {
                neighbour.setDistance(newDistance);
            }
        }
    }

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
