package com.keshaw.FyersAPIIntegration;

import com.keshaw.FyersAPIIntegration.GoNoGo.OptionValidation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FyersApiIntegrationApplication {


    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(FyersApiIntegrationApplication.class, args);
        System.out.println("Working ..........");

        OptionValidation validation = context.getBean(OptionValidation.class);
        //customLogger log = context.getBean(customLogger.class);

//        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//        Runnable task = () -> {
//            System.out.println("Task is running at: " + System.currentTimeMillis());
//            // Your method call here
//            validation.getData();
//        };

        // Schedule the task to run every 1 minute (60 seconds)
//        scheduler.scheduleAtFixedRate(task, 0, 1, TimeUnit.MINUTES);
    }

}
