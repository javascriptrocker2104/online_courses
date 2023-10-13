/*package com.example.demo.repository;

import com.example.demo.domain.entity.Group;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GroupRepository {
    @Query(value = "SELECT g FROM group g")
    List<Group> getAll();
    Optional<Group> findByName(String name);
    Boolean existsByName(String name);
}*/
