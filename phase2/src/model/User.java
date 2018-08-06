package model;
import java.util.HashMap;

/**
 * Class User is to store information about the User and all sort of actions a user may take when
 * using the card.
 */
public class User {
    /** store the name of the user. */
    private String userName;
    /** stores the email address of the user. */
    private String emailAddress;

    /** store the password for the user when login into the system(for phase 2) */
    private String password;
    private static HashMap<String, User> users = new HashMap<>();


    /** initialize a new User instance. */
    public User(String userName, String emailAddress, String password) {
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.password = password;
        users.put(emailAddress, this);
    }

    /** change the users user name. */
    public void changeName(String newName) {
        this.userName = newName;
    }

    /** return the user name of the user. */
    public String getUserName() {
        return userName;
    }

    /** return the email address of the user */
    public String getEmailAddress() {
        return emailAddress;
    }

    public static HashMap<String, User> getUsers(){
        return users;
    }

    public boolean correctPassword(String verifyPassword){
        return password.equals(verifyPassword);
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
