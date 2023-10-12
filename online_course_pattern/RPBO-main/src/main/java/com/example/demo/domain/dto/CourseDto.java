package com.example.demo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

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
