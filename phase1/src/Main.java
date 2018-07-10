import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException{
        BufferedReader fileReader = new BufferedReader(new FileReader("phase1/src/stations.txt"));
        String info = fileReader.readLine();
        while (!info.equals("")) {
            StationManager.addStations(info);
            info = fileReader.readLine();
        }
        info = fileReader.readLine();
        while (!(info == null)){
            StopManager.addStop(info);
            info = fileReader.readLine();
        }
  }
}