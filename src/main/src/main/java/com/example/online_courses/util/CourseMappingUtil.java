package com.example.online_courses.util;

import com.example.online_courses.dto.CourseDto;
import com.example.online_courses.dto.CreateCourseRequest;
import com.example.online_courses.models.Course;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class CourseMappingUtil {

    public static CourseDto mapToCourseDto(Course course) {
        return CourseDto.builder()
                .name(course.getName())
                .description(course.getDescription())
                .start_time(course.getStart_time())
                .end_time(course.getEnd_time())
                .block(course.isBlock())
                //.requestTime(LocalDateTime.now())
                .build();
    }

    public static Course mapToCourseFromRequest(CreateCourseRequest request) {
        return Course.builder()
                .name(request.getName())
                .description(request.getDescription())
                .start_time(request.getStart_time())
                .end_time(request.getEnd_time())
                .block(request.isBlock())
                .build();
    }
}
