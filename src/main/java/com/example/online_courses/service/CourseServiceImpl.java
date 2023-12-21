package com.example.online_courses.service;

import com.example.online_courses.dto.CourseDto;
import com.example.online_courses.dto.CreateCourseRequest;
import com.example.online_courses.exceptions.CourseAlreadyExistException;
import com.example.online_courses.exceptions.CourseNotFoundException;
import com.example.online_courses.exceptions.CourseNotFoundExceptionByID;
import com.example.online_courses.models.Course;
import com.example.online_courses.models.User;
import com.example.online_courses.repositories.CourseRepository;
import com.example.online_courses.repositories.UserRepository;
import com.example.online_courses.service.interfaces.CourseService;
import com.example.online_courses.util.CourseMappingUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.example.online_courses.util.CourseMappingUtil.mapToCourseDto;
import static com.example.online_courses.util.CourseMappingUtil.mapToCourseFromRequest;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public CourseDto getCourseByName(String name) throws CourseNotFoundException {
        return courseRepository.findByName(name).stream()
                .map(CourseMappingUtil::mapToCourseDto)
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
    public List<CourseDto> getAllCourses() {
        return courseRepository.getAll().stream()
                .map(course -> CourseDto.builder()
                        .name(course.getName())
                        .description(course.getDescription())
                        .start_time(course.getStart_time())
                        .end_time(course.getEnd_time())
                        .block(course.isBlock())
                        .build())
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public CourseDto updateCourse(Course course) {
        return mapToCourseDto(courseRepository.save(course));
    }
    @Override
    public void deleteCourse(UUID courseId) {
        courseRepository.deleteById(courseId);
    }

    @Transactional
    public void assignUserToCourse(UUID id, UUID course_id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        Course course = courseRepository.findById(course_id).orElseThrow(() -> new EntityNotFoundException("Course not found"));
        user.getCourses().add(course);
        userRepository.save(user);
    }

    @Override
    public CourseDto getCourseById(UUID id) throws CourseNotFoundExceptionByID {
        return courseRepository.findById(id).stream()
                .map(CourseMappingUtil::mapToCourseDto)
                .findFirst()
                .orElseThrow(() -> new CourseNotFoundExceptionByID(id));
    }

    public Optional<Course> searchCourses(String query) {
        return courseRepository.findByName(query);
    }
}

