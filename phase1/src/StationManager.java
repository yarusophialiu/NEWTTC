import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;


class StationManager {



    private ArrayList<Station> stationSet = new ArrayList<>();

    void addStations(String stationID){
        Station station = new Station(stationID);
        stationSet.add(station);
    }

    int minDistance(Station source, Station destination){
        source.setDistance(0);
        ArrayList<Station> queue = new ArrayList<>();
        for (Station station :stationSet ) {
            if (station != source){
                station.setDistance(Integer.MAX_VALUE);
            }
            queue.add(station);
        }
        Station minStation = queue.get(0);
        Integer min = minStation.getDistance();
        while (!queue.isEmpty()){
            for (Station station :queue) {
                if (station.getDistance() < min){
                    min = station.getDistance();
                    minStation = station;
                }
            }
            queue.remove(minStation);

            for (Station neighbour :minStation.getNeighbours()) {
                int newDistance = minStation.getDistance() + 1;
                if (newDistance < neighbour.getDistance()){
                    neighbour.setDistance(newDistance);
                }
            }
        }
        return destination.getDistance();
    }

}
