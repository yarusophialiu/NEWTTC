//package model;
//
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//
//public class PrestoCardSystem {
//    private static ArrayList<RegularUser> users = new ArrayList<>();
//    private StationFactory stationFactory;
//    private AdminUser adminUser= new AdminUser("admin", "admin@mail.com", "12344");
//
//    public PrestoCardSystem(StationFactory stationFactory){
//        this.stationFactory = stationFactory;
//        Card.setAdminUser(adminUser);
//    }
//
//    private Card getCard(String id){
//        for (RegularUser user:users){
//            for (Card card:user.getMyCards()){
//                if (card.getId() == Integer.parseInt(id)){
//                    return card;
//                }
//            }
//        }
//        return new Card();
//    }
//
//    private RegularUser getUser(String name){
//        for (RegularUser user:users){
//            if (user.getUserName().equals(name)){
//                return user;
//            }
//        }
//        return new RegularUser("","","");
//    }
//
//    void doEvent(ArrayList<String> data) throws Exception {
//        if (data.contains("bought")) { // events about buy new card.
//            RegularUser user = getUser(data.get(0));
//            for (int i = 0; i < Integer.parseInt(data.get(2)); i++) {
//                user.buyCard();
//            }
//        } else if (data.contains("enters")
//                || data.contains("exits")) { // events on enter and exit a station.
//            Card card = getCard(data.get(1));
//            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            Date time = df.parse(data.get(7) + " " + data.get(8));
//            Station station = stationFactory.newStation(data.get(3), data.get(4), "1");
//            card.updateOnTap(data.get(2), station, time, data.get(4), stationFactory);
//        } else if (data.contains("suspended")
//                || data.contains("retrieved")) { // events on suspend and retrieved card.
//            Card card = getCard(data.get(1));
//            card.reverseSuspended();
//        } else if (data.contains("added")) { // events on add balance for the card.
//            Card card = getCard(data.get(6));
//            card.increaseBalance(Integer.parseInt(data.get(0)));
//        } else if (data.contains("recent") && data.contains("trip")) {
//            Card card = getCard(data.get(1));
//            System.out.println(card.recentTripString());
//        } else if (data.contains("average")) {
//            RegularUser user = getUser(data.get(1));
//            System.out.println(
//                    "User "
//                            + user.getUserName()
//                            + "'s average monthly fee for the past "
//                            + user.getTotalMonth()
//                            + " months are "
//                            + user.getAverageMonthlyFare());
//        } else if (data.contains("nothing")) {
//            RegularUser user = getUser(data.get(1));
//            user.incrementTotalMonth();
//            System.out.println("Total month has been incremented for " + user.getUserName());
//        } else if (data.contains(
//                "ended,")) { // events on when the day ended, clear data from the previous day and print
//            // report.
//            this.adminUser.report();
//            this.adminUser.clearData();
//        } else { // events on create a new user.
//            users.add(new RegularUser(data.get(0), data.get(1), data.get(2)));
//        }
//    }
//}
