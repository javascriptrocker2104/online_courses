package com.example.online_courses.service;

import com.example.online_courses.dto.UserData;
import com.example.online_courses.exceptions.UserAlreadyExistException;
import com.example.online_courses.models.User;
import com.example.online_courses.repositories.RoleRepository;
import com.example.online_courses.repositories.UserRepository;
import com.example.online_courses.service.interfaces.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service("userService")
public class DefaultUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void register(UserData user) throws UserAlreadyExistException {

        if (!checkIfUserExist(user.getEmail())) {
            User userEntity = new User();
            BeanUtils.copyProperties(user, userEntity);
            encodePassword(userEntity, user);
            userEntity.setBlock(false);
            userEntity.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
            userEntity.setAttempts(0);
            userRepository.save(userEntity);
        }
    }

    @Override
    public boolean checkIfUserExist(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }

    private void encodePassword( User userEntity, UserData user){
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
    }
}
