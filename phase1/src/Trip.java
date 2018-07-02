import java.sql.Time;

public class Trip {
    private String entrance;
    private String exit;
    private Time enterTime;
    private Time exitTime;
    private String transportation;    //vehicles used in this trip.

    Trip(String entrance, Time enterTime, String vehile){
        this.enterTime = enterTime;
        this.entrance = entrance;
        this.transportation = vehile;
    }
}
