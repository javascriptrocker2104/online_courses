package com.example.online_courses.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "Course")
@Data
@AllArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long course_id;

    private String name;

    private String description;

    private LocalDateTime start_time;

    private LocalDateTime end_time;

    public Course() {
    }
    public Course(String name, String description, LocalDateTime startTime, LocalDateTime endTime) {
        this.name = name;
        this.description = description;
        this.start_time = startTime;
        this.end_time = endTime;
    }
}
