package model;

import java.util.Date;

public class Trip{
    /** The start station of the trip. */
    private Station entrance;
    /** The end station of the trip. */
    private Station exit;
    /** The start time of the trip. */
    private Date enterTime;
    /** The end time of the trip. */
    private Date exitTime;
    /** A boolean to indicate whether the trip is continuous trip in a two-hour period. */
    private boolean isContinuous = false;
    /** A double to represent the cumulative fare in the chain of continuous trips in two hours. */
    private double currentFare;
    /** Represent the length of time interval of continuous trip. */
    private Long continuousTime = (long) 0;
    /** vehicles used in this trip. */
    private boolean transportation;

    /** initialize a new Trip when enters the station */
    Trip(Station entrance, Date enterTime, String vehicle) {
        this.enterTime = enterTime;
        this.entrance = entrance;
        this.transportation = vehicle.equals("subway");
    }

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

    /**
     * A setter to update the current fare to make sure that the user aren't paying over $6 when
     * he/she is still in a continuous trip.
     */
    void setCurrentFare(double fare) {
        this.currentFare = fare;
    }

    Date getEnterTime(){
        return enterTime;
    }

    /** A getter to get the current fare that the user has payed for this continuous trip. */
    double getCurrentFare() {
        return this.currentFare;
    }

    /** A getter to if this trip is continuous. */
    boolean getIsContinuous() {
        return this.isContinuous;
    }

    /** Used to calculate the time duration of this trip. */
    Long tripTime() {
        return exitTime.getTime() - enterTime.getTime();
    }

    /** A getter of the time user exit the destination station. */
    Date getExitTime() {
        return exitTime;
    }

    long getContinuousTime(){
        return continuousTime;
    }

    /** A getter for the type of transportation user used in this trip. */
    Boolean getTransportation() {
        return transportation;
    }

    void updateContinuity(Trip trip){
        if (trip == this) {
            this.continuousTime += tripTime();
            this.isContinuous = true;
            // since we only consider the continuous time when we enters the station when calculating fares, therefore,
            // isContinuous should always be true even if time now exceed the time limit.
            // and if this trip was not continuous at the beginning, it should be since it could be the start of another
            // continuous trip.
        }
        else {
            boolean nameEqual = trip.getExit().getName().equals(this.entrance.getName());
            long timeUsedBeforeEnter = this.enterTime.getTime() - trip.getExitTime().getTime();
            this.continuousTime = trip.getContinuousTime() + timeUsedBeforeEnter;
            if (nameEqual && this.continuousTime <= 7200000){
                this.isContinuous = true;
                this.currentFare = trip.getCurrentFare();
            }
            else if (!nameEqual || continuousTime + timeUsedBeforeEnter > 7200000){
                this.isContinuous = false;
                this.continuousTime =(long) 0;
            }
        }
    }
}