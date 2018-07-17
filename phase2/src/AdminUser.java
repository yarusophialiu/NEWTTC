/**
 * class AdminUser is for Administrator to store important informations like the total profit per
 * day and total stations reached by cardholders
 */
class AdminUser {

  /** userName of the AdminUser and is defaulted as "Admin" */
  private String userName = "Admin";
  /** password for AdminUser (Not used for now since the login phase is for phase 2) */
  private String password;
  /** a double storing totalFare collected from all card in one day */
  static double totalFare;
  /** an int storing total stations reached by all cards from all users. */
  static int totalStations;

  /** initialize a new AdminUser */
  AdminUser(String userName, String password) {
    this.userName = userName;
    this.password = password;
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
