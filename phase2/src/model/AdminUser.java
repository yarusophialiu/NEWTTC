package model;

/**
 * class AdminUser is for Administrator to store important informations like the total profit per
 * day and total stations reached by cardholders
 */
class AdminUser extends User{

    /** a double storing totalFare collected from all card in one day */
    private double totalFare = 0;
    /** an int storing total stations reached by all cards from all users. */
    private int totalStations = 0;

    /**
     * initialize a new User instance.
     *
     * @param userName
     * @param emailAddress
     * @param password
     */
    AdminUser(String userName, String emailAddress, String password) {
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
    void report() {
        System.out.println(
                totalFare + " dollars were collected " + "from " + totalStations + " stations today.");
    }

    /** used to clear data for each day to start recording information for a new day. */
    void clearData() {
        totalFare = 0;
        totalStations = 0;
    }
}
