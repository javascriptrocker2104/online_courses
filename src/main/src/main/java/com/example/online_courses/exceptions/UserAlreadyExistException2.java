package com.example.online_courses.exceptions;

import static java.lang.String.format;

public class UserAlreadyExistException2 extends Exception {

    public UserAlreadyExistException2(String password) {
            super(format("Пароль не соответствует политике безопасности сайта" +
                    "Проверте соблюдение следующих условий:" +
                    "- наличие одной цифры 0-9" +
                    "- наличие одной буквы верхнего регистра A-Z" +
                    "- наличие одной буквы нжниго регистра a-z" +
                    "- длинна пароля не менее 7 символов, но не более 20", password));
        }
        @Override
        public synchronized Throwable fillInStackTrace() {
            return this;
        }

}
