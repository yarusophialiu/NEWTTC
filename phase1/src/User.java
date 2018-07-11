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
}
