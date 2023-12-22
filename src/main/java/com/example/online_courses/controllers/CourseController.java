package com.example.online_courses.controllers;


import com.example.online_courses.dto.CourseDto;
import com.example.online_courses.dto.CreateCourseRequest;
import com.example.online_courses.dto.UserData;
import com.example.online_courses.exceptions.CourseAlreadyExistException;
import com.example.online_courses.exceptions.CourseNotFoundException;
import com.example.online_courses.exceptions.UserAlreadyExistException;
import com.example.online_courses.models.Course;
import com.example.online_courses.service.interfaces.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.online_courses.repositories.CourseRepository;
import static java.lang.String.format;
import com.example.online_courses.dto.CreateCourseRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/course/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String viewCourseAdd() {
        return "course-add";
    }

    @PostMapping("/course/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String courseAdd(@RequestParam(name="name", required=false) String name,
                            @RequestParam(name="description", required=false) String description,
                            @RequestParam(name="start_time", required=false) LocalDateTime start_time,
                            @RequestParam(name="end_time", required=false)LocalDateTime end_time){

        Course course = new Course(name, description, start_time, end_time);
        courseRepository.save(course);
        return "redirect:/admin";
    }

    @GetMapping("/course/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/course/find/{name}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CourseDto> getCourseByName(@PathVariable String name) throws CourseNotFoundException {
        return ResponseEntity.ok(courseService.getCourseByName(name));
    }

   
    @PostMapping("/admin/deletecourse")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteCourse(@RequestParam(name="name", required=false) String name) {
        Course course = courseRepository.findByName(name).orElse(null);
        UUID course_id = course.getCourse_id();
        course.getContents().clear();
        courseService.deleteCourse(course_id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/deletecourse")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String viewCourseDelete() {
        return "deletecourse";
    }



    @PostMapping(value = "/admin/blockcourse")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String hideCourse(@RequestParam(name="name", required=false) String name) {

        Course course = courseRepository.findByName(name).orElse(null);

        course.setBlock(true);
        courseRepository.save(course);

        return "redirect:/admin";
    }

    @GetMapping("/admin/blockcourse")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String viewCourseBlock() {
        return "blockcourse";
    }



}
