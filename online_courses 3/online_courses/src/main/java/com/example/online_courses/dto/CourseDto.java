package com.example.online_courses.dto;
import lombok.Data;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDto {
    private String name;
    private String description;
    private LocalDateTime start_time;
    private LocalDateTime end_time;
    private LocalDateTime requestTime;
}

