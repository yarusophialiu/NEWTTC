package controller;


import java.io.IOException;
import java.io.Serializable;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogWriter implements Serializable {

    private static final Logger logger = Logger.getLogger(LogWriter.class.getName());


    public LogWriter(){
        logger.setLevel(Level.ALL);
    }

    public void helpLog(Level level, String message){
        try{
            FileHandler fileHandler = new FileHandler("phase2/CardAndUserLog.log", true);
            logger.addHandler(fileHandler);
            SimpleFormatter simpleFormatter= new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            logger.setUseParentHandlers(false);
            logger.log(level, message);
            fileHandler.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
