import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PrestoCardSystem {

    ArrayList<User> users = new ArrayList<>();
    AdminUser adimin = new AdminUser("Boss", "1234");

    void doEvent(ArrayList<String> data) throws Exception {
        if (data.contains("bought")){
            for (User user : users) {
                if(user.getUserName().equals(data.get(0))){
                    for (int i = 0; i < Integer.parseInt(data.get(2)); i++){
                        user.buyCard();
                    }
                }
            }
        }
        else if (data.contains("enters") || data.contains("exits")){
            for (User user : users) {
                for (Card card : user.getMyCards()) {
                    if(card.getId() == Integer.parseInt(data.get(1))){
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date time = df.parse(data.get(7) +" " + data.get(8));
                        Station station = StationManager.newStation(data.get(3));
                        card.recordTrip(data.get(4), data.get(2), time, station);
                    }
                }
            }
        }
        else if (data.contains("suspended") || data.contains("retrieved")){
            for (User user : users) {
                for (Card card : user.getMyCards()) {
                    if(card.getId() == Integer.parseInt(data.get(1))){
                        card.reverseSuspended();
                    }
                }
            }
        }
        else if (data.contains("added")){
            for (User user : users) {
                for (Card card : user.getMyCards()) {
                    if(card.getId() == Integer.parseInt(data.get(6))){
                        card.increaseBalance(Integer.parseInt(data.get(0)));
                    }
                }
            }
        }
        else if (data.contains("ended")){
            this.adimin.report();
            this.adimin.clearData();
        }
        else{
            users.add(new User(data.get(0), data.get(1), data.get(2)));
        }
    }
}
