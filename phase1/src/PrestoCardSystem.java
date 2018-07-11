import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Class PrestoCardSystem mainly accept orders from user (now its events.txt) and execute them
 * though other classes.
 */
class PrestoCardSystem {

  /**
   * users is a collection of User instance and is for easier access to the card each of them own.
   */
  private ArrayList<User> users = new ArrayList<>();
  /**
   * admin users are initiated automatically here since there can only be one boss for this system
   * to check the information.
   */
  private AdminUser admin = new AdminUser("Boss", "1234");

  /**
   * method doEvent accept an ArrayList of data read from events.txt (and in phase two, it will
   * accept from user) and coordinate with other class if necessary
   */
  void doEvent(ArrayList<String> data) throws Exception {
    if (data.contains("bought")) { // events about buy new card.
      for (User user : users) {
        if (user.getUserName().equals(data.get(0))) {
          for (int i = 0; i < Integer.parseInt(data.get(2)); i++) {
            user.buyCard();
          }
        }
      }
    } else if (data.contains("enters")
        || data.contains("exits")) { // events on enter and exit a station.
      for (User user : users) {
        for (Card card : user.getMyCards()) {
          if (card.getId() == Integer.parseInt(data.get(1))) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date time = df.parse(data.get(7) + " " + data.get(8));
            Station station = StationManager.newStation(data.get(3), data.get(4));
            card.recordTrip(data.get(4), data.get(2), time, station);
          }
        }
      }
    } else if (data.contains("suspended")
        || data.contains("retrieved")) { // events on suspend and retrieved card.
      for (User user : users) {
        for (Card card : user.getMyCards()) {
          if (card.getId() == Integer.parseInt(data.get(1))) {
            card.reverseSuspended();
          }
        }
      }
    } else if (data.contains("added")) { // events on add balance for the card.
      for (User user : users) {
        for (Card card : user.getMyCards()) {
          if (card.getId() == Integer.parseInt(data.get(6))) {
            card.increaseBalance(Integer.parseInt(data.get(0)));
          }
        }
      }
    } else if (data.contains("recent") && data.contains("trip")) {
      for (User user : users) {
        for (Card card : user.getMyCards()) {
          if (card.getId() == Integer.parseInt(data.get(1))) {
            System.out.println(card.recentTripString());
          }
        }
      }
    } else if (data.contains(
        "ended,")) { // events on when the day ended, clear data from the previous day and print
      // report.
      this.admin.report();
      this.admin.clearData();
    } else { // events on create a new user.
      users.add(new User(data.get(0), data.get(1), data.get(2)));
    }
  }
}
