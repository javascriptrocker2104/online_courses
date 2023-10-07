package com.example.demo.repository;

import com.example.demo.domain.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Roles,String>{
@Query(value="SELECT r FROM roles r WHERE r.name=(:name")
    Roles findByName(String name);
}

