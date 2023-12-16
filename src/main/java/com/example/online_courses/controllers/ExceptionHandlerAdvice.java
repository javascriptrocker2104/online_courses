package com.example.online_courses.controllers;

import com.example.online_courses.exceptions.CourseAlreadyExistException;
import com.example.online_courses.exceptions.CourseNotFoundException;
import com.example.online_courses.exceptions.PasswordIsWrong;
import com.example.online_courses.models.User;
import com.example.online_courses.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestParam;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

    UserRepository userRepository;

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

    @ExceptionHandler(PasswordIsWrong.class)
    public ResponseEntity handlePasswordIsWrong(PasswordIsWrong e, @RequestParam String email){
        //String email = request.getParameter("email");
        User user =userRepository.findByEmail(email);
        int failedAttempts = user.getAttempts() + 1;

        if (failedAttempts >= 3) {
            user.setBlock(true);
        }

        user.setAttempts(failedAttempts);
        userRepository.save(user);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }

}