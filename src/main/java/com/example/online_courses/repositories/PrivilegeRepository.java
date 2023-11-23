package com.example.online_courses.repositories;

import com.example.online_courses.models.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PrivilegeRepository extends JpaRepository<Privilege, UUID>{
    Privilege findByName(String name);
}
