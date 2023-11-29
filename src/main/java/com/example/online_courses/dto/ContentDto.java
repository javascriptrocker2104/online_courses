package com.example.online_courses.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@Builder
public class ContentDto {
    @Id
    private UUID content_id;
    private String name;
    private String info;
    private String type;
    //private LocalDateTime requestTime;
    private boolean block;
}
