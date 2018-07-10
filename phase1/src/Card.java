import java.util.ArrayList;
import java.util.Date;

public class Card {
    private boolean isSuspended = false;
    private int id;
    private User user;
    private double balance;
    private static int idIncrementer = 1000;
    private ArrayList<Trip> myTrip = new ArrayList<>();


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

    private void deductFare(String vehicle) {
        Trip currentTrip = myTrip.get(myTrip.size() - 1);
        if(vehicle.equals("bus")){
            if (currentTrip.getCurrentFare() + 2 <= 6 & currentTrip.getIsContinuous()) {
                if (balance >= 0) {
                    this.balance -= 2;
                    Trip.totalFare += 2;
                    currentTrip.setCurrentFare(currentTrip.getCurrentFare() + 2.0);
                }
            }
            else if (currentTrip.getCurrentFare() + 2 > 6 & currentTrip.getIsContinuous()
                    & currentTrip.getContinuousTime() <= 7200000){
                double diff = 6 - currentTrip.getCurrentFare();
                if(balance >= 0){
                    this.balance -= diff;
                    Trip.totalFare += diff;
                    currentTrip.setCurrentFare(6.0);
                }
                else{
                    System.out.println("Your balance is not enough.");
                }
            }
            else if (currentTrip.getCurrentFare() + 2 > 6 & currentTrip.getIsContinuous() & currentTrip.getContinuousTime() > 7200000){
                if (balance >= 0){
                    this.balance -= 2;
                    Trip.totalFare += 2;
                    currentTrip.setCurrentFare(2.0);
                }
                else {
                    System.out.println("Your balance is not enough.");
                }
            }
            else if (!currentTrip.getIsContinuous()){
                if (balance >= 0){
                    this.balance -=2;
                    Trip.totalFare += 2;
                    currentTrip.setCurrentFare(2.0);
                    currentTrip.setDiscontinuous();
                }
                else {
                    System.out.println("Your balance is not enough.");
                }
            }
        }

        if (vehicle.equals("subway")){
            Trip trip = myTrip.get(myTrip.size() - 1);
            double fare = StationManager.minDistance(trip.getEntrance(), trip.getExit()) * 0.5;
            if (currentTrip.getCurrentFare() + fare <= 6 & currentTrip.getIsContinuous()) {
                this.balance -= fare;
                Trip.totalFare += fare;
                currentTrip.setCurrentFare(currentTrip.getCurrentFare() + fare);
            }
            else if (currentTrip.getCurrentFare() + fare > 6 & currentTrip.getIsContinuous()
                    & (currentTrip.getContinuousTime() - currentTrip.tripTime()) <= 7200000){
                double diff = 6 - currentTrip.getCurrentFare();
                this.balance -= diff;
                Trip.totalFare += diff;
                currentTrip.setCurrentFare(6.0);
            }
            else if (currentTrip.getCurrentFare() + fare > 6 & currentTrip.getIsContinuous() &
                    (currentTrip.getContinuousTime() - currentTrip.tripTime()) > 7200000){
                this.balance -= fare;
                Trip.totalFare += fare;
                currentTrip.setCurrentFare(fare);
            }
            else if (!currentTrip.getIsContinuous()){
                this.balance -= fare;
                Trip.totalFare += fare;
                currentTrip.setCurrentFare(fare);
                currentTrip.setDiscontinuous();
            }
        }
    }


    void recordTrip(String vehicle, String enterOrExit, Date time, Station station){
        if (!isSuspended){
            if (myTrip.size() >= 1){
                Trip previousTrip = myTrip.get(myTrip.size() - 1);
                if(enterOrExit.equals("enters")){
                    Trip trip = new Trip(station, time, vehicle);
                    if (!trip.getEntrance().equals(previousTrip.getExit())){
                        trip.setContinuousTime((long) 0);
                    }
                    else if (trip.getEntrance().equals(previousTrip.getExit())){
                        trip.setContinuous();
                        trip.setContinuousTime(previousTrip.getContinuousTime() + (trip.getEnterTime().getTime() - previousTrip.getExitTime().getTime()));
                    }
                    if (myTrip.size() >= 3){
                        myTrip.remove(myTrip.get(0));
                        myTrip.add(trip);
                    }
                    else{
                        myTrip.add(trip);
                    }
                    if (vehicle.equals("bus")){
                        deductFare("bus");
                    }
                    else if (vehicle.equals("subway") && balance <= 0){
                        System.out.println("Your balance is not enough.");
                    }
                }
                else{
                    Trip exitTrip = myTrip.get(myTrip.size() - 1);
                    exitTrip.setExit(station, time);
                    exitTrip.setContinuousTime(exitTrip.getContinuousTime() + exitTrip.tripTime());
                    if (vehicle.equals("subway")){
                        deductFare("subway");
                    }
                }
            }
            else{
                if (enterOrExit.equals("enters")){
                    Trip trip = new Trip(station, time, vehicle);
                    trip.setContinuous();
                    trip.setContinuousTime((long) 0); // addTrip
                    myTrip.add(trip);
                    if (vehicle.equals("bus")){
                        deductFare("bus");
                    }
                    else if (vehicle.equals("subway") && balance <= 0){
                        System.out.println("Your balance is not enough.");
                    }
                }
            }
        }
        else {
            System.out.println("This card is suspended.");
        }
    }

    double getBalance(){
        return balance;
    }
}
