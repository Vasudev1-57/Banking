package com.bankproject.controller;

import jakarta.servlet.ServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ValidationHandler   {
@ExceptionHandler(MethodArgumentNotValidException.class )
    protected ResponseEntity<Map<String,String>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex ) {

        Map<String, String> er = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) ->{

            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            er.put(fieldName, message);
        });
        return new ResponseEntity<Map<String,String>>(er, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(DataIntegrityViolationException.class )
    protected   ResponseEntity<Map<String,String>> dataIntegrityViolationException(DataIntegrityViolationException ex ) {
        Map<String, String> er = new HashMap<>();


            String fieldName ="DataIntegrityViolationException" ;
            String message = ex.getRootCause().getMessage() ;
            er.put(fieldName, message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(er) ;
    }
}
