package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.*;

class CardHandler {

    private static final Logger logger = Logger.getLogger(CardHandler.class.getName());

    /** An array list to store the 3 most recent trips. */
    ArrayList<Trip> myTrip = new ArrayList<>();

    /** The card's owner. */
    RegularUser user;

    static AdminUser adminUser;

    CardHandler(){
        logger.setLevel(Level.ALL);
        try {
            FileHandler fileHandler = new FileHandler("phase2/CardAndUserLog.log", true);
            logger.addHandler(fileHandler);
            SimpleFormatter simpleFormatter= new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            logger.setUseParentHandlers(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void helpLog(Level level, String message){
        logger.log(level, message);
    }

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
                            + " "
                            + "\n";
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
