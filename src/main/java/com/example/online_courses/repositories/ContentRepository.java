package com.example.online_courses.repositories;


import com.example.online_courses.models.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContentRepository extends JpaRepository<Content, UUID> {
    @Query(value = "SELECT ct FROM Content ct WHERE ct.block = false")
    Optional<Content> findById(UUID content_id);
    boolean existsById(UUID content_id);

    @Query(value = "SELECT ct FROM Content ct WHERE ct.block = false")
    List<Content> getAll();

    Optional<Content> findContentByName(String name);
}