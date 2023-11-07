package com.example.online_courses.repositories;

import com.example.online_courses.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

}