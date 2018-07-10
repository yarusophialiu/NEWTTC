
import java.util.ArrayList;

class User {
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
        System.out.println(userName + "bought" + " " + "1 card." );

    }

    void addCard(Card card){
        if(!myCards.contains(card)){
            this.myCards.add(card);
            card.setUser(this);
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

    ArrayList<Card> getMyCards(){
        return myCards;
    }

    void changeName(String newName){
        this.userName = newName;
    }
    String getUserName() {
        return userName;
    }

    String getEmailAddress(){
        return emailAddress;
    }

}
