package com.example.online_courses.exceptions;

import static java.lang.String.format;

public class ContentAlreadyExistException extends Exception{
    public ContentAlreadyExistException(String name) {
        super(format("Content with name %s already exists", name));
    }
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}