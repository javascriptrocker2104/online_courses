package com.example.online_courses.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "Content")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Content {
    @Id
    @GeneratedValue
    private UUID content_id;
    private String name;
    private String info;
    private String type;
    private boolean block;

    public Content(String name, String info, String type) {
        this.name = name;
        this.info = info;
        this.type = type;
        this.block = false;
    }
}
