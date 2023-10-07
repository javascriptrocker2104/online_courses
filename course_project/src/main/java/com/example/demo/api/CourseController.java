package com.example.demo.api;

import com.example.demo.domain.entity.Courses;
import com.example.demo.repository.CourseRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/courses")
@Data
@AllArgsConstructor
public class CourseController{
    @Autowired
    private CourseRepository courseRepo;
    @PostMapping
    public Courses createCourse(@RequestBody Courses course){
        return Courses.builder()
                .name(course.getName())
                .description(course.getDescription())
                .content(course.getContent())
                .start_time(course.getStart_time())
                .end_time(course.getEnd_time())
                .build();
    }
}

