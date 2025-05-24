package com.keshaw.FyersAPIIntegration.Exceptions;

import com.keshaw.FyersAPIIntegration.Logger.Log;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ErrorResponse requestHeaders(MissingRequestHeaderException ex){
        Log.errorLog("Excetion occured ", ex);
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Request header "+ex.getHeaderName() + " is not present")
                .build();
        return errorResponse;
    }
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ErrorResponse MissingServletRequestParameterException(MissingServletRequestParameterException ex){
        Log.errorLog("Excetion occured " , ex);
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Request Parameter "+ex.getParameterName() + " is not present")
                .build();
        return errorResponse;
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ErrorResponse InvalidRequestException(InvalidRequestException ex){
        Log.errorLog("Excetion occured " , ex);
        return ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .build();
        
    }

}
