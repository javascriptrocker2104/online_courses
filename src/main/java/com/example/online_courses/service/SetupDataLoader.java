package com.example.online_courses.service;

import com.example.online_courses.models.Privilege;
import com.example.online_courses.models.Role;
import com.example.online_courses.models.User;
import com.example.online_courses.repositories.PrivilegeRepository;
import com.example.online_courses.repositories.RoleRepository;
import com.example.online_courses.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;
        Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        List<Privilege> adminPrivileges = new ArrayList<>(Arrays.asList(readPrivilege, writePrivilege));
        List<Privilege> userPrivileges = new ArrayList<>(Arrays.asList(readPrivilege));
        Role adminRole = createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", userPrivileges);
        createRoleIfNotFound("ROLE_TEACHER", adminPrivileges);

        createUserIfNotFound("admin@gmail.com", "Admin", "kek", new ArrayList<>(Arrays.asList(adminRole)));

        alreadySetup = true;
    }

    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {

        Privilege privileges = privilegeRepository.findByName(name);
        if (privileges == null) {
            privileges = new Privilege(name);
            privilegeRepository.save(privileges);
        }
        return privileges;
    }

    @Transactional
    Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }


    @Transactional
    public User createUserIfNotFound(String email,  String Name,  String password,  Collection<Role> roles) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            user = new User();
            user.setName(Name);
            user.setPassword(passwordEncoder.encode(password));
            user.setEmail(email);
            user.setBlock(false);
        }
        user.setRoles(roles);
        user = userRepository.save(user);
        return user;
    }

}