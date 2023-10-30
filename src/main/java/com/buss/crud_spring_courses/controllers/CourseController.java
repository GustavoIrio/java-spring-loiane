package com.buss.crud_spring_courses.controllers;

import java.util.List;

import com.buss.crud_spring_courses.model.Course;
import com.buss.crud_spring_courses.repositories.CourseRepository;
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

    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> findCourseById(@PathVariable @NotNull @Positive Long id) {
        return courseRepository.findById(id)
                .map(courseFound -> ResponseEntity.ok().body(courseFound))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Course> listCourses() {
        return courseRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Course createCourse(@RequestBody @Valid Course course) {
        // return courseRepository.save(course);
        //return ResponseEntity.status(HttpStatus.CREATED)
        //        .body(courseRepository.save(course));
        return courseRepository.save(course);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid Course course) {
        return courseRepository.findById(id)
                .map(courseFound -> {
                    courseFound.setName(course.getName());
                    courseFound.setCategory(course.getCategory());
                    Course updated = courseRepository.save(courseFound);
                    return ResponseEntity.ok().body(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCourse(@PathVariable @NotNull @Positive Long id) {
        return courseRepository.findById(id)
                .map(courseFound -> {
                    courseRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
