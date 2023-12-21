package com.example.online_courses.controllers;

import com.example.online_courses.dto.UserData;
import com.example.online_courses.exceptions.PasswordIsWrong;
import com.example.online_courses.exceptions.UserAlreadyExistException;
import com.example.online_courses.exceptions.UserAlreadyExistException2;
import com.example.online_courses.models.Content;
import com.example.online_courses.models.Course;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang3.StringUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.example.online_courses.service.DefaultUserService.isValidPassword;


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
            if (isValidPassword(userData.getPassword()))
                userService.register(userData);
            else {
                bindingResult.rejectValue("password", "userData.password","The password does not work, please create a different one.");
                model.addAttribute("registration", userData);
                return "registration";
            }
        }catch (UserAlreadyExistException e){
            bindingResult.rejectValue("email", "userData.email","An account already exists for this email.");
            model.addAttribute("registration", userData);
            return "registration";
        }
        return "redirect:/login";
    }

     @PostMapping(value = "/admin/blockuser")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String hideUser(@RequestParam(name="name", required=false) String email) {

        User user = userRepository.findByEmail(email);

        user.setBlock(true);
        userRepository.save(user);

        return "redirect:/admin";
    }

    @GetMapping("/admin/blockuser")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String viewUserBlock() {
        return "blockuser";
    }


    @PostMapping("/admin/deleteuser")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteUser(@RequestParam(name="name", required=false) String email) {
        User user = userRepository.findByEmail(email);
        UUID user_id = user.getId();
        user.getRoles().clear();
        userRepository.deleteById(user_id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/deleteuser")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String viewUserDelete() {
        return "deleteuser";
    }


}
