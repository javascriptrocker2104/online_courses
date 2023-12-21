package com.example.online_courses.exceptions;

import static java.lang.String.format;

public class UserAlreadyExistException extends Exception {

    public UserAlreadyExistException(String email) {
            super(format("Пользователь с почтой %s уже зарегистрирован", email));
        }
        @Override
        public synchronized Throwable fillInStackTrace() {
            return this;
        }

}
