package com.example.online_courses.controllers;

import com.example.online_courses.models.User;
import com.example.online_courses.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/registration")
    public String userAdd(Model model) {
        return "registration";
    }

    @PostMapping("/registration")
    public String userPostAdd(@RequestBody String name, @RequestBody String email, @RequestBody String password, Model model) {
        User user = new User(name, email, password);
        userRepository.save(user);
        return "redirect:/login";
    }

}
