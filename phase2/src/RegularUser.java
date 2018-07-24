import java.util.ArrayList;

public class RegularUser extends User {

    /** store all the card that user have */
    private ArrayList<Card> myCards;
    /** the month user currently in */
    private int month = 0;
    /**
     * store the total amount of month this user pass since their first time using any of their card
     */
    private int totalMonth = 1;
    /** stores average monthly cost for the user for all cards. */
    private double averageMonthlyFare = 0.0;

    /**
     * initialize a new User instance.
     *
     * @param userName
     * @param emailAddress
     * @param password
     */
    RegularUser(String userName, String emailAddress, String password) {
        super(userName, emailAddress, password);
        this.myCards = new ArrayList<>();
    }

    /** user buy a new card */
    void buyCard() {
        Card card = new Card();
        addCard(card);
        System.out.println(getUserName() + "bought" + " " + "1 card.");
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
    /**
     * this method will update the average monthly fare for the user depending on the month(if its a
     * new month)
     */
    void updateAverageMonthlyFare(String month, double fare) {
        int intMonth = convertMonth(month);
        if (this.month == 0) {
            this.month = intMonth;
            this.averageMonthlyFare = fare / totalMonth;
        } else if (this.month == intMonth) {
            this.averageMonthlyFare = (this.averageMonthlyFare * totalMonth + fare) / totalMonth;
        } else {
            int difference = intMonth - this.month;
            this.averageMonthlyFare =
                    (this.averageMonthlyFare * this.totalMonth + fare) / (this.totalMonth + difference);
            this.totalMonth += difference;
            this.month = intMonth;
        }
    }

    /** A getter for the averageMonthlyFare. */
    double getAverageMonthlyFare() {
        return this.averageMonthlyFare;
    }

    /** A getter for the totalMonth variable */
    int getTotalMonth() {
        return totalMonth;
    }

    /**
     * used in cases where the user didn't do anything in that month to update its average monthly
     * cost.
     */
    void incrementTotalMonth() {
        averageMonthlyFare = (averageMonthlyFare * totalMonth) / (totalMonth + 1);
        totalMonth++;
    }

    /**
     * this method will turn the string representation of the month into int for
     * updateAverageMonthlyFare method.
     */
    private int convertMonth(String month) {
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
