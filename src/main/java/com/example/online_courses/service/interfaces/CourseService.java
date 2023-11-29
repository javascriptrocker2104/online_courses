package com.example.online_courses.service.interfaces;

import com.example.online_courses.dto.CourseDto;
import com.example.online_courses.dto.CreateCourseRequest;
import com.example.online_courses.exceptions.CourseAlreadyExistException;
import com.example.online_courses.exceptions.CourseNotFoundException;
import com.example.online_courses.exceptions.CourseNotFoundExceptionByID;
import com.example.online_courses.models.Course;
import java.util.List;
import java.util.UUID;

public interface CourseService {
    CourseDto getCourseByName(String name) throws CourseNotFoundException;

    CourseDto createCourse(CreateCourseRequest course) throws CourseAlreadyExistException;

    List<CourseDto> getAllCourses();

    CourseDto updateCourse(Course course);

    void deleteCourse(String courseId);

    void assignUserToCourse(UUID id, UUID course_id);

    CourseDto getCourseById(UUID id) throws CourseNotFoundExceptionByID;
}

