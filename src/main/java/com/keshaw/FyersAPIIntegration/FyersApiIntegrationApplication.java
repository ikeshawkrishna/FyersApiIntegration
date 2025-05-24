package com.keshaw.FyersAPIIntegration;

import com.keshaw.FyersAPIIntegration.Logger.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class FyersApiIntegrationApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(FyersApiIntegrationApplication.class, args);

        Environment env = context.getEnvironment();
        Log.consoleLog("Working from logger .........." + env.getProperty("clientid"));
    }

}
