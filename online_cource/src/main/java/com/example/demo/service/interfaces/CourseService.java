package com.example.demo.service.interfaces;

import com.example.demo.domain.dto.CreateCourseRequest;
import com.example.demo.domain.dto.CourseDto;
import com.example.demo.domain.entity.Course;
import com.example.demo.domain.exceptions.CourseAlreadyExistException;
import com.example.demo.domain.exceptions.CourseNotFoundException;
import java.util.List;

public interface CourseService {
    CourseDto getCourseByName(String name) throws CourseNotFoundException;

    CourseDto createCourse(CreateCourseRequest course) throws CourseAlreadyExistException;

    List<Course> getAllCourse();

    CourseDto updateCourse(Course course);

    void deleteCourse(String course_id);
}
