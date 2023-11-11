package com.example.online_courses.service.interfaces;

import com.example.online_courses.dto.CourseDto;
import com.example.online_courses.dto.CreateCourseRequest;
import com.example.online_courses.exceptions.CourseAlreadyExistException;
import com.example.online_courses.exceptions.CourseNotFoundException;
import com.example.online_courses.models.Course;
import java.util.List;

public interface CourseService {
    CourseDto getCourseByName(String name) throws CourseNotFoundException;

    CourseDto createCourse(CreateCourseRequest course) throws CourseAlreadyExistException;

    List<Course> getAllCourses();

    CourseDto updateCourse(Course course);

    void deleteCourse(String courseId);
}

