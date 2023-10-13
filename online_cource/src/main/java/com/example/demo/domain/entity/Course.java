package com.example.demo.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Course")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {
    @Id
    @GeneratedValue
    private UUID course_id;

    @Column(unique = true)
    private String name;
    /*@ManyToOne
    @JoinTable(name = "group",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> course;*/


    @Column(name = "description")
    private String description;

    /*@Column(name="content_id")
    private long content_id;
    @OneToMany
    @JoinTable(name = "content",
            joinColumns = @JoinColumn(name = "content_id"),
            inverseJoinColumns = @JoinColumn(name = "content_id"))
    private List<Content> content;*/

    @Column(name = "start")
    private LocalDateTime start_time;
    @Column(name = "end_time")
    private LocalDateTime end_time;
}
