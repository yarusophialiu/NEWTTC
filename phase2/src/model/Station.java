package model;

public class Station {
    /** store name of the user. */
    private String name;
    /** store if its a bus or subway */
    private String vehicle;
    /** store the distance of this station to the source station(station where the trip started.) */
    private Integer distance;

    /** initiate a new Station. */
    Station(String name, String vehicle) {
        this.name = name;
        this.vehicle = vehicle;
    }

    /** return the name of this station. */
    String getName() {
        return name;
    }


    /** return whether this station is a subway station or bus station. */
    String getVehicle() {
        return vehicle;
    }

    /** used to set the distance of this station to the source station. */
    void setDistance(Integer distance) {
        this.distance = distance;
    }

    /** return the distance of this station to the source station. */
    Integer getDistance() {
        return distance;
    }

}