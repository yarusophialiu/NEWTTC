package model;

import java.util.ArrayList;
import java.util.Date;

public class RegularUser extends User {

    /** store all the card that user have */
    private ArrayList<Card> myCards;

    private UserAverageMonthlyFare userAverageMonthlyFare = new UserAverageMonthlyFare();

    public RegularUser(String userName, String emailAddress, String password) {
        super(userName, emailAddress, password);
        this.myCards = new ArrayList<>();
    }

    /** user buy a new card */
    public Card buyCard() {
        Card card = new Card();
        addCard(card);
        System.out.println(getUserName() + " bought" + " " + "1 card.");
        return card;
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

    /**
     * this method will update the average monthly fare for the user depending on the month(if its a
     * new month)
     */
    void updateAverageMonthlyFare(Date time, double fare) {
        userAverageMonthlyFare.updateAverageMonthlyFare(time, fare);
    }

    int getFirstYear(){
        return userAverageMonthlyFare.getFirstYear();
    }

    /** A getter for the averageMonthlyFare. */
    public double getAverageMonthlyFare() {
        return userAverageMonthlyFare.getAverageMonthlyFare();
    }



    /** return an ArrayList of cards that the user has. */
    public ArrayList<Card> getMyCard(){
        return myCards;
    }

    public void removeCard(Card card){
        myCards.remove(card);
    }
}
