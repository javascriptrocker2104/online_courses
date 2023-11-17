package com.example.online_courses.exceptions;
import static java.lang.String.format;

public class CourseAlreadyExistException extends Exception{
    public CourseAlreadyExistException(String name) {
        super(format("Course with name %s already exists", name));
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}