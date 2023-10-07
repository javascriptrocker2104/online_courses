package com.example.demo.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;


import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="courses")

public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long course_id;
    @ManyToOne
    @JoinTable(name = "groups",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Courses> courses;


    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;

    @Column(name="content_id")
    private long content_id;
    @OneToMany
    @JoinTable(name = "content",
            joinColumns = @JoinColumn(name = "content_id"),
            inverseJoinColumns = @JoinColumn(name = "content_id"))
    private List<Content> content;

    @Column(name = "start")
    private Date start_time;
    @Column(name = "end")
    private Date end_time;
}