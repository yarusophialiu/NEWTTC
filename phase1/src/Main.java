import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) throws IOException{
        StationManager stationManager = new StationManager();
        StopManager stopManager = new StopManager();
        BufferedReader fileReader = new BufferedReader(new FileReader("phase1/src/stations.txt"));
        String info = fileReader.readLine();
        info = fileReader.readLine();
        while (!info.equals("")) {
<<<<<<< HEAD
            String[] data = info.split(" ");
            ArrayList<String> dataArray = new ArrayList<String>(Arrays.asList(data));
            dataArray.remove(dataArray.get(1));
            for (String id : dataArray) {
                Station station = StationManager.newStation(id);
                for (int i = 1; i < dataArray.size(); i++){
                    station.addNeighbours(StationManager.newStation(dataArray.get(i)));
                }
            }
=======
            stationManager.addStations(info);
>>>>>>> b9cab4c1d5fd5a59fb60449edb5376a2fd5065a9
            info = fileReader.readLine();
        }
        info = fileReader.readLine();
        while (!(info == null)){
            stopManager.addStop(info);
            info = fileReader.readLine();
        }
        // the above parts are for reading from station.txt

        BufferedReader fileReader2 = new BufferedReader(new FileReader("phase1/src/events.txt"));
        String info2 = fileReader2.readLine();

  }
}