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
     * @param userName
     * @param emailAddress
     * @param password
     */
   public AdminUser(String userName, String emailAddress, String password) {
        super(userName, emailAddress, password);
    }

    void updateTotalFare(double fare){
        this.totalFare += fare;
    }

    void updateTotalStation(int numStations){
        this.totalStations += numStations;
    }

    /**
     * used to print a report for adminUser about the profit and the totalStation reached each day.
     */
    public String report() {
        return Double.toString(totalFare) + " dollars were collected " + "from " + Integer.toString(totalStations) + " stations today.";
    }

    public String yesterdayReport() {
        return Double.toString(yesterdayFare) + " dollars were collected " + "from " + Integer.toString(yesterdayStations) + " stations yesterday.";
    }

    /** used to clear data for each day to start recording information for a new day. */
    void clearData() {
        yesterdayFare = totalFare;
        yesterdayStations = totalStations;
        totalFare = 0;
        totalStations = 0;
    }
}
