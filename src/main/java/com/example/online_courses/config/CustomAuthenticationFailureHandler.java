package com.example.online_courses.config;

import com.example.online_courses.models.User;
import com.example.online_courses.repositories.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private String email;

    public void setEmail(String email) {
        this.email = email;
    }

    private final UserRepository userRepository;

    public CustomAuthenticationFailureHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        //String email = request.getParameter("email");
        User user = userRepository.findByEmail(this.email);
        int failedAttempts = user.getAttempts() + 1;
        System.out.println(failedAttempts);

        if (failedAttempts >= 3) {
            user.setBlock(true);
        }

        user.setAttempts(failedAttempts);
        userRepository.save(user);

        String redirectUrl = "/login?error";

        // Перенаправление на указанную страницу
        response.sendRedirect(request.getContextPath() + redirectUrl);
    }

}
