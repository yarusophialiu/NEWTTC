import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) throws IOException{
        BufferedReader fileReader = new BufferedReader(new FileReader("phase1/src/stations.txt"));
        String info = fileReader.readLine();
        info = fileReader.readLine();
        while (!info.equals("")) {
            String[] data = info.split(" ");
            ArrayList<String> dataArray = new ArrayList<String>(Arrays.asList(data));
            dataArray.remove(dataArray.get(1));
            for (String id : dataArray) {
                Station station = StationManager.newStation(id);
                for (int i = 1; i < dataArray.size(); i++){
                    station.addNeighbours(StationManager.newStation(dataArray.get(i)));
                }
            }
            info = fileReader.readLine();
        }
        info = fileReader.readLine();
        while (!(info == null)){
            StopManager.addStop(info);
            info = fileReader.readLine();
        }
  }
}