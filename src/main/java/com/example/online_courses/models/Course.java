package com.example.online_courses.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "Course")
@Data
@AllArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue
    private UUID course_id;

    private String name;

    private String description;

    private LocalDateTime start_time;

    private LocalDateTime end_time;
    private boolean block;

    public Course() {
    }
    public Course(String name, String description, LocalDateTime startTime, LocalDateTime endTime) {
        this.name = name;
        this.description = description;
        this.start_time = startTime;
        this.end_time = endTime;
        this.block = false;
    }

    //@ManyToMany(mappedBy="courses", fetch = FetchType.EAGER)
    //private Set<User> user;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Content> contents;


}
