package model;

/**
 * class AdminUser is for Administrator to store important informations like the total profit per
 * day and total stations reached by cardholders
 */
public class AdminUser extends User{
    /** a double storing totalFare collected from all card in one day */
    private double totalFare = 0;
    /** an int storing total stations reached by all cards from all users. */
    private int totalStations = 0;

    private double yesterdayFare = 0;

    private int yesterdayStations = 0;

    /**
     * initialize a new User instance.
     *
     * @param userName user name of admin user
     * @param emailAddress email address of admin user
     * @param password password of admin user
     */
    public AdminUser(String userName, String emailAddress, String password) {
        super(userName, emailAddress, password);
    }

    /**
     * Update total fare.
     *
     * @param fare the newly collected fare that need to be added to total fare.
     */
    void updateTotalFare(double fare){
        this.totalFare += fare;
    }

    /**
     * Update total stations.
     *
     * @param numStations the newly reached fare that need to be added to total stations.
     */
    void updateTotalStation(int numStations){
        this.totalStations += numStations;
    }

    /** used to print a report for adminUser about the profit and the totalStation reached today. */
    public String report() {
        return Double.toString(totalFare) + " dollars were collected " + "from " + Integer.toString(totalStations) + " stations today.";
    }

    /** used to print a report for adminUser about the profit and the totalStation reached yesterday. */
    public String yesterdayReport() {
        return Double.toString(yesterdayFare) + " dollars were collected " + "from " + Integer.toString(yesterdayStations) + " stations yesterday.";
    }

    /**
     * used to record yesterday's total fare and reached stations and clear data for each day to start recording information for a new day.
     */
    void clearData() {
        yesterdayFare = totalFare;
        yesterdayStations = totalStations;
        totalFare = 0;
        totalStations = 0;
    }
}
