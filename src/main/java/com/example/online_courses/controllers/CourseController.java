package com.example.online_courses.controllers;


import com.example.online_courses.dto.CourseDto;
import com.example.online_courses.dto.CreateCourseRequest;
import com.example.online_courses.dto.UserData;
import com.example.online_courses.exceptions.CourseAlreadyExistException;
import com.example.online_courses.exceptions.CourseNotFoundException;
import com.example.online_courses.exceptions.UserAlreadyExistException;
import com.example.online_courses.models.Course;
import com.example.online_courses.service.interfaces.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.online_courses.repositories.CourseRepository;
import static java.lang.String.format;
import com.example.online_courses.dto.CreateCourseRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // Выводит html добавление курса
    @GetMapping("/course/add")
    public String viewCourseAdd(Model model) {
        model.addAttribute("courseRequest", new CreateCourseRequest());
        return "course-add";
    }
    // Сохраняет введенные данные в базу данных
    /*
    @PostMapping("/course/add")
    public String coursePostAdd(@RequestParam String name, @RequestParam String description, @RequestParam LocalDateTime start_time, @RequestParam LocalDateTime end_time) {
        Course course = new Course(name, description, start_time, end_time);
        courseRepository.save(course);
        return "redirect:/";
        }

     */
    @PostMapping("/course/add")
    public String courseAdd(final @Valid CreateCourseRequest courseRequest, final BindingResult bindingResult, final Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("courseRequest", courseRequest);
            return "course-add";
        }
        try {
            courseService.createCourse(courseRequest);
        }catch (CourseAlreadyExistException e){
            bindingResult.rejectValue("name", "courseRequest.name","Курс с таким именем уже существует.");
            model.addAttribute("courseRequest", courseRequest);
            return "course-add";
        }
        return "redirect:/";
    }


    @GetMapping("/course/all")
    //@PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/course/find/{name}")
    //@PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<CourseDto> getCourseByName(@PathVariable String name) throws CourseNotFoundException {
        return ResponseEntity.ok(courseService.getCourseByName(name));
    }

    /*
    @PostMapping("/admin/course")
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<CourseDto> createCourse(@RequestBody CreateCourseRequest course) throws CourseAlreadyExistException {
        return ResponseEntity.ok(courseService.createCourse(course));
    }

     */

    // Нужно сначала запросить имя курса, потом вывести html как курс адд, только потом сохранить...
    /*
    @PatchMapping("/admin/course/change")
    public String courseChange(final @Valid CreateCourseRequest courseRequest, final BindingResult bindingResult, final Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("courseRequest", courseRequest);
            return "course-add";
        }
        courseService.updateCourse(courseRequest);
        return "redirect:/";
    }
    */
    //@PatchMapping("/admin/course/change")
    //public ResponseEntity<CourseDto> updateCourse(@RequestBody Course course) {
    //    return ResponseEntity.ok(courseService.updateCourse(course));
    //}

    @DeleteMapping("/deletecourse")
    //@PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity deleteCourse(@RequestBody String course_id) {
        courseService.deleteCourse(course_id);
        return ResponseEntity.ok()
                .body(format("Course with id= %s deleted", course_id));
    }


    @RequestMapping(value = "/admin/course/{id}/hide", method = RequestMethod.PUT)
    public ResponseEntity<String> hideCourse(@PathVariable("id") UUID id) {

        Course course = courseRepository.findById(id).orElse(null);

        if (course == null) {
            return ResponseEntity.notFound().build();
        }

        course.setBlock(true);
        courseRepository.save(course);

        return ResponseEntity.ok().build();
    }




}
