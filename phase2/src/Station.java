import java.util.*;

/** this class is used to record stations for subway and bus depending on the vehicle variable. */
class Station {
  /** store name of the user. */
  private String name;
  /** store the distance of this station to the source station(station where the trip started.) */
  private Integer distance;
  /** stores the neighbouring stations of this station */
  private ArrayList<Station> neighbours = new ArrayList<>();
  /** store if its a bus or subway */
  private String vehicle;

  /** initiate a new Station. */
  Station(String name, String vehicle) {
    this.name = name;
    if (vehicle.equals("subway")) {
      StationManager.addStations(this);
    } else {
      StationManager.addStops(this);
    }

    this.vehicle = vehicle;
  }

  /** used to set the distance of this station to the source station. */
  void setDistance(Integer distance) {
    this.distance = distance;
  }

  /** return the distance of this station to the source station. */
  Integer getDistance() {
    return distance;
  }

  /** return an ArrayList of stations that is the neighbour of this station. */
  ArrayList<Station> getNeighbours() {
    return neighbours;
  }

  /** return the name of this station. */
  String getName() {
    return name;
  }

  /** add neighbours to this station. */
  void addNeighbours(Station neighbour) {
    this.neighbours.add(neighbour);
  }

  /** return whether this station is a subway station or bus station. */
  String getVehicle() {
    return vehicle;
  }
}
