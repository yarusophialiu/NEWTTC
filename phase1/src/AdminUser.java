public class AdminUser {

    private String userName = "Admin";
    private String password;
    static int totalFare;
    static int totalStations;

    String report(){
        return totalFare + " dollars were collected " + "from " + totalStations + " stations today.";
    }

}
