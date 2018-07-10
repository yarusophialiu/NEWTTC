import java.util.Date;

public class Trip {
    private Station entrance;
    private Station exit;
    private Date enterTime;
    private Date exitTime;
    private String transportation;    //vehicles used in this trip.
    private boolean isContinuous = false;
    private double currentFare;
    private Long continuousTime = (long)0;

    Trip(Station entrance, Date enterTime, String vehicle){
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

    void setExit(Station exit, Date exitTime){
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

    Date getEnterTime(){
        return enterTime;
    }

    Date getExitTime(){
        return exitTime;
    }
}
