package com.example.online_courses.controllers;


import com.example.online_courses.models.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.online_courses.repositories.CourseRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/course")
    public String courseMain(Model model) {
        Iterable<Course> courses = courseRepository.findAll();
        model.addAttribute("courses", courses);
        return "course-main";
    }

    @GetMapping("/course/add")
    public String courseAdd(Model model) {
        return "course-add";
    }

    @PostMapping("/course/add")
    public String coursePostAdd(@RequestBody String name, @RequestBody String description, @RequestBody LocalDateTime start_time, @RequestBody LocalDateTime end_time, Model model) {
        Course course = new Course(name, description, start_time, end_time);
        courseRepository.save(course);
        return "redirect:/course";
    }
}
