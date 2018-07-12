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
  private int month = 0;
  private double monthlyFare;
  private int totalMonth = 1;
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

  void updateAverageMonthlyFare(String month, double fare){
    int intMonth = convertMonth(month);
    if (this.month == 0){
      this.month = intMonth;
      this.monthlyFare += fare;
      this.averageMonthlyFare = fare;
    }
    else if (this.month == intMonth){
      this.monthlyFare += fare;
      this.averageMonthlyFare = (this.averageMonthlyFare * totalMonth + fare)/totalMonth;
    }
    else{
      int difference = intMonth - this.month;
      this.averageMonthlyFare = (this.averageMonthlyFare*this.totalMonth + fare)/(this.totalMonth + difference);
      this.totalMonth += difference;
      this.monthlyFare = fare;
      this.month = intMonth;
    }
  }

  double getAverageMonthlyFare(){

    return this.averageMonthlyFare;
  }

  int getTotalMonth(){
    return totalMonth;
  }

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
