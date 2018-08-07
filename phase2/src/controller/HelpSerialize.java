package controller;

import model.User;

import java.io.*;
import java.util.HashMap;

/** A class that write the user information anytime creating a new user or change the balance of the card and
 * read the file whenever opens the program. */
public class HelpSerialize {

    /** A method that takes a HashMap of user and store it as .ser file. */
    void serializeUser(HashMap<String, User> users) throws IOException {
        File f = new File("phase2/serializedUser.ser");
        FileOutputStream fos =new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(users);
    }

    /** A method that read the .ser file and record the information on it. */
    public HashMap<String, User> deserializeUser() throws IOException, ClassNotFoundException {
        File f = new File("phase2/serializedUser.ser");
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);
        HashMap hashMap =  (HashMap) ois.readObject();
        return (HashMap<String, User>) hashMap;
    }
}
