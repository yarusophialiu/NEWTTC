package model;

import java.io.Serializable;
import java.util.Date;

public class TripInfoHandler implements Serializable {
    /** The start station of the trip. */
    Station entrance;
    /** The end station of the trip. */
    Station exit;
    /** The start time of the trip. */
    Date enterTime;
    /** The end time of the trip. */
    Date exitTime;

    /** A setter to set exit information when user tap the card when getting out of the station. */
    void setExit(Station exit, Date exitTime) {
        this.exit = exit;
        this.exitTime = exitTime;
    }

    Station getEntrance() {
        return entrance;
    }

    Station getExit() {
        return exit;
    }

    Date getEnterTime(){
        return enterTime;
    }

    /** Used to calculate the time duration of this trip. */
    Long tripTime() {
        return exitTime.getTime() - enterTime.getTime();
    }

    /** A getter of the time user exit the destination station. */
    Date getExitTime() {
        return exitTime;
    }
}
