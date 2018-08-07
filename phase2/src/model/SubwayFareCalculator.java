package model;

/** Class SubwayFareCalculator is an implementation of strategy FareCalculator. The main functionality of the class
 * is to calculate fares for subway stations.*/
public class SubwayFareCalculator implements FareCalculator {
    private AdminUser adminUser;

  /** Constructor for class SubwayFareCalculator.
   * @param adminUser set adminUser for this class to update daily revenue for adminUser.*/
  SubwayFareCalculator(AdminUser adminUser) {
        this.adminUser = adminUser;
    }

  /** this method takes in a trip and help to calculate the fare that's needed to complete the trip.
   * @param trip trip that the user is currently in.
   * @return return a double representation of fare needed to pay.
   */
  @Override
  public double calculateFare(Trip trip) {
        StationFactory stationFactory = new StationFactory();
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
            trip.setCurrentFare(currentFare + fare);
            return fare;
        }
        trip.setCurrentFare(fare);
        return fare;
    }
}
