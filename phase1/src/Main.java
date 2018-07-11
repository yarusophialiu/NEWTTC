import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) throws Exception {
        BufferedReader fileReader = new BufferedReader(new FileReader("phase1/src/stations.txt"));
        String vehicle = fileReader.readLine();
        String info = fileReader.readLine();
        while (!info.equals("")) {
            ArrayList<String> dataArray = new ArrayList<String>(Arrays.asList(info.split(" ")));
            dataArray.remove(dataArray.get(1));
            Station station = StationManager.newStation(dataArray.get(0), vehicle);
                for (int i = 1; i < dataArray.size(); i++){
                    station.addNeighbours(StationManager.newStation(dataArray.get(i), vehicle));
                }
            info = fileReader.readLine();
        }
        vehicle = fileReader.readLine();
        info = fileReader.readLine();
        while (!(info == null)){
            ArrayList<String> dataArray = new ArrayList<String>(Arrays.asList(info.split(" ")));
            dataArray.remove(dataArray.get(1));
            Station stop = StationManager.newStation(dataArray.get(0), vehicle);
            for (int i = 1; i < dataArray.size(); i++){
                stop.addNeighbours(StationManager.newStation(dataArray.get(i), vehicle));
            }
            info = fileReader.readLine();
        }

        PrestoCardSystem prestoCardSystem = new PrestoCardSystem();
        BufferedReader fileReader2 = new BufferedReader(new FileReader("phase1/src/events.txt"));
        String info2 = fileReader2.readLine();
        while (!(info2 == null)){
            if (!info2.equals("")){
                ArrayList<String> data = new ArrayList<String>(Arrays.asList(info2.split(" ")));
                prestoCardSystem.doEvent(data);
            }
            info2 = fileReader2.readLine();
        }

  }
}