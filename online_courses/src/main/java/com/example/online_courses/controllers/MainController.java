package com.example.online_courses.controllers;

import com.example.online_courses.models.User;
import com.example.online_courses.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("name", "Главная страница");
        return "home";
    }

}