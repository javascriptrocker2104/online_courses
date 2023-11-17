package com.example.online_courses.service;

import com.example.online_courses.dto.UserData;
import com.example.online_courses.exceptions.UserAlreadyExistException;
import com.example.online_courses.models.User;
import com.example.online_courses.repositories.UserRepository;
import com.example.online_courses.service.interfaces.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service("userService")
public class DefaultUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void register(UserData user) throws UserAlreadyExistException {

        if (!checkIfUserExist(user.getEmail())) {
            User userEntity = new User();
            BeanUtils.copyProperties(user, userEntity);
            encodePassword(userEntity, user);
            userRepository.save(userEntity);
        }
    }

    @Override
    public boolean checkIfUserExist(String email) {
        return userRepository.findByEmail(email) !=null ? true : false;
    }

    private void encodePassword( User userEntity, UserData user){
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
    }


}
