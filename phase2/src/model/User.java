package model;
import java.io.Serializable;
import java.util.HashMap;

/**
 * Class User is to store information about the User and all sort of actions a user may take when
 * using the card.
 */
public class User implements Serializable {
    /** store the name of the user. */
    private String userName;
    /** stores the email address of the user. */
    private String emailAddress;

    /** store the password for the user when login into the system(for phase 2) */
    private String password;

    private static HashMap<String, User> users = new HashMap<>();

    private Long timeSpendOnTransit = (long)0;


    /** initialize a new User instance. */
    public User(String userName, String emailAddress, String password) {
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.password = password;
        users.put(emailAddress, this);
    }

    /** change the users user name.
     * @param newName the name that this user is about to change to.*/
    public void changeName(String newName) {
        this.userName = newName;
    }

    /** return the user name of the user. */
    public String getUserName() {
        return userName;
    }

    /** return the email address of the user */
    public String getEmailAddress() {
        return emailAddress;
    }

  /**  @return return a HashMap of key: email and value: user.*/
  public static HashMap<String, User> getUsers() {
        return users;
    }

  /** This method is used when a user trying to log into the system to compare if the given password is the same as the
   * one stored.
   * @param verifyPassword the password from user input.
   * @return return whether the password is the right one.
   */
  public boolean correctPassword(String verifyPassword) {
        return password.equals(verifyPassword);
    }

  /** This method is used to reset the user password.
   *  @param password the new password that is about to change to.*/
  public void setPassword(String password) {
    this.password = password; }

  /** this is called whenever the transit system is opened to load all users from the .ser file.
   *  @param users this parameter is read from the .ser file.*/
  public static void setUsers(HashMap<String, User> users) {
        User.users = users;
    }

  /**  this method is used to increment total time user spend on transit.
   * @param time time about to add to the total time spend on transit.*/
  void addTime(long time) {
        this.timeSpendOnTransit += time;
    }

  /** A getter that get the total time spend on transit system.
   *  @return return a long representation of total time spend on transit system.*/
  public Long getTimeSpendOnTransit() {
        return timeSpendOnTransit;
    }
}
