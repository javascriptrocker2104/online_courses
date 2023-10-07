package com.example.demo.repository;

import com.example.demo.domain.entity.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

public interface PeopleRepository extends JpaRepository<People,String>{
@Query(value="SELECT p FROM people p WHERE p.name=(:name")
    People findByName(String name);
}

