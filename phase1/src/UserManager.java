import java.util.ArrayList;

public class UserManager {
    private ArrayList<User> userList = new ArrayList<>();


    void addUser(User user){
        userList.add(user);
    }

    void removeUser(User user){
        for (int i = 0; i < userList.size(); i++){
            if (userList.get(i).getEmailAddress().equals(user.getEmailAddress())){
                userList.remove(i);
            }
        }
    }


}
