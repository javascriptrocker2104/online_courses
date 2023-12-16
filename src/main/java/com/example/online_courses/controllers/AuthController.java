package com.example.online_courses.controllers;

import com.example.online_courses.dto.UserData;
import com.example.online_courses.exceptions.PasswordIsWrong;
import com.example.online_courses.exceptions.UserAlreadyExistException;
import com.example.online_courses.models.Content;
import com.example.online_courses.models.User;
import com.example.online_courses.repositories.UserRepository;
import com.example.online_courses.service.RecaptchaService;
import com.example.online_courses.service.UserDetailsServiceImpl;
import com.example.online_courses.service.interfaces.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang3.StringUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Controller
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private RecaptchaService recaptchaService;

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public ResponseEntity<?> signup(@Valid User user,
                                    @RequestParam(name="g-recaptcha-response") String recaptchaResponse,
                                    HttpServletRequest request
    ) {

        String ip = request.getRemoteAddr();
        String captchaVerifyMessage =
             recaptchaService.verifyRecaptcha(ip, recaptchaResponse);

        if ( StringUtils.isNotEmpty(captchaVerifyMessage)) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", captchaVerifyMessage);
            return ResponseEntity.badRequest()
                    .body(response);
        }
        userRepository.save(user);
        return ResponseEntity.ok().build();
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

    @RequestMapping(value = "/User/{id}/hide", method = RequestMethod.PUT)
    public ResponseEntity<String> hideUser(@PathVariable("id") UUID id) {

        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        user.setBlock(true);
        userRepository.save(user);

        return ResponseEntity.ok().build();
    }
}
