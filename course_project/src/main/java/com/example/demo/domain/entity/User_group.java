package com.example.demo.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import jakarta.persistence.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="user_group")

public class User_group{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long Id;

    @Column(name="person_id")
    private People person_id;
    @Column(name="group_id")
    private Groups group_id;

    @ManyToOne
    @JoinTable(name = "people",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private List<People> people;

    @ManyToOne
    @JoinTable(name = "groups",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private List<Groups> groups;
}