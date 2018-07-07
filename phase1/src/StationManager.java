import java.util.ArrayList;

class StationManager {
    private static ArrayList<Station> stations = new ArrayList<>();


    static void addStations(Station station){
        stations.add(station);
    }

    static ArrayList<Station> getStations(){
        return stations;
    }

    int getShortestDistance(Trip trip){
        Station source = trip.getEntrance();
        Station destination = trip.getExit();
        source.setDistance(0);
        while (!source.getNeighbour().contains(destination)){
            for (Station station :source.getNeighbour()) {
                if (!(station.getPathVia()== null) & station.getDistance() > source.getDistance() + 1){
                    station.setDistance(source.getDistance() + 1);
//                    station.setPathVia();
                }
            }
        }
        return 0;
    }
}
