package com.example.demo.repository;

import com.example.demo.domain.entity.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface GroupRepository extends JpaRepository<Groups,String>{
@Query(value="SELECT g FROM groups g WHERE g.name=(:name")
    Groups findByName(String name);
}

