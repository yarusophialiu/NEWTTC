import java.util.ArrayList;

public class UserManager {
    private ArrayList<User> Userlist = new ArrayList<>();


    void addUser(User user){
        Userlist.add(user);
    }

    void removeUser(User user){
        for (int i = 0; i < Userlist.size(); i++){
            if (Userlist.get(i).getEmailAddress().equals(user.getEmailAddress())){
                Userlist.remove(i);
            }
        }
    }


}
