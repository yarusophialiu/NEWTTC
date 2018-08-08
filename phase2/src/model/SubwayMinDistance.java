package model;

import java.util.ArrayList;
import java.util.Collection;

/** Class SubwayMinDistance is the implementation of strategy MinDistance, it if used to calculate the minimum
 * distance between the source station and destination station.*/
public class SubwayMinDistance implements MinDistance {

  /** Method minDistance was used to calculate the minimum distance between the source and the destination station.
   * @param source the station that the user start the trip in.
   * @param destination the destination station that the user end his trip in.
   * @return
   */
  @Override
  public int minDistance(Station source, Station destination) {
        StationFactory stationFactory = new StationFactory();
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
