import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException{
        StationManager stationManager = new StationManager();
        StopManager stopManager = new StopManager();
        BufferedReader fileReader = new BufferedReader(new FileReader("phase1/src/stations.txt"));
        String info = fileReader.readLine();
        while (!info.equals("")) {
            stationManager.addStations(info);
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