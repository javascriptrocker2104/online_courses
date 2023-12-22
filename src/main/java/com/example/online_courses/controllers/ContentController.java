package com.example.online_courses.controllers;

import static java.lang.String.format;
import com.example.online_courses.dto.ContentDto;
import com.example.online_courses.dto.CreateContentRequest;
import com.example.online_courses.dto.CreateCourseRequest;
import com.example.online_courses.exceptions.ContentNotFoundException;
import com.example.online_courses.models.Content;
import com.example.online_courses.models.Course;
import com.example.online_courses.repositories.ContentRepository;
import com.example.online_courses.service.interfaces.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ContentController {
    @Autowired
    private final ContentService contentService;

    @Autowired
    private ContentRepository contentRepository;

    @GetMapping("/content/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ContentDto> getContentById(@PathVariable UUID id) throws ContentNotFoundException {
        return ResponseEntity.ok(contentService.getContentById(id));
    }
    @GetMapping("/content/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Content>> getAllContents() {
        return ResponseEntity.ok(contentService.getAllContents());
    }


    @PostMapping("/admin/deletecontent")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteContent(@RequestParam(name="name", required=false) String name) {
        Content content = contentRepository.findContentByName(name).orElse(null);
        UUID content_id = content.getContent_id();
        contentService.deleteContent(content_id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/deletecontent")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String viewContentDelete() {
        return "deletecontent";
    }

    @PostMapping(value = "/admin/blockcontent")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String blockContent(@RequestParam(name="name", required=false) String name) {
        Content content = contentRepository.findContentByName(name).orElse(null);
        content.setBlock(true);
        contentRepository.save(content);
        return "redirect:/admin";
    }

    @GetMapping("/admin/blockcontent")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String viewContentBlock() {
        return "blockcontent";
    }


    @GetMapping("/content/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String viewContentAdd() {
        return "content-add";
    }

    @PostMapping("/content/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String contentAdd(@RequestParam(name="name", required=false) String name,
                            @RequestParam(name="info", required=false) String info,
                            @RequestParam(name="type", required=false) String type){

        Content content = new Content(name, info, type);
        contentRepository.save(content);
        return "redirect:/admin";
    }
}
