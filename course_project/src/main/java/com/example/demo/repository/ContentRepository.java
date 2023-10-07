package com.example.demo.repository;

import com.example.demo.domain.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface ContentRepository extends JpaRepository<Content,String>{
@Query(value="SELECT c FROM content c WHERE c.name=(:name")
    Content findByName(String name);
}

