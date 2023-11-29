package com.example.online_courses.controllers;

import com.example.online_courses.dto.CourseDto;
import com.example.online_courses.exceptions.CourseNotFoundExceptionByID;
import com.example.online_courses.models.Course;
import com.example.online_courses.service.interfaces.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@Controller
public class MainController {

    @Autowired
    CourseService courseService;

    /*@GetMapping("/")
    public String home(Model model) {
        model.addAttribute("name", "Главная страница");
        return "home";
    }*/
    @GetMapping("/")
    public String AllCourses(Model model) {
        List<CourseDto> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "courses-list";
    }

    @GetMapping("/courseDetails/{id}")
    public String courseDetails(@PathVariable UUID id, Model model) throws CourseNotFoundExceptionByID {
        CourseDto course = courseService.getCourseById(id);
        model.addAttribute("course", course);
        return "course-details";
    }
}