package model;

public class BusFareCalculator implements FareCalculator {
    @Override
    public double calculateFare(Trip trip) {
        double currentFare = trip.getCurrentFare();
        if (trip.getIsContinuous()){
            if (currentFare + 2 >= 6){
                double fare = 6 - currentFare;
                trip.setCurrentFare(6);
                return fare;
            }
            trip.setCurrentFare(currentFare + 2);
            return 2;
        }
        trip.setCurrentFare(2);
        return 2;
    }
}
