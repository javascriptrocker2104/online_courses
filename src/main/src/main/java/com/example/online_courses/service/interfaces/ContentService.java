package com.example.online_courses.service.interfaces;


import com.example.online_courses.dto.ContentDto;
import com.example.online_courses.dto.CreateContentRequest;
import com.example.online_courses.exceptions.ContentAlreadyExistException;
import com.example.online_courses.exceptions.ContentNotFoundException;
import com.example.online_courses.models.Content;
import com.example.online_courses.models.Course;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.UUID;

public interface ContentService {
    ContentDto getContentById(UUID id) throws ContentNotFoundException;
    ContentDto createContent(CreateContentRequest content) throws ContentAlreadyExistException;
    void deleteContent(UUID content_id);
    List<Content> getAllContents();
}

