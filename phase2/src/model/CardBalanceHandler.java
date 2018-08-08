package model;

import controller.LogWriter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.logging.*;

/**
 * This class is for handler events about making changes to the balance of the card.
 */
public class CardBalanceHandler implements Serializable {

    /** The card's balance. */
    double balance;

    /** An int to represent card number. */
    int id;

    /** An array list to store the 3 most recent trips. */
    ArrayList<Trip> myTrip = new ArrayList<>();

    /** The card's owner. */
    RegularUser user;

    /** initialize a logWriter instance to help write log files when balance changed. */
    LogWriter logWriter = new LogWriter();

    /** All the card shares the same adminUser instance since adminUser needs to collect all the fare information.. */
    static AdminUser adminUser;

  /** Constructor of the class where it records balance information from card.
   * @param balance  the balance of the card.*/
  public CardBalanceHandler(double balance) {
        this.balance = balance;
    }


    /**
     * Add money to card's balance. Exception will be threw if the adding money is not $10 or $20 or
     * $50.
     * @param i  balance that's going to be added to the card balance.*/
    public void increaseBalance(int i) throws Exception {
        if (i == 10 || i == 20 || i == 50) {
            this.balance += i;
            System.out.println(i + " dollars has been added to card " + id + " New balance: $" + balance);
            logWriter.helpLog(Level.INFO, i + " dollars has been added to card " + id + " New balance: $" + balance);
        } else {
            logWriter.helpLog(Level.SEVERE, "You can only add $10, $20 or $50");
            throw new Exception("You can only add $10, $20 or $50");
        }
    }


    /** Method used to check if the remaining balance is enough to take the trip.
     * @param enterOrExit A string that indicate whether the user enter or exit the station.
     * @param fare a double that tells the balance how much to deduct.*/
    void deductFare(double fare, String enterOrExit){
        Trip trip = this.myTrip.get(this.myTrip.size() - 1);
        if (balance <= 0){
            System.out.println(
                    "CardController " + id + " balance is not enough at " + trip.getEnterTime());
            logWriter.helpLog(Level.WARNING, "Card " + id + " balance is not enough at " + trip.getEnterTime());
        }
        else if(enterOrExit.equals("enters")){
            balance -= fare;
            System.out.println("CardController " + id + " new balance: $" + balance + " at " + trip.getEnterTime());
            helpUpdateStats(trip, fare);
            logWriter.helpLog(Level.INFO, "Card " + id + " new balance: $" + balance + " at " + trip.getEnterTime());
        }
        else if (enterOrExit.equals("exits")){
            balance -= fare;
            System.out.println(
                    "CardController " + id + " new balance: $" + balance + " at " + trip.getExitTime());
            helpUpdateStats(trip, fare);
            logWriter.helpLog(Level.INFO, "Card " + id + " new balance: $" + balance + " at " + trip.getExitTime());
        }
    }


    /** Returning a string representation of the 3 recent trips. */
    public String recentTripString() {
        StringBuilder output = new StringBuilder();
        int i = 1;
        for (Trip trip : myTrip) {
            boolean transportation = trip.getTransportation();
            String vehicle;
            if (transportation){
                vehicle = "subway";
            }else{
                vehicle = "bus";
            }
            String tripInfo = "Trip " + i + ": begin at " + trip.getEntrance().getName()
                            + " " + trip.getEnterTime() + " end at " + trip.getExit().getName()
                            + " using " + vehicle + " " + trip.getExitTime() + " " + "\n";
            output.append(tripInfo);
            i++;
        }
        return output.toString();
    }

    /** A setter for the static variable AdminUser.
     * @param newAdminUser  AdminUser that all card and user shares.*/
    public static void setAdminUser(AdminUser newAdminUser){
        adminUser = newAdminUser;
    }

    /** A getter for the user that holds the card. */
    public User getUser(){
        return user;
    }

    /** Setter for the card holder. */
    void setUser(RegularUser user) {
        this.user = user;
    }

    /** A helper method that help to update the monthly and yearly data for admin user to display information.
     * @param fare the amount of money used in this trip.
     * @param trip the trip that the card is currently in.*/
    private void helpUpdateStats(Trip trip, double fare){
        LocalDate localDate = trip.getEnterTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        user.updateAverageMonthlyFare(trip.getEnterTime(), fare);
        adminUser.getMonthlyStats().updateMonthlyStats(localDate.getMonthValue(), fare);
        adminUser.getYearlyStats().updateYearlyStats(localDate.getYear() - user.getFirstYear() + 1, fare);
        // +1 here because if years are the same, it should be the 1st first not the 0 year.
    }

}
