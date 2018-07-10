import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class PrestoCardSystem {

    ArrayList<User> users = new ArrayList<>();

    void doEvent() throws IOException, ParseException {
        BufferedReader fileReader = new BufferedReader(new FileReader("phase1/src/events.txt"));
        String info = fileReader.readLine();
        ArrayList<String> data = new ArrayList<String>(Arrays.asList(info.split(" ")));
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
        else{
            users.add(new User(data.get(0), data.get(1), data.get(2)));
        }
    }
}
