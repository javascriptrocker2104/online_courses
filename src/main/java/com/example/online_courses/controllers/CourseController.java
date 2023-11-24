package com.example.online_courses.controllers;


import com.example.online_courses.dto.CourseDto;
import com.example.online_courses.dto.CreateCourseRequest;
import com.example.online_courses.exceptions.CourseAlreadyExistException;
import com.example.online_courses.exceptions.CourseNotFoundException;
import com.example.online_courses.models.Course;
import com.example.online_courses.service.interfaces.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.online_courses.repositories.CourseRepository;
import static java.lang.String.format;

import java.util.List;
import java.util.UUID;

@Controller
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /*
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
        */
    @GetMapping("/course/all")
    //@PreAuthorize("hasAuthority('read')")
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/course/find/{name}")
    //@PreAuthorize("hasAuthority('read')")
    public ResponseEntity<CourseDto> getCourseByName(@PathVariable String name) throws CourseNotFoundException {
        return ResponseEntity.ok(courseService.getCourseByName(name));
    }

    @PostMapping("/course")
    @PreAuthorize("hasAuthority('modification')")
    public ResponseEntity<CourseDto> createCourse(@RequestBody CreateCourseRequest course) throws CourseAlreadyExistException {
        return ResponseEntity.ok(courseService.createCourse(course));
    }

    @PatchMapping("/course")
    @PreAuthorize("hasAuthority('modification')")
    public ResponseEntity<CourseDto> updateCourse(@RequestBody Course course) {
        return ResponseEntity.ok(courseService.updateCourse(course));
    }

    @DeleteMapping("/course")
    @PreAuthorize("hasAuthority('modification')")
    public ResponseEntity deleteCourse(@RequestBody String course_id) {
        courseService.deleteCourse(course_id);
        return ResponseEntity.ok()
                .body(format("Course with id= %s deleted", course_id));
    }


    @RequestMapping(value = "/course/{id}/hide", method = RequestMethod.PUT)
    public ResponseEntity<String> hideCourse(@PathVariable("id") UUID id) {

        Course course = courseRepository.findById(id).orElse(null);

        if (course == null) {
            return ResponseEntity.notFound().build();
        }

        course.setBlock(true);
        courseRepository.save(course);

        return ResponseEntity.ok().build();
    }




}
