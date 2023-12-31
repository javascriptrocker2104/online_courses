package com.example.online_courses.repositories;

import com.example.online_courses.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, UUID> {
    @Query(value = "SELECT cs FROM Course cs WHERE cs.block=false")
    List<Course> getAll();
    Optional<Course> findByName(String name);
    Optional<Course> findById(UUID id);
    Boolean existsByName(String name);
}