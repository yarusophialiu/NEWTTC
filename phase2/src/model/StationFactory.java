package model;

import java.util.HashMap;

class StationFactory {
    private static HashMap<String, SubwayStation> subwayStationHashMap = new HashMap<>();
    private static HashMap<String, BusStation> busStationHashMap = new HashMap<>();

    Station newStation(String stationID, String vehicle, String lineNumber) {
        Integer lineNum = Integer.parseInt(lineNumber);
        if (vehicle.equals("subway")){
            if (subwayStationHashMap.keySet().contains(stationID)) {
                for (SubwayStation station : subwayStationHashMap.values()) {
                    if (station.getName().equals(stationID)) {
                        return station;
                    }
                }
            }
            Station station = new SubwayStation(stationID, vehicle);
            subwayStationHashMap.put(stationID, (SubwayStation) station);
            return station;
        }
       else{
            if (busStationHashMap.keySet().contains(stationID)) {
                for (BusStation stop : busStationHashMap.values()) {
                    if (stop.getName().equals(stationID) && stop.getLineNumber() == lineNum) {
                        return stop;
                    }
                }
            }
            Station station = new BusStation(stationID, vehicle, lineNum);
            busStationHashMap.put(stationID, (BusStation) station);
            return station;
        }
    }


    HashMap getSubwayStationHashMap(){
        return subwayStationHashMap;
    }

    HashMap getBusStationHashMap(){
        return busStationHashMap;
    }
}
