package com.example.online_courses.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;

    //@ManyToMany(mappedBy = "privileges", fetch = FetchType.EAGER)
    //private Collection<Role> roles;

    public Privilege(String name) {
        this.name = name;
    }
}