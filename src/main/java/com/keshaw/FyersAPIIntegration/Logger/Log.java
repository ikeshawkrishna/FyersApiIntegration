package com.keshaw.FyersAPIIntegration.Logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class Log {

    private final static Logger logger = LogManager.getLogger(Log.class);

    public static void consoleLog(String message){
        logger.info(message);
    }

    public static void errorLog(String message, Throwable throwable){
        logger.error(message, throwable);
    }

}
