
/**
 * Class User is to store information about the User and all sort of actions a user may take when
 * using the card.
 */
class User {
    /** store the name of the user. */
    private String userName;
    /** stores the email address of the user. */
    private String emailAddress;
    /** store the password for the user when login into the system(for phase 2) */
    private String password;


    /** initialize a new User instance. */
    User(String userName, String emailAddress, String password) {
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.password = password;
    }


    /** change the users user name. */
    void changeName(String newName) {
        this.userName = newName;
    }

    /** return the user name of the user. */
    String getUserName() {
        return userName;
    }

    /** return the email address of the user */
    String getEmailAddress() {
        return emailAddress;
    }

}
