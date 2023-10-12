package com.example.demo.domain.exceptions;

import static java.lang.String.format;

public class CourseAlreadyExistException extends Exception{
    public CourseAlreadyExistException(String name) {
        super(format("course with name %s is already exist", name));
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
