package model;

import controller.LogWriter;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.*;

public class CardBalanceHandler implements Serializable {

    /** The card's balance. */
    double balance;

    /** An int to represent card number. */
    int id;


    /** An array list to store the 3 most recent trips. */
    ArrayList<Trip> myTrip = new ArrayList<>();

    /** The card's owner. */
    RegularUser user;

    LogWriter logWriter = new LogWriter();

    static AdminUser adminUser;

    /**
     * Add money to card's balance. Exception will be threw if the adding money is not $10 or $20 or
     * $50.
     */
    public CardBalanceHandler(double balance){
        this.balance = balance;
    }

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
            logWriter.helpLog(Level.INFO, "Card " + id + " new balance: $" + balance + " at " + trip.getEnterTime());
        }
        else if (enterOrExit.equals("exits")){
            balance -= fare;
            System.out.println(
                    "CardController " + id + " new balance: $" + balance + " at " + trip.getExitTime());
            logWriter.helpLog(Level.INFO, "Card " + id + " new balance: $" + balance + " at " + trip.getExitTime());
        }
    }


    public String recentTripString() {
        StringBuilder output = new StringBuilder();
        int i = 1;
        for (Trip trip : myTrip) {
            String tripInfo = "Trip " + i + ": begin at " + trip.getEntrance().getName()
                            + " " + trip.getEnterTime() + " end at " + trip.getExit().getName()
                            + " using " + trip.getTransportation() + " " + trip.getExitTime() + " " + "\n";
            output.append(tripInfo);
            i++;
        }
        return output.toString();
    }

    public static void setAdminUser(AdminUser newAdminUser){
        adminUser = newAdminUser;
    }

    public User getUser(){
        return user;
    }

    /** Setter for CardController.user. */
    void setUser(RegularUser user) {
        this.user = user;
    }

}
