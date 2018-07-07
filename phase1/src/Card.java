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
        if (vehicle.equals("Bus")) {
            this.balance -= 2;
            Trip.totalFare += 2;
        }

        if (vehicle.equals("Subway")) {
            Trip trip = myTrip.get(myTrip.size()-1);
            ArrayList<String> stationList = CardManager.stationList;
            int numOfStations = stationList.indexOf(trip.getExit()) - stationList.indexOf(trip.getEntrance());
            double fare = numOfStations * 0.5;
            this.balance -= fare;
            Trip.totalFare += fare;
        }

    }

    void recordTrip(String vehicle, String enterOrExit, Time time, String stationName){
        if(enterOrExit.equals("enter")){
            Trip trip = new Trip(stationName, time, vehicle);
            myTrip.add(trip);
            if (vehicle.equals("Bus")){
                deductFare("Bus");
            }
        }
        else{
            myTrip.get(myTrip.size() - 1).setExit(stationName, time);
            if (vehicle.equals("Subway")){
                deductFare("Subway");
            }
        }
    }

    void regulateMyTrip() {
        if (myTrip.size() > 3) {
            ArrayList<Trip> tripsLeft = new ArrayList<>();
            for (int i = 3; i > 0; i--) {
                Trip trip = myTrip.get(myTrip.size() - i);
                tripsLeft.add(trip);
            }
            myTrip = tripsLeft;
        }
    }
}
