package controller;


import java.io.IOException;
import java.io.Serializable;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/** A class that helps other class to write log files. */
public class LogWriter implements Serializable {

    /** Since all instances should share the same logger, we make it static final here as an variable. */
    private static final Logger logger = Logger.getLogger(LogWriter.class.getName());


    /** A constructor for LogWriter class and set the level of the logger here to all levels. */
    public LogWriter(){
        logger.setLevel(Level.ALL);
    }

    /** A method that takes in a level and String of message and write log file according to them. */
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
