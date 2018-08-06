package controller;

import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class HelpSerialize {
    void serializeUser(HashMap<String, User> users) throws IOException {
        File f = new File("../phase2/test.txt");
        FileOutputStream fos =new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(users);
    }

    public HashMap<String, User> deserializeUser() throws IOException, ClassNotFoundException {
        File f = new File("../phase2/test.txt");
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);
        return (HashMap<String, User>) ois.readObject();
    }
}
