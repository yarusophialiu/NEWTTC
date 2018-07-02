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

    void recordTrip(String vehicle, String enterOrExit, Time time, String stationName){
        if(enterOrExit.equals("enter")){
            Trip trip = new Trip(stationName, time, vehicle);
            myTrip.add(trip);
        }
        else{
            myTrip.get(myTrip.size() - 1).setExit(stationName, time);
        }
    }
}
