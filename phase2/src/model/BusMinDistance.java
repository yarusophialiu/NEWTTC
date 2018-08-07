package model;

import java.util.ArrayList;
import java.util.Collection;

/** Class BusMinDistance is the implementation of strategy min distance, the main functionality is to calculate the
 * distance between the source and destination station..*/
public class BusMinDistance implements MinDistance {

  /**
   * @param busStations a collection of busStation that records all the bus stations.
   * @param undiscovered a collection bus stations that the distance to the source haven't been calculated.
   * @param source the station that the trip starts.
   */
  private void addUndiscovered(
      Collection<BusStation> busStations, ArrayList<BusStation> undiscovered, Station source) {
        for (BusStation stop : busStations) {
            if (stop.getLineNumber() == ((BusStation)source).getLineNumber()) {
                stop.setDistance(Integer.MAX_VALUE);
                undiscovered.add(stop);
            }
        }
    }

  /** A helper method that helps update the distance to the source station for the neighbouring station of minStation.
   * @param minStation  the station that currently most closest to source.*/
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
     * The distance between two adjacent stations is 1.
     * @param destination the destination of this trip.
     * @param source  the source of this trip.*/
    @Override
    public int minDistance (Station source, Station destination){
        StationFactory stationFactory = new StationFactory();
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
