package com.example.online_courses.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@Builder
public class CreateContentRequest {
    @Id
    private UUID content_id;
    private String name;
    private String info;
    private String type;
}
