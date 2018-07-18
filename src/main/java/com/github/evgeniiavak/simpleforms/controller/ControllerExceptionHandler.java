package com.github.evgeniiavak.simpleforms.controller;

import com.github.evgeniiavak.simpleforms.exception.NotFoundException;
import com.github.evgeniiavak.simpleforms.model.ErrorData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = MoodController.class)
public class ControllerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorData> anyUnknownExceptionHandler(Exception ex) {
        ErrorData error = new ErrorData();
        error.setCode(ex.getClass().getName());
        error.setMessage(ex.getMessage() + " from anyUnknownException()");
        return ResponseEntity.status(500).body(error);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorData> notFoundExceptionHandler(NotFoundException ex) {
        ErrorData error = new ErrorData();
        error.setCode(ex.getClass().getName());
        error.setMessage(ex.getMessage() + " from notFoundExceptionHandler()");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

}
