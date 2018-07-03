import java.util.ArrayList;

public class CardManager {
    private ArrayList<Card> cardList = new ArrayList<Card>();

    static ArrayList<String> stationList = new ArrayList<>();

    void addCard(Card card){cardList.add(card);
    }

    ArrayList getCardList(){
        return cardList;
    }

    void deleteCard(Long id){
        for (int i = 0; i < cardList.size();i++){
            if (cardList.get(i).getId() == id){
                cardList.remove(i);
            }
        }
    }
}
