package com.keshaw.FyersAPIIntegration.Logger;

import org.springframework.stereotype.Component;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@Component
public class customLogger {
    public static Logger log = Logger.getLogger("MyLog");

    public static void init() {
        FileHandler fh;
        try {
            fh = new FileHandler("K:/Keshaw/fyers API/Springboot/FyersIntegration.log");

            log.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            log.info("Logger Initialized ::");
        } catch (Exception e) {
            log.info("Exception in init : " + e);
        }
    }
}
