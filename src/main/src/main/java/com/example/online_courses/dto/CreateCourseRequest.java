package com.example.online_courses.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCourseRequest implements Serializable {
    private String name;
    private String description;
    private LocalDateTime start_time;
    private LocalDateTime end_time;
    private boolean block;

    public CreateCourseRequest(String name, String description, LocalDateTime start_time, LocalDateTime end_time) {
        this.name = name;
        this.description = description;
        this.start_time = start_time;
        this.end_time = end_time;
        this.block = false;
    }
}

