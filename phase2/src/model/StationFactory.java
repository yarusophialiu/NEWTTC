package model;

import java.util.HashMap;

public class StationFactory {
    private static HashMap<String, SubwayStation> subwayStationHashMap = new HashMap<>();
    private static HashMap<String, BusStation> busStationHashMap = new HashMap<>();

    private Station newSub(String stationID, String vehicle) {
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

    private Station newBus(String stationID, String vehicle, Integer lineNum) {
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

    public Station newStation(String stationID, String vehicle, String lineNumber) {
        Integer lineNum = Integer.parseInt(lineNumber);
        if (vehicle.equals("subway")){
            return newSub(stationID, vehicle);
        }
       else{
            return newBus(stationID, vehicle, lineNum);
        }
    }


    HashMap getSubwayStationHashMap(){
        return subwayStationHashMap;
    }

    HashMap getBusStationHashMap(){
        return busStationHashMap;
    }
}
