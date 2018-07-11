import java.util.Date;

/**
 * This class is for recording information about the Trip and calculate how long the trip is in
 * order to make sure that the trip is still continuous.
 */
class Trip {
  /** The start station of the trip. */
  private Station entrance;
  /** The end station of the trip. */
  private Station exit;
  /** The start time of the trip. */
  private Date enterTime;
  /** The end time of the trip. */
  private Date exitTime;
  /** vehicles used in this trip. */
  private String transportation;
  /** A boolean to indicate whether the trip is continuous trip in a two-hour period. */
  private boolean isContinuous = false;
  /** A double to represent the cumulative fare in the chain of continuous trips in two hours. */
  private double currentFare;
  /** Represent the length of time interval of continuous trip. */
  private Long continuousTime = (long) 0;

  /** initialize a new Trip when enters the station */
  Trip(Station entrance, Date enterTime, String vehicle) {
    this.enterTime = enterTime;
    this.entrance = entrance;
    this.transportation = vehicle;
  }

  Station getEntrance() {
    return entrance;
  }

  Station getExit() {
    return exit;
  }

  /** A setter to set exit information when user tap the card when getting out of the station. */
  void setExit(Station exit, Date exitTime) {
    this.exit = exit;
    this.exitTime = exitTime;
  }

  /** A setter to set the trip to continuous. */
  void setContinuous() {
    this.isContinuous = true;
  }

  /** A setter to set the trip to discontinuous. */
  void setDiscontinuous() {
    this.isContinuous = false;
  }

  /**
   * A setter to update the current fare to make sure that the user aren't paying over $6 when
   * he/she is still in a continuous trip.
   */
  void setCurrentFare(double fare) {
    this.currentFare = fare;
  }

  /** A setter to update the how long the user has been in his/her continuous trip. */
  void setContinuousTime(Long time) {
    this.continuousTime = time;
  }

  /** A getter to get the current fare that the user has payed for this continuous trip. */
  double getCurrentFare() {
    return this.currentFare;
  }

  /** A getter to get the how long the user has been in this continuous trip. */
  Long getContinuousTime() {
    return continuousTime;
  }

  /** A getter to if this trip is continuous. */
  boolean getIsContinuous() {
    return this.isContinuous;
  }

  /** Used to calculate the time duration of this trip. */
  Long tripTime() {
    return exitTime.getTime() - enterTime.getTime();
  }

  /** A getter of the time user enters the source station. */
  Date getEnterTime() {
    return enterTime;
  }

  /** A getter of the time user exit the destination station. */
  Date getExitTime() {
    return exitTime;
  }
}
