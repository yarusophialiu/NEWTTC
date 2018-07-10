import java.util.ArrayList;


class StationManager {
    private static ArrayList<Station> stationSet = new ArrayList<>();
    private static ArrayList<String> stationIDSet = new ArrayList<>();

    static void addStations(Station station){
        stationSet.add(station);
        stationIDSet.add(station.getName());
    }

    static ArrayList<Station> getStationSet(){
        return stationSet;
    }

    static ArrayList<String> getStationIDSet(){
        return stationIDSet;
    }

    static Station newStation(String stationID){
        ArrayList<Station> stationSet = StationManager.getStationSet();
        ArrayList<String> stationIDSet = StationManager.getStationIDSet();
        if (stationIDSet.contains(stationID)){
            for (Station station : stationSet) {
                if(station.getName().equals(stationID)){
                    return station;
                }
            }
        }
        return new Station(stationID);
    }

    static int minDistance(Station source, Station destination){
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
