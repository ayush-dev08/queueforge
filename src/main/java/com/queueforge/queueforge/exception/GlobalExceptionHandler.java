package com.queueforge.queueforge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice//connects to ResourceNotFpundException class below down
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)//here connection
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);//without this spring just print error message , this will get ResourceNotFoundException message like "Seat is not present etc" and then return error ,so it doesn't look direty error 
    }
}
