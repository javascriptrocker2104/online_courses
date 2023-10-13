package com.example.demo.api.rest;

import com.example.demo.domain.dto.CreateCourseRequest;
import com.example.demo.domain.dto.CourseDto;
import com.example.demo.domain.entity.Course;
import com.example.demo.domain.exceptions.CourseAlreadyExistException;
import com.example.demo.domain.exceptions.CourseNotFoundException;
import com.example.demo.service.interfaces.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static java.lang.String.format;

@RestController
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping("/course/all")
    @PreAuthorize("hasAuthority('read')")
    public ResponseEntity<List<Course>> getAllCourse() {
        return ResponseEntity.ok(courseService.getAllCourse());
    }

    @GetMapping("/course/find/{name}")
    @PreAuthorize("hasAuthority('read')")
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
                .body(format("course with id= %s deleted", course_id));
    }
}
