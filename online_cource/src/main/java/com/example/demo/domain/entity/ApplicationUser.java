package com.example.demo.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationUser {

    @Id
    @GeneratedValue
    private UUID id;

    private String firstname;
    private String secondname;
    private Long mobile_phone;

    private String email;

    private String password;
    @Enumerated(value = EnumType.STRING)
    @Column(name ="role")
    private ApplicationUserRole role;
}
