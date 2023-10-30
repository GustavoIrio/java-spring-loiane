package com.buss.crud_spring_courses.controllers;

import java.util.List;

import com.buss.crud_spring_courses.model.Course;
import com.buss.crud_spring_courses.services.CourseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public @ResponseBody List<Course> listCourses() {
        return courseService.listCourses();
    }

    @GetMapping("/{id}")
    public Course findCourseById(@PathVariable @NotNull @Positive Long id) {
        return courseService.findCourseById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Course createCourse(@RequestBody @Valid Course course) {
        return courseService.createCourse(course);
    }

    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid Course course) {
        return courseService.updateCourse(id, course);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourse(@PathVariable @NotNull @Positive Long id) {
        courseService.deleteCourse(id);
    }
}
