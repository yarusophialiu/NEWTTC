import java.util.Date;

public class Trip {
    /**The start station of the trip. */
    private Station entrance;
    /**The end station of the trip. */
    private Station exit;
    /**The start time of the trip. */
    private Date enterTime;
    /**The end time of the trip. */
    private Date exitTime;
    /**vehicles used in this trip. */
    private String transportation;
    /**A boolean to indicate whether the trip is continuous trip. */
    private boolean isContinuous = false;
    /**A double to represent the cumulative fare in the chain of continuous trips. */
    private double currentFare;
    /**Represent the length of time interval of continuous trip. */
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

    Date getExitTime(){ return exitTime; }
}
