import java.util.ArrayList;


class StationManager {
    private static ArrayList<Station> stationSet = new ArrayList<>();
    private static ArrayList<String> stationIDSet = new ArrayList<>();

    private static ArrayList<Station> stopSet = new ArrayList<>();
    private static ArrayList<String> stopIDSet = new ArrayList<>();

    static void addStations(Station station){
        stationSet.add(station);
        stationIDSet.add(station.getName());
    }

    static void addStops(Station stop){
        stopSet.add(stop);
        stopIDSet.add(stop.getName());
    }

    static ArrayList<Station> getStationSet(){
        return stationSet;
    }

    static ArrayList<String> getStationIDSet(){
        return stationIDSet;
    }

    static ArrayList<Station> getStopSet(){
        return stopSet;
    }

    static ArrayList<String> getStopIDSet(){
        return stopIDSet;
    }

    static Station newStation(String stationID, String vehicle){
        if (vehicle.equals("subway")){
            if (stationIDSet.contains(stationID)){
                for (Station station : stationSet) {
                    if(station.getName().equals(stationID)){
                        return station;
                    }
                }
            }
            return new Station(stationID, vehicle);
        }
        else{
            if (stopIDSet.contains(stationID)){
                for (Station stop : stopSet) {
                    if(stop.getName().equals(stationID)){
                        return stop;
                    }
                }
            }
            return new Station(stationID, vehicle);
        }
    }

    static int minDistance(Station source, Station destination){
        source.setDistance(0);
        ArrayList<Station> queue = new ArrayList<>();
        if (source.getVehicle().equals("subway")){
            for (Station station :stationSet ) {
                if (station != source){
                    station.setDistance(Integer.MAX_VALUE);
                }
                queue.add(station);
            }
        }
         else{
            for (Station stop :stopSet ) {
                if (stop != source){
                    stop.setDistance(Integer.MAX_VALUE);
                }
                queue.add(stop);
            }
        }
            while (!queue.isEmpty()){
                Station minStation = queue.get(0);
                Integer min = minStation.getDistance();
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
