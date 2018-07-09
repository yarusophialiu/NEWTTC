import java.sql.Time;
import java.util.ArrayList;

public class Card {
    private boolean isSuspended = false;
    private int id;
    private User user;
    private double balance;
    private static int idIncrementer = 1000;
    private ArrayList<Trip> myTrip;


    Card(){
        this.id = idIncrementer;
        idIncrementer++;
        this.balance = 19;
    }

    void reverseSuspended(){
        this.isSuspended = !this.isSuspended;
    }

    int getId(){
        return this.id;
    }

    void setUser(User user){
        this.user = user;
    }

    void increaseBalance(int i) throws Exception {
        if (i == 10 || i == 20 || i == 50) {
            this.balance += i;
        } else {
            throw new Exception("You can only add $10, $20 or $50");
        }
    }

    void deductFare(String vehicle) {
        Trip currentTrip = myTrip.get(myTrip.size() - 1);
        if (vehicle.equals("Bus") & currentTrip.getCurrentFare() + 2 <= 6 & currentTrip.getIsContinuous()) {
            this.balance -= 2;
            Trip.totalFare += 2;
            currentTrip.setCurrentFare(currentTrip.getCurrentFare() + 2.0);
        }
        if (vehicle.equals("Bus") & currentTrip.getCurrentFare() + 2 > 6 & currentTrip.getIsContinuous()
                & currentTrip.getContinuousTime() <= 7200000){
            double diff = 6 - currentTrip.getCurrentFare();
            this.balance -= diff;
            Trip.totalFare += diff;
            currentTrip.setCurrentFare(6.0);
        }
        if (vehicle.equals("Bus") & currentTrip.getCurrentFare() + 2 > 6 & currentTrip.getIsContinuous()
                & currentTrip.getContinuousTime() > 7200000){
            this.balance -= 2;
            Trip.totalFare += 2;
            currentTrip.setCurrentFare(2.0);
        }
        if (vehicle.equals("Bus") & !currentTrip.getIsContinuous()){
            this.balance -=2;
            Trip.totalFare += 2;
            currentTrip.setCurrentFare(2.0);
        }

//        if (vehicle.equals("Subway")) {
//            Trip trip = myTrip.get(myTrip.size()-1);
//            ArrayList<String> stationList = CardManager.stationList;
//            int numOfStations = stationList.indexOf(trip.getExit()) - stationList.indexOf(trip.getEntrance());
//            double fare = numOfStations * 0.5;
//            this.balance -= fare;
//            Trip.totalFare += fare;
//        }


    }

    void recordTrip(String vehicle, String enterOrExit, Time time, Station station){
        Trip trip = new Trip(station, time, vehicle);
        Trip previousTrip = myTrip.get(myTrip.size() - 1);
        if(enterOrExit.equals("enter")){
            if (trip.getEntrance() == previousTrip.getExit()){
                trip.reverseContinuous();
                trip.setContinuousTime(previousTrip.tripTime());
            }
            else if (trip.getEntrance()!= previousTrip.getExit()){
                trip.setContinuousTime((long) 0);
            }
            if (myTrip.size() >= 3){
                myTrip.remove(myTrip.get(0));
                myTrip.add(trip);
            }
            else{
                myTrip.add(trip);
            }
            if (vehicle.equals("Bus")){
                deductFare("Bus");
            }
        }
        else{
            myTrip.get(myTrip.size() - 1).setExit(station, time);
            trip.setContinuousTime(trip.getContinuousTime() + trip.tripTime());
            if (vehicle.equals("Subway")){
                deductFare("Subway");
            }
        }
    }
}
