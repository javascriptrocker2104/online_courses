package com.example.online_courses.dto;
import com.example.online_courses.models.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;


@Data
public class UserData implements Serializable {

    @NotEmpty(message = "Имя не может быть пустым")
    private String name;

    @NotEmpty(message = "Почта не может быть пустой")
    @Email(message = "Введите действительную почту")
    private String email;

    @NotEmpty(message = "Пароль не может быть пустым")
    private String password;
}