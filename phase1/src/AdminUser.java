class AdminUser {

    private String userName = "Admin";
    private String password;
    static int totalFare;
    static int totalStations;


    AdminUser(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    void report(){
        System.out.println(totalFare + " dollars were collected " + "from " + totalStations + " stations today.");
    }

    void clearData(){
        this.totalFare = 0;
        this.totalStations = 0;
    }

}
