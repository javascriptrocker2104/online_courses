/*package com.example.demo.repository;

import com.example.demo.domain.entity.Content;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface ContentRepository {
    @Query(value = "SELECT cont FROM content cont")
    List<Content> getAll();
    Optional<Content> findByName(String name);
    Boolean existsByName(String name);
}*/
