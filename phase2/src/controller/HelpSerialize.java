package controller;

import model.*;

import java.io.*;
import java.util.ArrayList;

public class HelpSerialize {
    void serializeUser(ArrayList<User> users) throws IOException {
        File f = new File("../phase2/test.txt");
        FileOutputStream fos =new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(users);
    }

    ArrayList<User> deserializeUser() throws IOException, ClassNotFoundException {
        File f = new File("../phase2/test.txt");
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);
        return (ArrayList<User>) ois.readObject();
    }
}
