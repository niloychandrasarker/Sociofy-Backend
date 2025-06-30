package com.niloy.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalException {


   @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleException(Exception ue, WebRequest req ) {

        ErrorDetails errorDetails = new ErrorDetails(ue.getMessage(),req.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<>(errorDetails, org.springframework.http.HttpStatus.BAD_REQUEST);
    }
}
