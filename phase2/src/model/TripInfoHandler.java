package model;

import java.io.Serializable;
import java.util.Date;

/** This class is to handle events on setting and acquiring the station and date information of the trip. */
class TripInfoHandler implements Serializable {
    /** The start station of the trip. */
    Station entrance;
    /** The end station of the trip. */
    Station exit;
    /** The start time of the trip. */
    Date enterTime;
    /** The end time of the trip. */
    Date exitTime;

    /** A setter to set exit information when user tap the card when getting out of the station.
     * @param exit the station that this trip end in.
     * @param exitTime the time that this trip ends.*/
    void setExit(Station exit, Date exitTime) {
        this.exit = exit;
        this.exitTime = exitTime;
    }

    /** A getter to get the station that the user enters at the beginning of this trip. */
    Station getEntrance() {
        return entrance;
    }

    /** A getter to get the station that the user exits at the end of this trip. */
    Station getExit() {
        return exit;
    }

    /** A getter to get the enter time that the user enters the station at the beginning of this trip. */
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
