package model;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;

/** Class Card used to handle the events that uses card. */
public class Card extends CardBalanceHandler implements Serializable {

    /** A boolean to indicate whether the card is suspended or not. */
    private boolean isSuspended = false;


    /** Initialize a new card. */
    public Card() {
        super(19);
        this.id = adminUser.getHighestID();
        adminUser.highestIDIncrement();
    }

    /** Adjust the boolean isSuspended when the card is suspended and the card is retrieved. */
    public void reverseSuspended() {
        this.isSuspended = !this.isSuspended;
        if (isSuspended) {
            System.out.println("CardController " + id + " has been suspended.");
            logWriter.helpLog(Level.WARNING, "Card " + id + " has been suspended.");
        } else {
            System.out.println("CardController " + id + " has been retrieved.");
            logWriter.helpLog(Level.WARNING, "Card " + id + " has been retrieved.");
        }
    }

    /** Getter for CardController.id. */
    public int getId() {
        return this.id;
    }

  /**This method is called whenever a user tap in or out of the station.
   * @param enterOrExit Indicate whether it enters or exit a station.
   * @param station The station that the card has been tapped on.
   * @param time time indicate when the user tapped the card.
   * @param vehicle indicate which type of vehicle the person is using.
   */
  public void updateOnTap(String enterOrExit, Station station, Date time, String vehicle) {
        if (isSuspended){
            System.out.println("You cannot enter because card " + id + " is suspended " + time);
        }
        else if (myTrip.size() >= 1){ //since the size of myTrip is greater than one, therefore it must be either exiting the first trip for starting a new trip.
            Trip previousTrip = this.myTrip.get(this.myTrip.size() - 1);
            if (enterOrExit.equals("enters")){
                helpEnter(station, time, vehicle, previousTrip);
            }
            else if (enterOrExit.equals("exits")){
                helpExit(station, time, previousTrip);
            }
        }
        else if (enterOrExit.equals("enters")){  //this is the case where this trip is the first trip of that card.
            Trip trip = new Trip(station, time, vehicle);
            myTrip.add(trip);
            if (vehicle.equals("bus")){
                deductFare(2, "enters");
                trip.setCurrentFare(2);
                adminUser.updateTotalFare(2);
            }
        }
        else {  //since this is the case of the first trip of the card, and its exiting, therefore must be entered the station without tapping.
            System.out.println("Exit without enter, 6 dollars deducted from your balance.");
            this.balance -= 6.0; //allow to be negative       // haven't calculate the average monthly fare here.
            logWriter.helpLog(Level.WARNING, "exit without enter, 6 dollars deducted from your balance.");
        }
    }

  /** This is a helper method for the case where the user starts a new trip and its not the first trip of the card.
   * @param station station that the person enters.
   * @param time time user enters the station.
   * @param vehicle vehicle the person is using.
   * @param previousTrip The trip before this one to decide whether the trip is continuous or not.
   */
  private void helpEnter(Station station, Date time, String vehicle, Trip previousTrip) {
        if (balance <= 0){
            System.out.println("CardController " + id + " balance is not enough at " + time);
        }
        else{
            if (previousTrip.getExit() == null){
                if (vehicle.equals("bus")){
                System.out.println("Please tap when you get off the bus next time.");
                }else {
                System.out.println("Last trip was incomplete, 6 dollars deducted as punishment.");
                this.balance -= 6.0;
                }
            }
            Trip trip = new Trip(station, time, vehicle);
            if(myTrip.size() >= 3){
                this.myTrip.remove(0);
            }
            this.myTrip.add(trip);
            if (!(previousTrip.getExit() == null)){
                trip.updateContinuity(previousTrip);
            }
            if (!trip.getTransportation()){
                FareCalculator fareCalculator = new BusFareCalculator();
                double fare = fareCalculator.calculateFare(trip);
                deductFare(fare, "enters");
                adminUser.updateTotalFare(fare);
            }
        }
    }

  /** This is a helper method for the case where the user ends a trip.
   *  @param station station the user exits.
   * @param time time user exit the station.
   * @param trip the Trip that is about to end.
   */
  private void helpExit(
      Station station, Date time, Trip trip) {
        if (!(trip.getExit() == null)){
            System.out.println("Exit without enter, 6 dollars deducted from your balance.");
            this.balance -= 6.0;
        }else{
            trip.setExit(station, time);
            trip.updateContinuity(trip);
            if (trip.getTransportation()){
                FareCalculator fareCalculator = new SubwayFareCalculator(adminUser);
                double fare = fareCalculator.calculateFare(trip);
                deductFare(fare, "exits");
                adminUser.updateTotalFare(fare);
            }
            else{
                MinDistance busMinDistance = new BusMinDistance();
                int numStations = busMinDistance.minDistance(trip.getEntrance(), trip.getExit());
                adminUser.updateTotalStation(numStations);
            }
            user.addTime(trip.tripTime());
        }
    }


    /** A getter to get the balance of the card. */
    public double getBalance(){
        return this.balance;
    }

    /** A getter to get the suspend status of the card. */
    public boolean getSuspended(){
        return this.isSuspended;
    }
}

