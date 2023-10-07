package com.example.demo.repository;

import com.example.demo.domain.entity.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface CourseRepository extends JpaRepository<Courses,String>{
@Query(value="SELECT c FROM courses c WHERE c.name=(:name")
    Courses findByName(String name);
}

