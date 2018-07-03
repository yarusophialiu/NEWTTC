import java.sql.Time;

public class Trip {
    private String entrance;
    private String exit;
    private Time enterTime;
    private Time exitTime;
    private String transportation;    //vehicles used in this trip.
    private boolean isContinuous = false;
    private double currentFare;
    private Time continuousTime;
    static int totalFare;
    static int totalStations;

    Trip(String entrance, Time enterTime, String vehile){
        this.enterTime = enterTime;
        this.entrance = entrance;
        this.transportation = vehile;
    }

    String getEntrance() {
        return entrance;
    }

    String getExit() {
        return exit;
    }

    void setExit(String exit, Time exitTime){
        this.exit = exit;
        this.exitTime = exitTime;
    }

    void reverseContinuous(){
        this.isContinuous = !this.isContinuous;
    }

    void setCurrentFare(double fare){
        this.currentFare = fare;
    }

    void setContinuousTime(Time time){
        this.continuousTime = time;
    }

    double getCurrentFare(){
        return this.currentFare;
    }

    Time getContinuousTime(){
        return continuousTime;
    }

    boolean getIsContinuous(){
        return this.isContinuous;
    }
}
