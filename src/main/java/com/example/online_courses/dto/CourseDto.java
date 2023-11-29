package com.example.online_courses.dto;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDto {
    private UUID id;
    private String name;
    private String description;
    private LocalDateTime start_time;
    private LocalDateTime end_time;
    //private LocalDateTime requestTime;
    private boolean block;
}

