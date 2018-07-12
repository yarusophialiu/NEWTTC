import java.util.ArrayList;

/**
 * Class User is to store information about the User and all sort of actions a user may take when
 * using the card.
 */
class User {
  /** store the name of the user. */
  private String userName;
  /** stores the email address of the user. */
  private String emailAddress;
  /** store all the card that user have */
  private ArrayList<Card> myCards;
  /** store the password for the user when login into the system(for phase 2) */
  private String password;
  /** the month user currently in */
  private int month = 0;
  /** store the total amount of month this user pass since their first time using any of their card */
  private int totalMonth = 1;
  /** stores average monthly cost for the user for all cards.*/
  private double averageMonthlyFare = 0.0;

  /** initialize a new User instance. */
  User(String userName, String emailAddress, String password) {
    this.userName = userName;
    this.emailAddress = emailAddress;
    myCards = new ArrayList<Card>();
    this.password = password;
  }

  /** user buy a new card */
  void buyCard() {
    Card card = new Card();
    addCard(card);
    System.out.println(userName + "bought" + " " + "1 card.");
  }

  /** used to check if the card has already existed in the collection of card user has. */
  private void addCard(Card card) {
    if (!myCards.contains(card)) {
      this.myCards.add(card);
      card.setUser(this);
    } else {
      System.out.println("This card was already added.");
    }
  }

  /** return an ArrayList of cards that the user has. */
  ArrayList<Card> getMyCards() {
    return myCards;
  }

  /** change the users user name. */
  void changeName(String newName) {
    this.userName = newName;
  }

  /** return the user name of the user. */
  String getUserName() {
    return userName;
  }

  /** return the email address of the user */
  String getEmailAddress() {
    return emailAddress;
  }

  /** this method will update the average monthly fare for the user depending on the month(if its a new month) */
  void updateAverageMonthlyFare(String month, double fare){
    int intMonth = convertMonth(month);
    if (this.month == 0){
      this.month = intMonth;
      this.averageMonthlyFare = fare;
    }
    else if (this.month == intMonth){
      this.averageMonthlyFare = (this.averageMonthlyFare * totalMonth + fare)/totalMonth;
    }
    else{
      int difference = intMonth - this.month;
      this.averageMonthlyFare = (this.averageMonthlyFare*this.totalMonth + fare)/(this.totalMonth + difference);
      this.totalMonth += difference;
      this.month = intMonth;
    }
  }
  /** A getter for the averageMonthlyFare. */
  double getAverageMonthlyFare(){
    return this.averageMonthlyFare;
  }
  /** A getter for the totalMonth variable*/
  int getTotalMonth(){
    return totalMonth;
  }

  /** used in cases where the user didn't do anything in that month to update its average monthly cost.*/
  void incrementTotalMonth(){
    averageMonthlyFare = (averageMonthlyFare * totalMonth)/(totalMonth + 1);
    totalMonth++;
  }

  /** this method will turn the string representation of the month into int for updateAverageMonthlyFare method.*/
  private int convertMonth(String month){
    switch (month) {
      case "Jan":
        return 1;
      case "Feb":
        return 2;
      case "Mar":
        return 3;
      case "Apr":
        return 4;
      case "May":
        return 5;
      case "Jun":
        return 6;
      case "Jul":
        return 7;
      case "Aug":
        return 8;
      case "Sep":
        return 9;
      case "Oct":
        return 10;
      case "Nov":
        return 11;
      default:
        return 12;
    }
  }
}
