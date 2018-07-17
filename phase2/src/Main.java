import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
  /**
   * main method for our program, read stations.txt and record information from them, and read
   * events.txt and pass the information in it one line after one line to PrestoCardSystem to
   * execute those events.
   */
  public static void main(String[] args) throws Exception {
    BufferedReader fileReader = new BufferedReader(new FileReader("phase1/stations.txt"));
    String vehicle = fileReader.readLine();
    String info = fileReader.readLine();
    while (!(info == null)) {
      switch (info) {
        case "":
          info = fileReader.readLine();
          break;
        case "bus":
          vehicle = "bus";
          info = fileReader.readLine();
          break;
        default:
          ArrayList<String> dataArray = new ArrayList<>(Arrays.asList(info.split(" ")));
          dataArray.remove(dataArray.get(1));
          Station station = StationManager.newStation(dataArray.get(0), vehicle);
          for (int i = 1; i < dataArray.size(); i++) {
            station.addNeighbours(StationManager.newStation(dataArray.get(i), vehicle));
          }
          info = fileReader.readLine();
          break;
      }
    }

    PrestoCardSystem prestoCardSystem = new PrestoCardSystem();
    BufferedReader fileReader2 = new BufferedReader(new FileReader("phase1/events.txt"));
    String info2 = fileReader2.readLine();
    while (!(info2 == null)) {
      if (!info2.equals("")) {
        ArrayList<String> data = new ArrayList<>(Arrays.asList(info2.split(" ")));
        prestoCardSystem.doEvent(data);
      }
      info2 = fileReader2.readLine();
    }
  }
}
