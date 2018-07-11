import java.util.ArrayList;
import java.util.Date;

/**
 * Class Card is responsible for storing information about the card balance and its id and the three
 * recent trip related to the card. And Card is also responsible for calculate how much to deduct
 * from the card balance when enter a bus station or exit a subway station.
 */
class Card {

  /** A boolean to indicate whether the card is suspended or not. */
  private boolean isSuspended = false;
  /** An int to represent card number. */
  private int id;
  /** The card's owner. */
  private User user;
  /** The card's balance. */
  private double balance;
  /**
   * Card number starts from 1000 and will be increased by 1 each time when a new card is generated.
   */
  private static int idIncrementer = 1000;
  /** An array list to store the 3 most recent trips. */
  private ArrayList<Trip> myTrip = new ArrayList<>();

  /** Initialize a new card. */
  Card() {
    this.id = idIncrementer;
    idIncrementer++;
    this.balance = 19;
  }

  /** Adjust the boolean isSuspended when the card is suspended and the card is retrieved. */
  void reverseSuspended() {
    this.isSuspended = !this.isSuspended;
    if (isSuspended) {
      System.out.println("Card " + id + " has been suspended.");
    } else {
      System.out.println("Card " + id + " has been retrieved.");
    }
  }

  /** Getter for Card.id. */
  int getId() {
    return this.id;
  }

  /** Setter for Card.user. */
  void setUser(User user) {
    this.user = user;
  }

  /**
   * Add money to card's balance. Exception will be threw if the adding money is not $10 or $20 or
   * $50.
   */
  void increaseBalance(int i) throws Exception {
    if (i == 10 || i == 20 || i == 50) {
      this.balance += i;
      System.out.println(i + " dollars has been added to card " + id + " New balance: $" + balance);
    } else {
      throw new Exception("You can only add $10, $20 or $50");
    }
  }

  /**
   * Calculate the amount to deduct from card balance every time the card enters a bus station or
   * exits a subway station. The algorithm also includes the $6 cap in two-hour continuous trip.
   */
  private void deductFare(String vehicle) {
    Trip currentTrip = myTrip.get(myTrip.size() - 1);
    // For the bus case.
    if (vehicle.equals("bus")) {
      if (currentTrip.getCurrentFare() + 2 <= 6 & currentTrip.getIsContinuous()) {
        // The trip is in continuous trips but the cumulative fare in the whole
        // chain of continuous trips does not reach $6.
        if (balance >= 0) {
          // Check if the card has money in it. If not, reject the card when it is
          // tapped.
          this.balance -= 2;
          AdminUser.totalFare += 2;
          currentTrip.setCurrentFare(currentTrip.getCurrentFare() + 2.0);
          System.out.println(
              "Card "
                  + id
                  + " new balance: $"
                  + getBalance()
                  + " at "
                  + currentTrip.getEnterTime());
        } else {
          System.out.println(
              "Card " + id + " balance is not enough at " + currentTrip.getEnterTime());
        }
      } else if (currentTrip.getCurrentFare() + 2 > 6
          & currentTrip.getIsContinuous()
          & currentTrip.getContinuousTime() <= 7200000) {
        // The trip is in continuous trips and the cumulative fare in the whole
        // chain of continuous trips exceeds $6 and the whole continuous period
        // is within 2 hours.
        double diff = 6 - currentTrip.getCurrentFare();
        if (balance >= 0) {
          this.balance -= diff;
          AdminUser.totalFare += diff;
          currentTrip.setCurrentFare(6.0);
          System.out.println(
              "Card "
                  + id
                  + " new balance: $"
                  + getBalance()
                  + " at "
                  + currentTrip.getEnterTime());
        } else {
          System.out.println(
              "Card " + id + " balance is not enough at " + currentTrip.getEnterTime());
        }
      } else if (currentTrip.getCurrentFare() + 2 > 6
          & currentTrip.getIsContinuous()
          & currentTrip.getContinuousTime() > 7200000) {
        // The trip is in continuous trips and the cumulative fare in the whole
        // chain of continuous trips exceeds $6 but the whole continuous period
        // exceeds 2 hours.
        if (balance >= 0) {
          this.balance -= 2;
          AdminUser.totalFare += 2;
          currentTrip.setCurrentFare(2.0);
          System.out.println(
              "Card "
                  + id
                  + " new balance: $"
                  + getBalance()
                  + " at "
                  + currentTrip.getEnterTime());
        } else {
          System.out.println(
              "Card " + id + " balance is not enough at " + currentTrip.getEnterTime());
        }
      } else if (!currentTrip.getIsContinuous()) {
        // The trip is not in a continuous trip chain.
        if (balance >= 0) {
          this.balance -= 2;
          AdminUser.totalFare += 2;
          currentTrip.setCurrentFare(2.0);
          currentTrip.setDiscontinuous();
          System.out.println(
              "Card "
                  + id
                  + " new balance: $"
                  + getBalance()
                  + " at "
                  + currentTrip.getEnterTime());
        } else {
          System.out.println(
              "Card " + id + " balance is not enough at " + currentTrip.getEnterTime());
        }
      }
    }

    // For the subway case.
    else if (vehicle.equals("subway")) {
      Trip trip = myTrip.get(myTrip.size() - 1);
      int distance = StationManager.minDistance(trip.getEntrance(), trip.getExit());
      double fare = distance * 0.5;
      AdminUser.totalStations += distance;
      if (currentTrip.getCurrentFare() + fare <= 6 & currentTrip.getIsContinuous()) {
        // The trip is in continuous trips but the cumulative fare in the whole
        // chain of continuous trips does not reach $6.
        this.balance -= fare;
        AdminUser.totalFare += fare;
        currentTrip.setCurrentFare(currentTrip.getCurrentFare() + fare);
        System.out.println(
            "Card " + id + " new balance: $" + getBalance() + " at " + currentTrip.getExitTime());
      } else if (currentTrip.getCurrentFare() + fare > 6
          & currentTrip.getIsContinuous()
          & (currentTrip.getContinuousTime() - currentTrip.tripTime()) <= 7200000) {
        // The trip is in continuous trips and the cumulative fare in the whole
        // chain of continuous trips exceeds $6 and the whole continuous period
        // is within 2 hours.
        double diff = 6 - currentTrip.getCurrentFare();
        this.balance -= diff;
        AdminUser.totalFare += diff;
        currentTrip.setCurrentFare(6.0);
        System.out.println(
            "Card " + id + " new balance: $" + getBalance() + " at " + currentTrip.getExitTime());
      } else if (currentTrip.getCurrentFare() + fare > 6
          & currentTrip.getIsContinuous()
          & (currentTrip.getContinuousTime() - currentTrip.tripTime()) > 7200000) {
        // The trip is in continuous trips and the cumulative fare in the whole
        // chain of continuous trips exceeds $6 but the whole continuous period
        // exceeds 2 hours.
        this.balance -= fare;
        AdminUser.totalFare += fare;
        currentTrip.setCurrentFare(fare);
        System.out.println(
            "Card " + id + " new balance: $" + getBalance() + " at " + currentTrip.getExitTime());
      } else if (!currentTrip.getIsContinuous()) {
        // The trip is not in a continuous trip chain.
        this.balance -= fare;
        AdminUser.totalFare += fare;
        currentTrip.setCurrentFare(fare);
        currentTrip.setDiscontinuous();
        System.out.println(
            "Card " + id + " new balance: $" + getBalance() + " at " + currentTrip.getExitTime());
      }
    }
  }

  /**
   * Generate a new trip whenever the card enters a station. Complete trip information whenever the
   * card exits a station. Call Card.deductFare() at the proper time.
   */
  void recordTrip(String vehicle, String enterOrExit, Date time, Station station) {
    if (!isSuspended) {
      // Check if the card is suspended.
      if (myTrip.size() >= 1) {
        // If the trip is not the first trip of the card in that day, looking for
        // the previous trip to check if current trip is a continuous trip.
        Trip previousTrip = myTrip.get(myTrip.size() - 1);
        if (enterOrExit.equals("enters")) {
          Trip trip = new Trip(station, time, vehicle);
          if (!trip.getEntrance().getName().equals(previousTrip.getExit().getName())) {
            // If current trip is not continuous.
            trip.setContinuousTime((long) 0);
          } else if (trip.getEntrance().getName().equals(previousTrip.getExit().getName())) {
            // If current trip is continuous. Check the continuous time and compare
            // it with two hours.
            Long currentContinuousTime =
                previousTrip.getContinuousTime()
                    + (trip.getEnterTime().getTime() - previousTrip.getExitTime().getTime());
            if (currentContinuousTime < 7200000) {
              // If the continuous time is within 2 hours.
              trip.setContinuous();
              trip.setContinuousTime(currentContinuousTime);
              trip.setCurrentFare(previousTrip.getCurrentFare());
            } else {
              // If the continuous time exceeds 2 hours.
              trip.setDiscontinuous();
              trip.setContinuousTime((long) 0);
            }
          }
          if (myTrip.size() >= 3) {
            // remove trips in myTrip to ensure that myTrip only contains the 3
            // most recent trips.
            myTrip.remove(myTrip.get(0));
            myTrip.add(trip);
          } else {
            myTrip.add(trip);
          }
          if (vehicle.equals("bus")) {
            deductFare("bus");
          } else if (vehicle.equals("subway") && balance <= 0) {
            // Print warnings whenever the card has no money and enters a subway
            // station.
            System.out.println("Card " + id + " balance is not enough at " + time);
          }
        } else {
          Trip exitTrip = myTrip.get(myTrip.size() - 1);
          exitTrip.setExit(station, time);
          exitTrip.setContinuousTime(exitTrip.getContinuousTime() + exitTrip.tripTime());
          if (vehicle.equals("subway")) {
            deductFare("subway");
          } else {
            int distance = StationManager.minDistance(exitTrip.getEntrance(), exitTrip.getExit());
            // Record the total number of stations reached in this trip.
            AdminUser.totalStations += distance;
          }
        }
      } else {
        // If the trip is the first trip of the card in that day.
        if (enterOrExit.equals("enters")) {
          Trip trip = new Trip(station, time, vehicle);
          trip.setContinuous();
          trip.setContinuousTime((long) 0); // addTrip
          myTrip.add(trip);
          if (vehicle.equals("bus")) {
            deductFare("bus");
          } else if (vehicle.equals("subway") && balance <= 0) {
            System.out.println("Card " + id + " balance is not enough at " + time);
          }
        }
      }
    } else {
      // If the card is suspended, print warnings.
      System.out.println("You cannot enter because card " + id + " is suspended " + time);
    }
  }

  /** Getter of the card's balance. */
  double getBalance() {
    return balance;
  }

  /** return a String representation of the recent trip the user has taken. */
  String recentTripString() {
    StringBuilder output = new StringBuilder();
    int i = 1;
    for (Trip trip : myTrip) {
      String tripInfo =
          "Trip "
              + i
              + ": begin at "
              + trip.getEntrance().getName()
              + " "
              + trip.getEnterTime()
              + " end at "
              + trip.getExit().getName()
              + " using "
              + trip.getTransportation()
              + " "
              + trip.getExitTime()
              + ". "
              + "\n";
      output.append(tripInfo);
      i++;
    }
    return output.toString();
  }
}
