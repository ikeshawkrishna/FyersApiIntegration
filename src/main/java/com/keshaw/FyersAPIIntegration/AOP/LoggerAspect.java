package com.keshaw.FyersAPIIntegration.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Aspect
@Component
public class LoggerAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerAspect.class);

    //return type, class.method(args)
    @Before("execution(* com.keshaw.FyersAPIIntegration..*(..))")
    public void logMethodCall(JoinPoint jp) {
        LOGGER.info("Method " + jp.getSignature().getName() + " called >> ");
    }

    @Before("execution(* com.keshaw.FyersAPIIntegration.GoNoGo.OptionValidation.getData(..))")
    public void logMethodCall1(JoinPoint jp) {
        // Optional: format the date and time
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        LOGGER.info("Method " + jp.getSignature().getName() + " called  at " + formattedDateTime + " >> ");
    }
}
