package model;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;

public class Card extends CardBalanceHandler implements Serializable {

    /** A boolean to indicate whether the card is suspended or not. */
    private boolean isSuspended = false;

    /**
     * CardController number starts from 1000 and will be increased by 1 each time when a new card is generated.
     */
    private static int idIncrementer = 1000;


    /** Initialize a new card. */
    public Card() {
        super(19);
        this.id = idIncrementer;
        idIncrementer++;
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




    public void updateOnTap(String enterOrExit, Station station, Date time, String vehicle, StationFactory stationFactory){
        if (isSuspended){
            System.out.println("You cannot enter because card " + id + " is suspended " + time);
        }
        else if (myTrip.size() >= 1){ //since the size of myTrip is greater than one, therefore it must be either exiting the first trip for starting a new trip.
            Trip previousTrip = this.myTrip.get(this.myTrip.size() - 1);
            if (enterOrExit.equals("enters")){
                helpEnter(station, time, vehicle, previousTrip);
            }
            else if (enterOrExit.equals("exits")){
                helpExit(station, time, vehicle, previousTrip, stationFactory);
            }
        }
        else if (enterOrExit.equals("enters")){  //this is the case where this trip is the first trip of that card.
            Trip trip = new Trip(station, time, vehicle);
            myTrip.add(trip);
            if (vehicle.equals("bus")){
                deductFare(2, "enters");
                trip.setCurrentFare(2);
                adminUser.updateTotalFare(2);
                user.updateAverageMonthlyFare(trip.getEnterTime().toString().split(" ")[1], 2);
            }
        }
        else {  //since this is the case of the first trip of the card, and its exiting, therefore must be entered the station without tapping.
            System.out.println("exit without enter, 6 dollars deducted from your balance.");
            this.balance -= 6; //allow to be negative       // haven't calculate the average monthly fare here.
            logWriter.helpLog(Level.INFO, "exit without enter, 6 dollars deducted from your balance.");
        }
    }



    private void helpEnter(Station station, Date time, String vehicle, Trip previousTrip){
        if (balance <= 0){
            System.out.println("CardController " + id + " balance is not enough at " + time);
        }
        else{
            Trip trip = new Trip(station, time, vehicle);
            if(myTrip.size() >= 3){
                this.myTrip.remove(0);
            }
            this.myTrip.add(trip);
            trip.updateContinuity(previousTrip);
            if (!trip.getTransportation()){
                FareCalculator fareCalculator = new BusFareCalculator();
                double fare = fareCalculator.calculateFare(trip);
                deductFare(fare, "enters");
                adminUser.updateTotalFare(fare);
                user.updateAverageMonthlyFare(trip.getEnterTime().toString().split(" ")[1], fare);
            }
        }
    }

    private void helpExit(Station station, Date time, String vehicle, Trip trip, StationFactory stationFactory){
        if (!(trip.getExit() == null)){
            System.out.println("exit without enter, 6 dollars deducted from your balance.");
            this.balance -= 6;
        }else{
            trip.setExit(station, time);
            trip.updateContinuity(trip);
            if (trip.getTransportation()){
                FareCalculator fareCalculator = new SubwayFareCalculator(stationFactory, adminUser);
                double fare = fareCalculator.calculateFare(trip);
                deductFare(fare, "exits");
                adminUser.updateTotalFare(fare);
                user.updateAverageMonthlyFare(trip.getEnterTime().toString().split(" ")[1], fare);
            }
            else{
                MinDistance busMinDistance = new BusMinDistance(stationFactory);
                int numStations = busMinDistance.minDistance(trip.getEntrance(), trip.getExit());
                adminUser.updateTotalStation(numStations);
            }
            user.addTime(trip.tripTime());
        }
    }

    public double getBalance(){
        return this.balance;
    }

    public boolean getSuspended(){
        return this.isSuspended;
    }
}

