
import java.util.ArrayList;

public class User {
    private String userName;
    private String emailAddress;
    private ArrayList<Card> myCards;
    private String password;

    User(String userName, String emailAddress, String password){
        this.userName = userName;
        this.emailAddress = emailAddress;
        myCards = new ArrayList<Card>();
        this.password = password;
    }

    void buyCard(){
        Card card = new Card();  // modify the constructor later when finished Card class.
        addCard(card);

    }

    void addCard(Card card){
        if(!myCards.contains(card)){
            this.myCards.add(card);
        }
        else{
            System.out.println("This card was already added.");
        }
    }

    void lostCard(int id){
        for (Card card : myCards){
            if(card.getId() == id){
                card.reverseSuspended();
            }
        }
    }

    void changeName(String newName){
        this.userName = newName;
    }

    String getEmailAddress(){
        return emailAddress;
    }

}
