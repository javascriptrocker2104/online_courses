package com.example.online_courses.exceptions;

import java.util.UUID;

import static java.lang.String.format;

public class CourseNotFoundExceptionByID extends Exception {
    public CourseNotFoundExceptionByID(UUID id) {
        super(format("course with id %s not found", id));
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}