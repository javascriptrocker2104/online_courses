package com.example.online_courses.service;

import com.example.online_courses.details.UserDetailsImpl;
import com.example.online_courses.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return UserDetailsImpl.fromUser(
                repository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found!"))
        );
    }
}