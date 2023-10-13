package com.example.demo.service;

import com.example.demo.domain.dto.CreateCourseRequest;
import com.example.demo.domain.dto.CourseDto;
import com.example.demo.domain.entity.Course;
import com.example.demo.domain.exceptions.CourseAlreadyExistException;
import com.example.demo.domain.exceptions.CourseNotFoundException;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.interfaces.CourseService;
import com.example.demo.util.MappingUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

import static com.example.demo.util.MappingUtil.mapToCourseDto;
import static com.example.demo.util.MappingUtil.mapToCourseFromRequest;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public CourseDto getCourseByName(String name) throws CourseNotFoundException {
        return courseRepository.findByName(name).stream()
                .map(MappingUtil::mapToCourseDto)
                .findFirst()
                .orElseThrow(() -> new CourseNotFoundException(name));
    }

    @Override
    @Transactional
    public CourseDto createCourse(CreateCourseRequest request) throws CourseAlreadyExistException {
        if (!courseRepository.existsByName(request.getName())) {
            return mapToCourseDto(courseRepository.save(mapToCourseFromRequest(request)));
        }
        throw new CourseAlreadyExistException(request.getName());
    }

    @Override
    public List<Course> getAllCourse() {
        return courseRepository.getAll();
    }

    @Override
    @Transactional
    public CourseDto updateCourse(Course course) {
        return mapToCourseDto(courseRepository.save(course));
    }

    @Override
    public void deleteCourse(String course_id) {
        courseRepository.deleteById(UUID.fromString(course_id));
    }
}
