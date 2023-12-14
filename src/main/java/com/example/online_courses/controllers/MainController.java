package com.example.online_courses.controllers;

import com.example.online_courses.dto.CourseDto;
import com.example.online_courses.exceptions.CourseNotFoundException;
import com.example.online_courses.exceptions.CourseNotFoundExceptionByID;
import com.example.online_courses.models.Course;
import com.example.online_courses.service.interfaces.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Controller
public class MainController {

    @Autowired
    CourseService courseService;

    /*@GetMapping("/")
    public String home(Model model) {
        model.addAttribute("name", "Главная страница");
        return "home";
    }*/
    @GetMapping("/")
    public String AllCourses(Model model) {
        List<CourseDto> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "courses-list";
    }

    @GetMapping("/courseDetails/{name}")
    public String courseDetails(@PathVariable String name, Model model) throws CourseNotFoundException {
        CourseDto course = courseService.getCourseByName(name);
        model.addAttribute("course", course);
        return "one_course";
    }
    private static final Logger LOGGER = Logger.getLogger(CourseService.class.getName());
    @GetMapping("/search")
    public String searchCourses(@RequestParam String query, Model model) {
        LOGGER.info("Search query: " + query);

        Optional<Course> searchResults = courseService.searchCourses(query);

        if (searchResults.isPresent()) {
            LOGGER.info("Course found: " + searchResults.get().toString());
        } else {
            LOGGER.info("No results found.");
        }

        model.addAttribute("courses", searchResults);
        return "course-details";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String AdminPage() {
        return "admin-main";
    }

}