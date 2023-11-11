package com.example.online_courses.exceptions;

import java.util.UUID;

import static java.lang.String.format;

public class ContentNotFoundException extends Exception {
    public ContentNotFoundException(UUID content_id) {
        super(format("content with id= %s not found", content_id));
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}

