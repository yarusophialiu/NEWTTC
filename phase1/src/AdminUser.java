public class AdminUser {

    private String userName = "Admin";
    private String password;

    String report(){
        return Trip.totalFare + " dollars were collected " + "from " + Trip.totalStations + " today.";
    }

}
