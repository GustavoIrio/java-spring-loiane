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
    public ResponseEntity<Course> findCourseById(@PathVariable @NotNull @Positive Long id) {
        return courseService.findCourseById(id)
                .map(courseFound -> ResponseEntity.ok().body(courseFound))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Course createCourse(@RequestBody @Valid Course course) {
        return courseService.createCourse(course);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid Course course) {
        return courseService.updateCourse(id, course)
                .map(courseFound -> ResponseEntity.ok().body(courseFound))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCourse(@PathVariable @NotNull @Positive Long id) {
        if (courseService.deleteCourse(id))
            return ResponseEntity.noContent().build();
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
