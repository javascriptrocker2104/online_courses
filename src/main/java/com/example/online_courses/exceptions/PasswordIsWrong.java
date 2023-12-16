package com.example.online_courses.exceptions;

import static java.lang.String.format;

public class PasswordIsWrong extends Exception{
    public PasswordIsWrong() {
        super("Неверный пароль");
    }
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
