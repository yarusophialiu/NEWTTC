import java.util.ArrayList;

class Stop {
    /**
     * this class is used to record stops for bus.
     */
    private String name;

    private static ArrayList<String> stops = new ArrayList<>();

    Stop(String name){
        this.name = name;
        Stop.stops.add(name);
    }

    static ArrayList<String> getStops() {
        return stops;
    }
}
