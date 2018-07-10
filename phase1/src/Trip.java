import java.sql.Time;

public class Trip {
    private Station entrance;
    private Station exit;
    private Time enterTime;
    private Time exitTime;
    private String transportation;    //vehicles used in this trip.
    private boolean isContinuous = false;
    private double currentFare;
    private Long continuousTime;
    static double totalFare;
    static int totalStations;

    Trip(Station entrance, Time enterTime, String vehicle){
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

    void setExit(Station exit, Time exitTime){
        this.exit = exit;
        this.exitTime = exitTime;
    }

    void setContinuous(){
        this.isContinuous = true;
    }

    void setDiscontinuous(){
        this.isContinuous = false;
    }

    void setCurrentFare(double fare){
        this.currentFare = fare;
    }

    void setContinuousTime(Long time){
        this.continuousTime = time;
    }

    double getCurrentFare(){
        return this.currentFare;
    }

    Long getContinuousTime(){
        return continuousTime;
    }

    boolean getIsContinuous(){
        return this.isContinuous;
    }

    Long tripTime() {
        return exitTime.getTime() - enterTime.getTime();
    }

    Time getEnterTime(){
        return enterTime;
    }

    Time getExitTime(){
        return exitTime;
    }

    void clearData(){
        totalFare = 0.0;
        totalStations = 0;
    }
}
