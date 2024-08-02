package com.iscourse.api.controller;

import com.iscourse.api.exception.DuplicateCourseException;
import com.iscourse.api.exception.UnavailableEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateCourseException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateCourseException(DuplicateCourseException e) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "DuplicateCourse");
        errorResponse.put("message", e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UnavailableEntityException.class)
    public ResponseEntity<Map<String, String>> handleUnavailableEntityException(UnavailableEntityException e) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "UnavailableEntity");
        errorResponse.put("message", e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }
}
