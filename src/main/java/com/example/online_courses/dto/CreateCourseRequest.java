package com.example.online_courses.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCourseRequest {
    private String name;
    private String description;
    private LocalDateTime start_time;
    private LocalDateTime end_time;
    private boolean block;
}