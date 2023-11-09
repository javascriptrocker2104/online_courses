package com.example.online_courses.controllers;

import static java.lang.String.format;
import com.example.online_courses.dto.ContentDto;
import com.example.online_courses.dto.CreateContentRequest;
import com.example.online_courses.exceptions.ContentAlreadyExistException;
import com.example.online_courses.exceptions.ContentNotFoundException;
import com.example.online_courses.service.interfaces.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AutoConfiguration
@RestController
@RequiredArgsConstructor
public class ContentController {
    private final ContentService contentService;

    @GetMapping("/Content/{id}")
    @PreAuthorize("hasAuthority('read')")
    public ResponseEntity<ContentDto> getContentById(@PathVariable UUID id) throws ContentNotFoundException {
        return ResponseEntity.ok(contentService.getContentById(id));
    }

    @PostMapping("/Content")
    @PreAuthorize("hasAuthority('modification')")
    public ResponseEntity<ContentDto> createContent(@RequestBody CreateContentRequest content) throws ContentAlreadyExistException {
        return ResponseEntity.ok(contentService.createContent(content));
    }

    @DeleteMapping("/Content")
    @PreAuthorize("hasAuthority('modification')")
    public ResponseEntity deleteContent(@RequestBody UUID content_id) {
        contentService.deleteContent(content_id);
        return ResponseEntity.ok()
                .body(format("Content with id= %s deleted", content_id));
    }


}