import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) throws Exception{
    BufferedReader fileReader = new BufferedReader(new FileReader("phase2/stations.txt"));
    String vehicle = fileReader.readLine();
    String info = fileReader.readLine();
    StationFactory stationFactory = new StationFactory();
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
          switch (vehicle){
            case "subway":
              Station subwayStation = stationFactory.newStation(dataArray.get(1), vehicle, "1");
              for (int i = 3; i < dataArray.size(); i++) {
                Station newNeighbour = stationFactory.newStation(dataArray.get(i), vehicle, "1");
                ((SubwayStation)subwayStation).addNeighbours((SubwayStation) newNeighbour);
              }
              info = fileReader.readLine();
              break;
            case "bus":
              Station busStation = stationFactory.newStation(dataArray.get(1), vehicle, dataArray.get(0));
              for (int i = 3; i < dataArray.size(); i++){
                Station newNeighbour = stationFactory.newStation(dataArray.get(i), vehicle, dataArray.get(0));
                ((BusStation) busStation).addNeighbours((BusStation) newNeighbour);
              }
              info = fileReader.readLine();
              break;
          }
          break;
      }
    }
      PrestoCardSystem prestoCardSystem = new PrestoCardSystem(stationFactory);
      BufferedReader fileReader2 = new BufferedReader(new FileReader("phase2/events.txt"));
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
