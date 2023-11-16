package com.example.online_courses.controllers;

import com.example.online_courses.dto.UserData;
import com.example.online_courses.exceptions.UserAlreadyExistException;
import com.example.online_courses.models.User;
import com.example.online_courses.repositories.UserRepository;
import com.example.online_courses.service.UserDetailsServiceImpl;
import com.example.online_courses.service.interfaces.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }


    @GetMapping("/registration")
    public String register(final Model model){
        model.addAttribute("userData", new UserData());
        return "registration";
    }

    @PostMapping("/registration")
    public String userRegistration(final @Valid UserData userData, final BindingResult bindingResult, final Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("registration", userData);
            return "registration";
        }
        try {
            userService.register(userData);
        }catch (UserAlreadyExistException e){
            bindingResult.rejectValue("email", "userData.email","An account already exists for this email.");
            model.addAttribute("registration", userData);
            return "registration";
        }
        return "redirect:/login";
    }





    /*
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
*/
}
