package com.example.demo.repository;

import com.example.demo.domain.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, UUID> {
    @Query(value = "SELECT c FROM Course c")
    List<Course> getAll();
    Optional<Course> findByName(String name);

    Boolean existsByName(String name);
}