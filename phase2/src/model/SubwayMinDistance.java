package model;

import java.util.ArrayList;
import java.util.Collection;

public class SubwayMinDistance implements MinDistance{
    private StationFactory stationFactory;

    SubwayMinDistance(StationFactory stationFactory){
        this.stationFactory = stationFactory;
    }

    @Override
    public int minDistance(Station source, Station destination){
        Collection<SubwayStation> subwayStations = stationFactory.getSubwayStationHashMap().values();
        source.setDistance(0);
        ArrayList<SubwayStation> undiscovered = new ArrayList<>();
        for (SubwayStation station : subwayStations) {
            if (station != source) {
                station.setDistance(Integer.MAX_VALUE);
            }
            undiscovered.add(station);
        }
        while (!undiscovered.isEmpty()) {
            SubwayStation minStation = undiscovered.get(0);
            Integer min = minStation.getDistance();
            for (SubwayStation station : undiscovered) {
                if (station.getDistance() < min) {
                    min = station.getDistance();
                    minStation = station;
                }
            }
            undiscovered.remove(minStation);

            for (Station neighbour : minStation.getNeighbours()) {
                // update the distance variable for all neighbours of that station.
                int newDistance = minStation.getDistance() + 1;
                if (newDistance < neighbour.getDistance()) {
                    neighbour.setDistance(newDistance);
                }
            }
        }
        return destination.getDistance();
    }
}
