package com.example.demo.util;

import com.example.demo.domain.dto.CreateCourseRequest;
import com.example.demo.domain.dto.CourseDto;
import com.example.demo.domain.entity.Course;
import lombok.experimental.UtilityClass;
import java.time.LocalDateTime;

@UtilityClass
public class MappingUtil {

    public static CourseDto mapToCourseDto(Course course) {
        return CourseDto.builder()
                .name(course.getName())
                .description(course.getDescription())
                .start_time(course.getStart_time())
                .end_time(course.getEnd_time())
                .requestTime(LocalDateTime.now())
                .build();
    }

    public static Course mapToCourseFromRequest(CreateCourseRequest request) {
        return Course.builder()
                .name(request.getName())
                .description(request.getDescription())
                .start_time(request.getStart_time())
                .end_time(request.getEnd_time())
                .build();
    }
}
