package com.example.online_courses.controllers;

import com.example.online_courses.exceptions.CourseAlreadyExistException;
import com.example.online_courses.exceptions.CourseNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity handleCourseNotFoundException(CourseNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }

    @ExceptionHandler(CourseAlreadyExistException.class)
    public ResponseEntity handleCourseAlreadyExistException(CourseAlreadyExistException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }
}