package controller;

import model.User;

import java.io.*;
import java.util.HashMap;

public class HelpSerialize {
    void serializeUser(HashMap<String, User> users) throws IOException {
        File f = new File("/Users/yaruliu/Desktop/group_0165/phase2/serializedUser.ser");
        FileOutputStream fos =new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(users);
    }

    public HashMap<String, User> deserializeUser() throws IOException, ClassNotFoundException {
        File f = new File("/Users/yaruliu/Desktop/group_0165/phase2/serializedUser.ser");
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);
        HashMap hashMap =  (HashMap) ois.readObject();
        return (HashMap<String, User>) hashMap;
    }
}
