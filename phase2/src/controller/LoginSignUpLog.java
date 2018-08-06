package controller;


import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

class LoginSignUpLog {

    private static final Logger logger = Logger.getLogger(LoginSignUpLog.class.getName());

    LoginSignUpLog(){
//        logger.setLevel(Level.ALL);
//        try {
//            FileHandler fileHandler = new FileHandler("phase2/CardAndUserLog.log", true);
//            logger.addHandler(fileHandler);
//            SimpleFormatter simpleFormatter= new SimpleFormatter();
//            fileHandler.setFormatter(simpleFormatter);
//            logger.setUseParentHandlers(false);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    void helpLog(Level level, String message){
//        logger.log(level, message);
    }
}
