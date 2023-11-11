package com.example.online_courses.repositories;


import com.example.online_courses.models.Content;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ContentRepository extends JpaRepository<Content, UUID> {
    Optional<Content> findById(UUID content_id);
    boolean existsById(UUID content_id);
}