package com.example.online_courses.exceptions;

import static java.lang.String.format;

public class CourseNotFoundException extends Exception {
    public CourseNotFoundException(String name) {
        super(format("course with name %s not found", name));
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}