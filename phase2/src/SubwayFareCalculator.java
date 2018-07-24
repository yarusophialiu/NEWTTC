public class SubwayFareCalculator implements FareCalculator{
    private StationFactory stationFactory;
    private AdminUser adminUser;

    SubwayFareCalculator(StationFactory stationFactory, AdminUser adminUser){
        this.stationFactory = stationFactory;
        this.adminUser = adminUser;
    }

    @Override
    public double calculateFare(Trip trip) {
        SubwayMinDistance subwayMinDistance= new SubwayMinDistance(stationFactory);
        int minDistance = subwayMinDistance.minDistance(trip.getEntrance(), trip.getExit());
        adminUser.updateTotalStation(minDistance);
        double fare = minDistance * 0.5;
        double currentFare = trip.getCurrentFare();
        if (trip.getIsContinuous()){
            if (currentFare + fare >= 6){
                trip.setCurrentFare(6);
                return 6 - currentFare;
            }
            else{
                trip.setCurrentFare(currentFare + fare);
                return fare;
            }
        }
        else{
            trip.setCurrentFare(fare);
            return fare;
        }
    }
}
