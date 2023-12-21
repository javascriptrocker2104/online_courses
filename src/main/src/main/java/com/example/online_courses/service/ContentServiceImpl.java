package com.example.online_courses.service;


import com.example.online_courses.dto.ContentDto;
import com.example.online_courses.dto.CreateContentRequest;
import com.example.online_courses.exceptions.ContentAlreadyExistException;
import com.example.online_courses.exceptions.ContentNotFoundException;
import com.example.online_courses.models.Content;
import com.example.online_courses.models.Course;
import com.example.online_courses.models.User;
import com.example.online_courses.repositories.ContentRepository;
import com.example.online_courses.repositories.CourseRepository;
import com.example.online_courses.service.interfaces.ContentService;
import com.example.online_courses.util.ContentMappingUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.example.online_courses.util.ContentMappingUtil.mapToContentDto;
import static com.example.online_courses.util.ContentMappingUtil.mapToContentFromRequest;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {

    private final ContentRepository contentRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public ContentDto getContentById(UUID id) throws ContentNotFoundException {

        return contentRepository.findById(id).stream()
                .map(ContentMappingUtil::mapToContentDto)
                .findFirst()
                .orElseThrow(() -> new ContentNotFoundException(id));
    }

    @Override
    @Transactional
    public ContentDto createContent(CreateContentRequest request) throws ContentAlreadyExistException {
        if (!contentRepository.existsById(request.getContent_id())) {
            return mapToContentDto(contentRepository.save(mapToContentFromRequest(request)));
        }
        throw new ContentAlreadyExistException(request.getName());
    }
    @Override
    public void deleteContent(UUID content_id) {
        contentRepository.deleteById(content_id);
    }

    @Transactional
    public void assignCourseToContent(UUID content_id, UUID course_id) {
        Content content = contentRepository.findById(content_id).orElseThrow(() -> new EntityNotFoundException("Content not found"));
        Course course = courseRepository.findById(course_id).orElseThrow(() -> new EntityNotFoundException("Course not found"));
        course.getContents().add(content);
        courseRepository.save(course);
    }

    @Override
    public List<Content> getAllContents() {
        return contentRepository.getAll();
    }

}

