import java.util.ArrayList;

/**
 * this class is used to manage all the stations and sort them by their type (subway or bus), and it
 * help used these information to calculate the minimum distance from the source station to the
 * destination station using the simplified version Dijkstra Algorithm where the distance between
 * vertices (in our case, stations) are all 1.
 */
class StationManager {
  /** An ArrayList of Station that stores all stations for subway */
  private static ArrayList<Station> stationSet = new ArrayList<>();
  /** An ArrayList of String that stores all stations ID for subway */
  private static ArrayList<String> stationIDSet = new ArrayList<>();
  /** An ArrayList of Station that stores all stop for bus */
  private static ArrayList<Station> stopSet = new ArrayList<>();
  /** An ArrayList of String that stores all stop ID for bus */
  private static ArrayList<String> stopIDSet = new ArrayList<>();

  /** add this station to the stationSet and its ID to stationIDSet */
  static void addStations(Station station) {
    stationSet.add(station);
    stationIDSet.add(station.getName());
  }

  /** add this station to the stopSet and its ID to stopIDSet */
  static void addStops(Station stop) {
    stopSet.add(stop);
    stopIDSet.add(stop.getName());
  }

  /** and getter for all the stations for subway */
  static ArrayList<Station> getStationSet() {
    return stationSet;
  }

  /** and getter for all the stations' ID for subway */
  static ArrayList<String> getStationIDSet() {
    return stationIDSet;
  }

  /** and getter for all the stations for bus */
  static ArrayList<Station> getStopSet() {
    return stopSet;
  }

  /** and getter for all the stations IDs' for bus */
  static ArrayList<String> getStopIDSet() {
    return stopIDSet;
  }

  /**
   * this method will determin whether the station has been created, if its created already, it will
   * return that instance, if its not, it will create a new instance of Station and return it.
   */
  static Station newStation(String stationID, String vehicle) {
    if (vehicle.equals("subway")) {
      if (stationIDSet.contains(stationID)) {
        for (Station station : stationSet) {
          if (station.getName().equals(stationID)) {
            return station;
          }
        }
      }
      return new Station(stationID, vehicle);
    } else {
      if (stopIDSet.contains(stationID)) {
        for (Station stop : stopSet) {
          if (stop.getName().equals(stationID)) {
            return stop;
          }
        }
      }
      return new Station(stationID, vehicle);
    }
  }

  /**
   * used to calculate the minimum distance between stations or stops using the Dijkstra Algorithm.
   */
  static int minDistance(Station source, Station destination) {
    source.setDistance(0);
    ArrayList<Station> undiscovered = new ArrayList<>();
    if (source.getVehicle().equals("subway")) {
      // used to set the distance of all stations other than source to infinity since all of them
      // are undiscovered.
      for (Station station : stationSet) {
        if (station != source) {
          station.setDistance(Integer.MAX_VALUE);
        }
        undiscovered.add(station);
      }
    } else {
      for (Station stop : stopSet) {
        if (stop != source) {
          stop.setDistance(Integer.MAX_VALUE);
        }
        undiscovered.add(stop);
      }
    }
    while (!undiscovered.isEmpty()) {
      Station minStation = undiscovered.get(0);
      Integer min = minStation.getDistance();
      for (Station station : undiscovered) {
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
